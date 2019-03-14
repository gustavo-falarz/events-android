package com.pinecone.events.ui.signin

import com.google.firebase.auth.FirebaseAuth
import com.pinecone.events.R
import com.pinecone.events.userInfo

class SignInPresenter(var view: SignInView) : Contract.Presenter {

    override fun signInWithEmail(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(view) { task ->
                    when {
                        task.isSuccessful -> {
                            checkActivation()
                        }
                        else -> {
                            view.onErrorSigningIn(task.exception!!)
                        }
                    }

                }
    }

    override fun signInWithGoogle() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signUp() {
        view.showSignUpScreen()
    }

    override fun checkActivation() {
        if (FirebaseAuth.getInstance().currentUser!!.isEmailVerified) {
            getToken()
        } else {
            view.onErrorSigningIn(Exception(view.getString(R.string.error_pending_activation)))
        }
    }

    override fun getToken() {
        FirebaseAuth.getInstance().currentUser!!.getIdToken(true)
                .addOnSuccessListener {
                    userInfo.token = it.token!!
                    view.onUserSignedIn()
                }
                .addOnFailureListener {
                    view.onErrorSigningIn(it)
                }
    }
}