package com.example.quizmobileapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import org.w3c.dom.Text

class RatingActivity : AppCompatActivity() {
    lateinit var textViewRatingList: TextView
    var ratingMutableMap: MutableMap<String, String> = mutableMapOf("user" to "47")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        textViewRatingList = findViewById(R.id.textViewRatingList)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        onChangeListener(myRef)
    }

    private fun onChangeListener(dbRef: DatabaseReference) {
        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child: DataSnapshot in snapshot.children) {
                    ratingMutableMap[child.key.toString()] = child.value.toString()
                }
                var ratingCount: Int = 1
                ratingMutableMap.forEach{
                    textViewRatingList.append("\n" + ratingCount.toString() + ". " + it.key + ": " + it.value)
                    ratingCount++
                }
            /*textViewRatingList.append(snapshot.childrenCount.toString() + snapshot.getValue())*/
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}