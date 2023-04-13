package com.example.quizmobileapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class ResultActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPrefL: SharedPreferences
    lateinit var editorL: SharedPreferences.Editor

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val quizResult: TextView = findViewById(R.id.textViewQuizResult)
        var result: Int? = intent.getIntExtra("correctAnswers", 0)
        if (result != null) {
            result = result * 100 / 4
            quizResult.text = "" + result.toString() + "%"
        }

        sharedPref = getSharedPreferences("RatingData", Context.MODE_PRIVATE)
        editor = sharedPref.edit()
        var rating: Int? = (result?.plus(sharedPref.getInt("rating", 0)))?.div(2)
        if (result != null) {
            editor.putInt("rating", result)
        }
        editor.commit()

        sharedPrefL = getSharedPreferences("AuthData", Context.MODE_PRIVATE)
        editorL = sharedPrefL.edit()
        val database = FirebaseDatabase.getInstance()
        sharedPrefL.getString("login", "user")?.let { database.getReference(it) }?.setValue(rating)
    }

    fun onClickBack(view: View) {
        finish()
    }
}