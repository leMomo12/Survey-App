package com.mnowo.surveyapp.data.remote.firebase

import android.util.Log.d
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mnowo.surveyapp.R
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDb @Inject constructor(){

    suspend fun signInWithEmailAndPassword(email: String, password: String) : Boolean {
        var status = false
        d("Login", "here $email, $password")
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                status = true
                d("Login", "Success")
            }
        }.await()

        return status
    }

    suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean {
        var status = false
        d("Register", "Email: ${email.toString()}")
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                status = true
            }
        }.await()
        return status
    }



    suspend fun signOut() {
        Firebase.auth.signOut()
    }


}