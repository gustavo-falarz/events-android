package com.pinecone.events.ui.splash

class SplashPresenter(var view: SplashView) : Contract.Presenter {

    override fun checkStart() {
        view.showSignInScreen()
    }
}