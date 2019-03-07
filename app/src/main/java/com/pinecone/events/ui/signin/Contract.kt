package com.pinecone.events.ui.signin

import java.lang.Exception

interface Contract {

    interface View {
        fun onClickSignUp()
        fun onClickSignIn()
        fun onUserSignedIn()
        fun showSignUpScreen()
        fun onErrorSigningIn(exception: Exception)
    }

    interface Presenter {
        fun signInWithEmail(email: String, password: String)
        fun signInWithGoogle()
        fun signUp()
        fun checkActivation()
    }
}