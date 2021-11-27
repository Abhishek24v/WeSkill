package com.weskill2.ui.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class OTP_Receiver : BroadcastReceiver() {

    var otpReceiverListener : OtpReceiverListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {

        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
            val extras = intent.extras
            val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
            when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> {

                    val messageIntent = extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                    if (messageIntent != null) {
                           otpReceiverListener?.onOtpSuccess(messageIntent)
                    }
                }       // Get SMS message contents
                CommonStatusCodes.TIMEOUT -> {
                    otpReceiverListener?.onOtpTimeout()
                }
            }
        }
    }

        interface OtpReceiverListener {
            fun onOtpSuccess(intent : Intent)
            fun onOtpTimeout()
        }
}