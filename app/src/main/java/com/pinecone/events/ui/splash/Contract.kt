package com.pinecone.events.ui.splash

interface Contract {

    interface View {
        fun checkStart()
        fun showSignInScreen()
        fun showMainScreen()
    }

    interface Presenter {
        fun checkStart()
    }
}