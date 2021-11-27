package com.weskill2.ui.authentication.login

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.weskill2.helper.baseUrl
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.weskill2.R
import com.weskill2.databinding.FragmentLoginBinding
import com.weskill2.helper.token
import com.weskill2.helper.userId
import com.weskill2.helper.userName
import com.weskill2.network.NetworkService
import com.weskill2.network.models.LoginUserModel
import com.weskill2.network.models.user.LoginUserResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    //lateinit var sharedPref: SharedPreferences

    companion object {

        //val begin = System.nanoTime()
        val retrofit =
            Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.ccPicker.registerCarrierNumberEditText(binding.mobileNo)

        binding.otpBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_otpFragment)
        }

        setClick()
        return binding.root
    }

    private fun setClick() {

        val loginUserResponse: MutableLiveData<Response<LoginUserResponse>> = MutableLiveData()

        binding.loginBtn.setOnClickListener {
            if (binding.signInText.text == "Loading")
                return@setOnClickListener
            binding.signInText.text = "Loading"
            binding.progressSignIn.visibility = View.VISIBLE

            GlobalScope.launch {
                try {

                    loginUserResponse.postValue(
                        retrofit.loginUser(
                            LoginUserModel(
                                binding.ccPicker.fullNumber.toString(),
                                binding.password.text.toString()
                            )
                        )
                    )
                } catch (e: Exception) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "Please Check Your Internet Connection!", Toast.LENGTH_SHORT)
                            .show()
                        Toast.makeText(requireContext(), "${binding.ccPicker.fullNumber.toString()}", Toast.LENGTH_SHORT)
                            .show()
                        binding.signInText.text = "SIGN IN"
                        binding.progressSignIn.visibility = View.GONE
                    }
                }
            }
        }

        loginUserResponse.observe(viewLifecycleOwner, {
            val response = it

            if (response.code() == 200) {

                /*sharedPref.edit().putString(token, "Bearer " + response.body()!!.jwt).apply()
                sharedPref.edit().putString(userId, response.body()!!.user.id).apply()
                sharedPref.edit().putString(userName, response.body()!!.user.name).apply()*/

                Toast.makeText(requireContext(), "Welcome!", Toast.LENGTH_SHORT).show()
                /*MainActivity().start(requireActivity(), response.body()!!)
                requireActivity().finish()
*/
                findNavController().navigate(R.id.action_loginFragment_to_communityFragment)
                //val end = System.nanoTime()
                //var min = (end - begin)/1000000
                /*Toast.makeText(requireContext(), "$min", Toast.LENGTH_SHORT)
                    .show()*/

               // Analytics.logEvent(Event.SIGN_IN_SUCCESSFUL, HashMaps.signInSuccess())

            } else {
                Toast.makeText(requireContext(), "Invalid Password", Toast.LENGTH_SHORT)
                    .show()
            }
            binding.signInText.text = "SIGN IN"
            binding.progressSignIn.visibility = View.GONE
        })

        binding.signUpText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)

           // Analytics.logEvent(Event.SIGN_UP,HashMaps.signUpLink())
        }

            binding.forgetPassBtn.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)

               // Analytics.logEvent(Event.FORGET_PASSWORD,HashMaps.forgetPass())
            }
    }

}