package com.example.topworlduniversityapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity: AppCompatActivity() {
    private lateinit var rvUniversities: RecyclerView
    private val list = ArrayList<University>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvUniversities = findViewById(R.id.rv_university)
        rvUniversities.setHasFixedSize(true)

        list.addAll(getListUniversities())
        showRecycleList()
    }
    private fun getListUniversities(): ArrayList<University> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataScore = resources.getStringArray(R.array.data_score)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataCountry = resources.getStringArray(R.array.data_country)
        val dataMotto = resources.getStringArray(R.array.data_motto)
        val listUniversity = ArrayList<University>()
        for (i in dataName.indices) {
            val university = University(dataName[i], dataDescription[i], dataScore[i], dataPhoto[i],dataCountry[i],dataMotto[i])
            listUniversity.add(university)
        }
        return listUniversity
    }

    private fun showRecycleList(){
        rvUniversities.layoutManager = LinearLayoutManager(this)
        val listUnivAdapter = ListUniversityAdapter(list)
        rvUniversities.adapter = listUnivAdapter

        listUnivAdapter.setOnItemClickCallback(object :ListUniversityAdapter.OnItemClickCallback{
            override fun onItemClicked(data: University) {
                showSelectedUniversity(data)
            }
        })
    }
    private fun showSelectedUniversity(univ: University) {
        val intent = Intent(this@HomeActivity, DescriptionActivity::class.java)
        intent.putExtra(DescriptionActivity.name,univ.name)
        intent.putExtra(DescriptionActivity.description,univ.description)
        intent.putExtra(DescriptionActivity.photo,univ.photo)
        intent.putExtra(DescriptionActivity.score,univ.score)
        intent.putExtra(DescriptionActivity.country,univ.country)
        intent.putExtra(DescriptionActivity.motto,univ.motto)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_page, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about->{
                val intent= Intent(this@HomeActivity, AboutPageActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}