package com.example.harrypoterrz

import ApiResponse
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    private lateinit var characterImage: ImageView
    private lateinit var characterName: TextView
    private lateinit var characterHouse: TextView
    private lateinit var characterBirth: TextView
    private lateinit var characterStudent: TextView
    private lateinit var characterAncestry: TextView
    private lateinit var characterActor: TextView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        characterImage = findViewById(R.id.imageView)
        characterName = findViewById(R.id.textViewName)
        characterHouse = findViewById(R.id.textViewHouse)
        characterBirth = findViewById(R.id.textViewBirth)
        characterStudent = findViewById(R.id.textViewStudent)
        characterAncestry = findViewById(R.id.textViewAncestry)
        characterActor = findViewById(R.id.textViewActor)
        toolbar = findViewById(R.id.toolbar_details)

        setUpActionBar()

        val character = intent.getSerializableExtra("character") as? ApiResponse

        character?.let {
            if (!it.image.isNullOrBlank()) {
                Picasso.get().load(it.image).into(characterImage)
            } else {
                characterImage.setImageResource(R.drawable.default_image)
            }

            characterName.text = it.name ?: "No information"
            characterHouse.text = it.house ?: "No information"
            characterBirth.text = it.dateOfBirth ?: "No information"
            characterStudent.text = it.hogwartsStudent?.toString() ?: "No information"
            characterAncestry.text = it.ancestry ?: "No information"
            characterActor.text = it.actor ?: "No information"
        }
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        toolbar.setNavigationOnClickListener{onBackPressed()}
    }
}
