package com.pinecone.events.ui.splash

import android.os.Bundle
import com.pinecone.events.R
import com.pinecone.events.ui.BaseView
import com.pinecone.events.ui.events.EventsView
import com.pinecone.events.ui.signin.SignInView
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class SplashView : BaseView(), Contract.View {
    var presenter = SplashPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkStart()
    }

    override fun checkStart() {
        presenter.checkStart()
    }

    override fun showMainScreen() {
        startActivity(intentFor<EventsView>().clearTask().newTask())
    }

    override fun showSignInScreen() {
        startActivity(intentFor<SignInView>().clearTask().newTask())
    }
}
