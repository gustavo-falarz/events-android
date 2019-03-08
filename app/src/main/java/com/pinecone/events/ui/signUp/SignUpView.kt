package com.pinecone.events.ui.signUp

import android.os.Bundle
import com.pinecone.events.R
import com.pinecone.events.ui.BaseView
import com.pinecone.events.ui.signin.SignInView
import io.reactivex.Completable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor

class SignUpView : BaseView(), Contract.View {
    var presenter = SignUpPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btSignUp.setOnClickListener { onClickSignUp() }
    }

    override fun onUserCreated() {
        startActivity(intentFor<SignInView>().clearTask())
    }

    override fun onCreateUser(completable: Completable) {
        completable.applySchedulers().subscribeBy(
                onError = {
                    handleException(it)
                },
                onComplete = {
                    presenter.onUserCreated()
                    closeProgress()
                }
        )
    }

    override fun onErrorSigningUp(exception: Exception) {
        handleException(exception)
    }

    override fun onClickSignUp() {
        showProgress()
        presenter.signUp(email = etEmail.text.toString(),
                password = etPassword.text.toString(), name = etName.text.toString())
    }
}
