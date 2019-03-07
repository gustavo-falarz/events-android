package com.pinecone.events.ui.splash

interface Contract {

    interface View {
        fun checkStart()
        fun showSignInScreen()
    }

    interface Presenter {
        fun checkStart()
    }
}