package com.pinecone.events.ui.signin

import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.pinecone.events.R
import com.pinecone.events.ui.BaseView
import com.pinecone.events.ui.events.EventsView
import com.pinecone.events.ui.signUp.SignUpView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import java.lang.Exception

class SignInView : BaseView(), Contract.View {
    val presenter = SignInPresenter(FirebaseAuth.getInstance(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btSignUp.setOnClickListener { onClickSignUp() }
        btSignIn.setOnClickListener { onClickSignIn() }
    }

    override fun onClickSignUp() {
        presenter.signUp()
    }

    override fun onClickSignIn() {
        presenter.signInWithEmail(email = etEmail.text.toString(),
                password = etPassword.text.toString())
    }

    override fun onErrorSigningIn(exception: Exception) {
        handleException(exception)
    }

    override fun showSignUpScreen() {
        startActivity<SignUpView>()
    }

    override fun onUserSignedIn() {
        startActivity<EventsView>()
    }
}
