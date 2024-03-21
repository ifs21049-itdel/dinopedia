package com.ifs21049.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21049.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataFamily = ArrayList<Family>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnUser = findViewById<ImageView>(R.id.btnUser)
        btnUser.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.rvFamily.setHasFixedSize(false)
        dataFamily.addAll(getListFamily())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListFamily(): ArrayList<Family> {
        val dataName =
            resources.getStringArray(R.array.family_names)
        val dataIcon =
            resources.obtainTypedArray(R.array.family_icon)
        val dataDescription =
            resources.getStringArray(R.array.family_desc)
        val dataPeriod =
            resources.getStringArray(R.array.family_period)
        val dataPhysical =
            resources.getStringArray(R.array.family_physical)
        val dataHabitat =
            resources.getStringArray(R.array.family_habitat)
        val dataBehavior =
            resources.getStringArray(R.array.family_behavior)
        val dataClassification =
            resources.getStringArray(R.array.family_classification)
        val dataStartIndex =
            resources.getStringArray(R.array.start_dino_array)
        val dataEndIndex =
            resources.getStringArray(R.array.end_dino_array)

        val listFamily = ArrayList<Family>()
        for (i in dataName.indices) {
            val family = Family(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDescription[i],
                dataPeriod[i],
                dataPhysical[i],
                dataHabitat[i],
                dataBehavior[i],
                dataClassification[i],
                dataStartIndex[i].toInt(),
                dataEndIndex[i].toInt())
            listFamily.add (family)
        }
        return listFamily
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) { binding.rvFamily.layoutManager =
            GridLayoutManager(this, 2)
        } else {
            binding.rvFamily.layoutManager =
                LinearLayoutManager(this)
        }
        val listFamilyAdapter = ListFamilyAdapter(dataFamily)
        binding.rvFamily.adapter = listFamilyAdapter
        listFamilyAdapter.setOnItemClickCallback(object :
            ListFamilyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Family) {
                showSelectedFamily(data)
            }
        })
    }

    private fun showSelectedFamily(family: Family) {
        val intentWithData = Intent(
            this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra (DetailActivity.EXTRA_FAMILY, family)
        startActivity(intentWithData)
    }
}