package com.pinecone.events.ui.signin

import android.os.Bundle
import com.pinecone.events.R
import com.pinecone.events.ui.BaseView
import com.pinecone.events.ui.events.EventsView
import com.pinecone.events.ui.signUp.SignUpView
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class SignInView : BaseView(), Contract.View {
    val presenter = SignInPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        btSignUp.setOnClickListener { onClickSignUp() }
        btSignIn.setOnClickListener { onClickSignIn() }
    }

    override fun onClickSignUp() {
        presenter.signUp()
    }

    override fun onClickSignIn() {
        showProgress()
        presenter.signInWithEmail(
                email = etEmail.text.toString(),
                password = etPassword.text.toString()
        )
    }

    override fun onErrorSigningIn(exception: Exception) {
        handleException(exception)
    }

    override fun showSignUpScreen() {
        startActivity<SignUpView>()
    }

    override fun onUserSignedIn() {
        closeProgress()
        startActivity(intentFor<EventsView>().clearTask())
    }
}
