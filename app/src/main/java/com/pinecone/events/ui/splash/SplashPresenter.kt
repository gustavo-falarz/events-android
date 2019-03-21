package com.pinecone.events.ui.splash

import com.google.firebase.auth.FirebaseAuth
import com.pinecone.events.userInfo

class SplashPresenter(var view: SplashView) : Contract.Presenter {

    override fun checkStart() {
        val user = FirebaseAuth.getInstance().currentUser

        when (user) {
            null -> view.showSignInScreen()
            else -> view.showMainScreen()
        }
    }

    override fun getToken() {
        FirebaseAuth.getInstance().currentUser!!.getIdToken(true)
                .addOnSuccessListener {
                    userInfo.token = it.token!!
                    view.showMainScreen()
                }
                .addOnFailureListener {
                    view.showSignInScreen()
                }
    }
}
