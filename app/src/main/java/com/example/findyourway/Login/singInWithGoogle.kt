package com.example.findyourway.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.findyourway.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class singInWithGoogle: ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var getResult: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN )
            .requestIdToken("614910928351-vgbb4sbci7i464a3jn6c80nv7gigfan8.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient (this, gso)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()

        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            if (task.isSuccessful) {
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    Toast.makeText(this,"Sign in Failed please try again later1", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Sign in Failed please try again later", Toast.LENGTH_SHORT).show()
            }
        }

        val signInIntent = googleSignInClient.signInIntent
        getResult.launch(signInIntent)
    }


    private fun firebaseAuthWithGoogle ( idToken : String ) {
        val credential = GoogleAuthProvider.getCredential ( idToken , null )
        auth.signInWithCredential(credential)
            .addOnCompleteListener{ task ->
                if ( task.isSuccessful ) {
                    finishAffinity()
                    this.startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(this, "Sorry something went wrong", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
    }

}