package com.weskill.models.reset_password

data class ResetPassword(
    val identifier: String,
    val password: String,
    val passwordConfirmation: String,
    val resetPasswordToken: String
)