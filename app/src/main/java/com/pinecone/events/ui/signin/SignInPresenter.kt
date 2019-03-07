package com.pinecone.events.ui.signin

import com.google.firebase.auth.FirebaseAuth
import com.pinecone.events.R
import java.lang.Exception

class SignInPresenter(var auth: FirebaseAuth, var view: SignInView) : Contract.Presenter {

    override fun signInWithEmail(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
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
        if (auth.currentUser!!.isEmailVerified) {
            view.onUserSignedIn()
        } else {
            view.onErrorSigningIn(Exception(view.getString(R.string.error_pending_activation)))
        }
    }
}