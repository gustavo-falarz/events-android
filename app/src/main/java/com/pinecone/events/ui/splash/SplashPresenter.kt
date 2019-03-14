package com.pinecone.events.ui.splash

import com.google.firebase.auth.FirebaseAuth

class SplashPresenter(var view: SplashView) : Contract.Presenter {

    override fun checkStart() {
        val user = FirebaseAuth.getInstance().currentUser
        when (user) {
            null -> view.showSignInScreen()
            else -> view.showMainScreen()
        }
    }
}