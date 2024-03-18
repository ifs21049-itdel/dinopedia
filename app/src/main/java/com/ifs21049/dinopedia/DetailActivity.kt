package com.ifs21049.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ifs21049.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var family: Family? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnDino = findViewById<TextView>(R.id.btnDino)
        btnDino.setOnClickListener {
            val intent = Intent(this, DinoActivity::class.java)
            startActivity(intent)
        }

        family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILY,Family::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILY)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (family != null) {
            supportActionBar?.title = "Awan ${family!!.name}"
            loadData(family!!)
        } else {
            finish()
        }
    }

    private fun loadData(family: Family) {
        binding.ivDetailIcon.setImageResource(family.icon)
        binding.tvDetailName.text = family.name
        binding.tvDetailDescription.text = family.deskripsi
        binding.tvDetailPeriod.text = family.periode
        binding.tvDetailCharacteristic.text = family.karakteristik
        binding.tvDetailHabitat.text = family.habitat
        binding.tvDetailPerilaku.text = family.perilaku
        binding.tvDetailKlasifikasi.text = family.klasifikasi
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
        const val EXTRA_FAMILY = "extra_family"
    }
}