package com.example.quizmobileapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    var selectedChoose: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences("AuthData", Context.MODE_PRIVATE)

        val chooseStars: LinearLayout = findViewById(R.id.linearLayoutChooseStars)
        val chooseAnimals: LinearLayout = findViewById(R.id.linearLayoutChooseAnimals)
        val chooseMusic: LinearLayout = findViewById(R.id.linearLayoutChooseMusic)
        val choosePlants: LinearLayout = findViewById(R.id.linearLayoutChoosePlants)

        val loginName: String? = sharedPref.getString("login", "").toString()
        val loginTitle: TextView = findViewById(R.id.textViewLoginTitle)
        loginTitle.text = "Login:$loginName"

        chooseStars.setOnClickListener {
            selectedChoose = "stars"
            chooseStars.setBackgroundResource(R.drawable.round_background_button)
            chooseAnimals.setBackgroundResource(R.drawable.round_background)
            chooseMusic.setBackgroundResource(R.drawable.round_background)
            choosePlants.setBackgroundResource(R.drawable.round_background)
        }
        chooseAnimals.setOnClickListener {
            selectedChoose = "animals"
            chooseStars.setBackgroundResource(R.drawable.round_background)
            chooseAnimals.setBackgroundResource(R.drawable.round_background_button)
            chooseMusic.setBackgroundResource(R.drawable.round_background)
            choosePlants.setBackgroundResource(R.drawable.round_background)
        }
        chooseMusic.setOnClickListener {
            selectedChoose = "music"
            chooseStars.setBackgroundResource(R.drawable.round_background)
            chooseAnimals.setBackgroundResource(R.drawable.round_background)
            chooseMusic.setBackgroundResource(R.drawable.round_background_button)
            choosePlants.setBackgroundResource(R.drawable.round_background)
        }
        choosePlants.setOnClickListener {
            selectedChoose = "plants"
            chooseStars.setBackgroundResource(R.drawable.round_background)
            chooseAnimals.setBackgroundResource(R.drawable.round_background)
            chooseMusic.setBackgroundResource(R.drawable.round_background)
            choosePlants.setBackgroundResource(R.drawable.round_background_button)
        }
    }

    fun onClickStartQuiz(view: View) {
        if (selectedChoose.isEmpty()) {
            Toast.makeText(this, "Выберите тему викторины", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("selectedChoose", selectedChoose)
            startActivity(intent)
        }
    }

    fun onClickRating(view: View) {
        val intent = Intent(this@MainActivity, RatingActivity::class.java)
        startActivity(intent)
    }
}