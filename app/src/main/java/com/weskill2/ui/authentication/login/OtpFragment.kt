package com.weskill2.ui.authentication.login

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.weskill.models.reset_password.ForgotPassword
import com.weskill.models.reset_password.ResetPassword
import com.weskill2.R
import com.weskill2.databinding.FragmentOtpBinding
import com.weskill2.helper.baseUrl
import com.weskill2.network.NetworkService
import com.weskill2.network.models.user.LoginUserResponse
import com.weskill2.ui.activities.OTP_Receiver
import com.weskill2.ui.authentication.ResetPasswordFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class OtpFragment : Fragment() {

    private lateinit var binding : FragmentOtpBinding
    var generating = false
    private val REQ_USER_CONCENT = 200

    var otpReceiver: OTP_Receiver? = null
    val loginUserResponse: MutableLiveData<Response<LoginUserResponse>> = MutableLiveData()

    companion object {
        private val retrofit =
            Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtpBinding.inflate(inflater, container, false)


        binding.signUpText.setOnClickListener {
            findNavController().navigate(R.id.action_otpFragment_to_signUpFragment)
        }

        binding.loginBtn.setOnClickListener {

        }
        otpSendClick()
        setView()
        smsRetriver()
        return binding.root
    }

    private fun otpSendClick() {

        binding.ccPicker.registerCarrierNumberEditText(binding.mobileNo)
        Log.d("ForgotPassword",binding.ccPicker.selectedCountryCodeWithPlus.toString())

        binding.otpBtn.setOnClickListener {
            if (generating)
                return@setOnClickListener

            if (binding.mobileNo.text.isNullOrEmpty()) {
                Toast.makeText(context, "Input cannot be blank!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            binding.getOtpText.text = "Generating"
            binding.progressOtp.visibility = View.VISIBLE

            //Analytics.logEvent(Event.OTP_GENERATE, HashMaps.otpGenerate())

            generating = true
            GlobalScope.launch {
                try {
                    Log.d("ForgotPassword",binding.ccPicker.fullNumber.toString()+" "+binding.ccPicker.selectedCountryCodeWithPlus.toString())
                    val res = LoginFragment.retrofit.forgotPassword(ForgotPassword(binding.ccPicker.selectedCountryCodeWithPlus.toString(),binding.ccPicker.fullNumber.toString()))
                    if (res.isSuccessful && res.body()?.ok == true) {
                        activity?.runOnUiThread {
                            binding.titleText.text = "Verify"
                            binding.smallText.text = "Confirm your OTP"
                            binding.otpBox.visibility = View.VISIBLE
                            binding.resendText.visibility = View.VISIBLE
                            Toast.makeText(
                                context,
                                "OTP sent to your registered phone number or WhatsApp",
                                Toast.LENGTH_LONG
                            ).show()

                            //Analytics.logEvent(Event.OTP_SEND,HashMaps.otpGenerate())
                        }
                    } else {
                        activity?.runOnUiThread {
                            Toast.makeText(
                                context,
                                "Phone number doesn't exist!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    activity?.runOnUiThread {
                        Toast.makeText(context,"Error :/", Toast.LENGTH_SHORT).show()
                    }
                }
                activity?.runOnUiThread {
                    generating=false
                    binding.getOtpText.text = "Generate OTP"
                    binding.progressOtp.visibility = View.GONE
                }
            }
        }
    }

    private fun smsRetriver() {
        val client = SmsRetriever.getClient(requireActivity())
        client.startSmsUserConsent(null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQ_USER_CONCENT) {
            if ((resultCode == Activity.RESULT_OK) && (data != null)) {
                val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                getOtpFromMessage(message)
            }
        }
    }

    private fun getOtpFromMessage(message: String?) {
        val pattern = Pattern.compile("(|^)\\d{6}")
        val matcher = pattern.matcher(message)

        if(matcher.find()) {
            binding.otpView.setOTP(matcher.group(0)!!)
        }

    }

    private fun autoOtpReceiver() {
        otpReceiver = OTP_Receiver()

        otpReceiver!!.otpReceiverListener =
            object : OTP_Receiver.OtpReceiverListener {

                override fun onOtpSuccess(intent : Intent) {

                    startActivityForResult(intent,REQ_USER_CONCENT)
                }

                override fun onOtpTimeout() {
                    Toast.makeText(requireContext(),"Otp TimeOut",Toast.LENGTH_SHORT).show()
                }
            }

        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        context?.registerReceiver(otpReceiver,intentFilter)
    }

    private fun setView() {
        setObserver()
        setClicks()
    }

    private fun setObserver() {
        loginUserResponse.observe(viewLifecycleOwner, {
            val response = it

            if (response.code() == 200) {

                /*sharedPref.edit().putString(token, "Bearer " + response.body()!!.jwt).apply()
                sharedPref.edit().putString(userId, response.body()!!.user.id).apply()*/

                Toast.makeText(requireContext(), "Welcome!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_otpFragment_to_communityFragment)
                /*MainActivity().start(requireActivity(), response.body()!!)
                requireActivity().finish()*/
            } else {
                Toast.makeText(requireContext(), "Some error occurred :/", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun setClicks() {

        binding.loginBtn.setOnClickListener {
            if (!validInput())
                return@setOnClickListener
            if (binding.loginText.text.toString().equals("Loading"))
                return@setOnClickListener

            //Analytics.logEvent(Event.RESET_PASSWORD, HashMaps.resetPass())

            val load: Runnable = Runnable {
                activity?.runOnUiThread {
                    binding.loginText.text = "Loading"
                    binding.progresslogin.visibility = View.VISIBLE
                }
            }
            val failed: Runnable = Runnable {
                activity?.runOnUiThread {
                    binding.loginText.text = "login"
                    binding.progresslogin.visibility = View.GONE
                    Toast.makeText(context, "Invalid OTP!", Toast.LENGTH_SHORT).show()
                }
            }
            load.run()
            /*GlobalScope.launch {
                try {
                    val res = retrofit.resetPassword(
                        ResetPassword(
                            identifier,
                            npswrd.text.toString(),
                            cnfpswrd.text.toString(),
                            otp.otp.toString()
                        )
                    )
                    if (res.isSuccessful) {
                       // loginUser()
                        //Analytics.logEvent(Event.RESET_PASS, HashMaps.resetPassSuccess())
                    } else {
                        failed.run()
                    }
                } catch (e: Exception) {
                    activity?.runOnUiThread {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }
            }*/
        }
    }

    /*private suspend fun loginUser() {
        try {
            loginUserResponse.postValue(
                SignInFragment.retrofit.loginUser(
                    LoginUserModel(
                        identifier,
                        npswrd.text.toString()
                    )
                )
            )
        } catch (e: Exception) {
            requireActivity().runOnUiThread {
                Toast.makeText(requireContext(), "Please Check Your Internet Connection!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }*/

    private fun validInput(): Boolean {
        if (binding.otpView.otp?.length != 6) {
            Toast.makeText(requireContext(), "Invalid OTP", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onStart() {
        super.onStart()
        autoOtpReceiver()
    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(otpReceiver)
    }
}