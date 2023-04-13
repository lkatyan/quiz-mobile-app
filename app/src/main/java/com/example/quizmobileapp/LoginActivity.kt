package com.example.quizmobileapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPref = getSharedPreferences("AuthData", Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    override fun onStart() {
        super.onStart()
        findViewById<EditText>(R.id.editTextLogin).setText(sharedPref.getString("login", ""))
        findViewById<EditText>(R.id.editTextPassword).setText(sharedPref.getString("password", ""))
    }

    fun onClickButtonAuthorization(view: View) {
        if (findViewById<EditText>(R.id.editTextLogin).text.toString()=="" || findViewById<EditText>(R.id.editTextPassword).text.toString()=="") {
            findViewById<TextView>(R.id.textViewWarning).text = "text me all"
        } else {
            editor.putString("login", findViewById<EditText>(R.id.editTextLogin).text.toString())
            editor.putString("password", findViewById<EditText>(R.id.editTextPassword).text.toString())
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}