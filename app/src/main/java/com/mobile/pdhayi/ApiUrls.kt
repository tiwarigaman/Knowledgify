package com.mobile.pdhayi

// Object to store API endpoint URLs used in the application
object ApiUrls {
    // Endpoint for user registration
    const val REGISTER = "/register"

    // Endpoint for user login
    const val LOGIN = "/login"

    // Endpoint for OTP verification
    const val VERIFY = "/verify"

    // Endpoint for OTP verification in beta environment
    const val BETA_VERIFY = "https://knowledgify-backend-beta.onrender.com/api/verify"

    // Endpoint for Google sign-up in beta environment
    const val BETA_GOOGLE_SIGN_UP = "https://knowledgify-backend-1.onrender.com/api/googleSignUp"

    // Endpoint for Google sign-in in beta environment
    const val BETA_GOOGLE_SIGN_IN = "https://knowledgify-backend-1.onrender.com/api/googleSignIn"
}
