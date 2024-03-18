package com.ifs21049.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21049.dinopedia.databinding.ActivityDetailDinoBinding
import com.ifs21049.dinopedia.databinding.ActivityDinoBinding

class DetailDinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoBinding
    private val dataDino = ArrayList<Dino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnUser = findViewById<ImageView>(R.id.btnUser)
        btnUser.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.rvDino.setHasFixedSize(false)
        dataDino.addAll(getListDino())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDino(): ArrayList<Family> {
        val dataName =
            resources.getStringArray(R.array.dino_species)
        val dataIcon =
            resources.obtainTypedArray(R.array.dino_icon)
        val dataDescription =
            resources.getStringArray(R.array.dino_desc)
        val dataKelompok =
            resources.getStringArray(R.array.dino_group)
        val dataKarakteristik =
            resources.getStringArray(R.array.dino_characteristics)
        val dataHabitat =
            resources.getStringArray(R.array.dino_habitat)
        val dataFood =
            resources.getStringArray(R.array.dino_food)
        val dataPanjang =
            resources.getStringArray(R.array.dino_length)
        val dataTinggi =
            resources.getStringArray(R.array.dino_height)
        val dataBobot =
            resources.getStringArray(R.array.dino_weight)

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
                dataClassification[i])
            listFamily.add (family)
        }
        return listFamily
    }
}