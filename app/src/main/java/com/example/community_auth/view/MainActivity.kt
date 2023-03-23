package com.example.community_auth.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.community_auth.R
import com.example.community_auth.model.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity: AppCompatActivity() {
    lateinit var userName: TextView
    lateinit var logout: Button
    lateinit var gClient: GoogleSignInClient
    lateinit var gOptions: GoogleSignInOptions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logout = findViewById(R.id.logout)
        userName = findViewById(R.id.userName)
        gOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gClient = GoogleSignIn.getClient(this, gOptions!!)
        val gAccount = GoogleSignIn.getLastSignedInAccount(this)
        if (gAccount != null) {
            val gName = gAccount.displayName
            userName.setText(gName)
        }
        logout.setOnClickListener(View.OnClickListener {
            gClient!!.signOut().addOnCompleteListener {
                finish()
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
        })
    }
}