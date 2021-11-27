package com.weskill.models.reset_password

data class ForgotPassword(
    val countryCode: String,
    val identifier:String
)

data class ForgotPasswordResponse(
    val ok: Boolean
)
