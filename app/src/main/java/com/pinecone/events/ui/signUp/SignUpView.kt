package com.pinecone.events.ui.signUp

import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.pinecone.events.R
import com.pinecone.events.ui.BaseView
import com.pinecone.events.ui.signin.SignInView
import io.reactivex.Completable
import kotlinx.android.synthetic.main.activity_new_user.*
import org.jetbrains.anko.startActivity
import java.lang.Exception

class SignUpView : BaseView(), Contract.View {
    var presenter = SignUpPresenter(FirebaseAuth.getInstance(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        btSignUp.setOnClickListener {}
    }

    override fun onUserCreated(completable: Completable) {
        startActivity<SignInView>()
    }

    override fun onErrorSigningUp(exception: Exception) {
        handleException(exception)
    }


    override fun onClickSignUp() {
        presenter.signUp(name = etName.text.toString(),
                email = etEmail.text.toString(),
                password = etPassword.text.toString())
    }
}
