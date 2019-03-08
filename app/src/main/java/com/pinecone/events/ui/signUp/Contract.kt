package com.pinecone.events.ui.signUp

import com.pinecone.events.model.Attendee
import io.reactivex.Completable

interface Contract {

    interface View {
        fun onUserCreated()

        fun onErrorSigningUp(exception: Exception)

        fun onClickSignUp()
        fun onCreateUser(completable: Completable)
    }

    interface Presenter {
        fun signUp(name: String, email: String, password: String)

        fun isAnyFieldEmpty(name: String, email: String, password: String): Boolean

        fun checkPassword(password: String): Boolean

        fun saveToBackEnd(attendee: Attendee)
        fun onUserCreated()
    }
}