package com.pinecone.events.ui.signUp

import com.google.firebase.auth.FirebaseAuth
import com.pinecone.events.R
import com.pinecone.events.model.Attendee
import com.pinecone.events.service.UserService
import java.lang.Exception

class SignUpPresenter(private var auth: FirebaseAuth, var view: SignUpView) : Contract.Presenter {

    override fun signUp(name: String, email: String, password: String) {
        if (isAnyFieldEmpty(name = name, password = password, email = email)) {
            view.onErrorSigningUp(Exception(view.getString(R.string.warning_empty_fields)))
            return
        }

        if (checkPassword(password)) {
            view.onErrorSigningUp(Exception(view.getString(R.string.warning_password_too_short)))
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(view) {
                    if (it.isSuccessful) {
                        auth.currentUser?.sendEmailVerification()
                                ?.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        saveToBackEnd(Attendee(firebaseId = auth.currentUser!!.uid,
                                                name = name, email = email))
                                    }
                                }
                    } else {
                        view.onErrorSigningUp(it.exception!!)
                    }
                }
    }

    override fun isAnyFieldEmpty(name: String, email: String, password: String): Boolean {
        return email.isEmpty() || password.isEmpty() || name.isEmpty()
    }

    override fun checkPassword(password: String): Boolean {
        return password.length > 5
    }

    override fun saveToBackEnd(attendee: Attendee) {
        view.onUserCreated(UserService.addAttendee(attendee))
    }

}