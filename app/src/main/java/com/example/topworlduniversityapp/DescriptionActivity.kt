package com.example.topworlduniversityapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DescriptionActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvScore : TextView
    private lateinit var tvPhoto: ImageView
    private lateinit var tvCountry: TextView
    private lateinit var tvMotto: TextView

    companion object{
        const val name = "Name"
        const val description = "Description"
        const val photo = "@drawable/about_me"
        const val score = "100"
        const val country = "Indonesia"
        const val motto = "Martuhan-Marroha-Marbisuk"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra(name)
        val description = intent.getStringExtra(description)
        val score = intent.getStringExtra(score)
        val photo = intent.getStringExtra(photo)
        val country = intent.getStringExtra(country)
        val motto = intent.getStringExtra(motto)

        tvName = findViewById(R.id.university_name)
        tvDescription = findViewById(R.id.university_detail)
        tvScore = findViewById(R.id.university_score)
        tvCountry = findViewById(R.id.university_country)
        tvMotto = findViewById(R.id.university_motto)
        tvPhoto = findViewById(R.id.university_profile)

        tvName.text = name
        tvDescription.text = description
        tvScore.text = score
        tvCountry.text = country
        tvMotto.text = motto

        Glide.with(this)
            .load(photo)
            .into(tvPhoto)

        // For Button
        val actionShare: Button = findViewById(R.id.action_share)
        actionShare.setOnClickListener(this)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                val intent= Intent(this@DescriptionActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.action_share->{
                val description = intent.getStringExtra(description)
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, description)
                startActivity(Intent.createChooser(shareIntent, "Share link using"))
            }
        }
    }
}