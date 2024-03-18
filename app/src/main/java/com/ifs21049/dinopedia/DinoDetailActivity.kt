package com.ifs21049.dinopedia

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21049.dinopedia.databinding.ActivityDetailDinoBinding

class DinoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDinoBinding
    private var dino: Dino? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DINO,Dino::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINO)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Dinosaurus ${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }
    }

    private fun loadData(dino: Dino) {
        binding.ivDetailIcon.setImageResource(dino.icon)
        binding.tvDetailName.text = dino.name
        binding.tvDetailDescription.text = dino.deskripsi
        binding.tvDetailKelompok.text = dino.kelompok
        binding.tvDetailCharacteristic.text = dino.karakteristik
        binding.tvDetailHabitat.text = dino.habitat
        binding.tvDetailMakanan.text = dino.makanan
        binding.tvDetailPanjang.text = dino.panjang
        binding.tvDetailTinggi.text = dino.tinggi
        binding.tvDetailBobot.text = dino.bobot
        binding.tvDetailKelemahan.text = dino.kelemahan
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}