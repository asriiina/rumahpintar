package com.example.rumahpintar.langganan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rumahpintar.MainActivity
import com.example.rumahpintar.langganan.adapter.PackageAdapter
import com.example.rumahpintar.langganan.model.Package
import com.example.rumahpintar.R
import kotlinx.android.synthetic.main.item_package.*

class LanggananActivity : AppCompatActivity() {

    private lateinit var recyclerPackage: RecyclerView
    private lateinit var adapterPackage: PackageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_langganan)


        val dummyData = arrayListOf<Package.Subscribe>()
        dummyData.add(Package.Subscribe(null, "Matematika Dasar", 12500, 4.5, "90 soal", "Rp 150.000"))
        dummyData.add(Package.Subscribe(null, "Ilmu Pengetahuan Alam (IPA)", 12500, 4.5, "45 soal", "Rp 250.000"))
        dummyData.add(Package.Subscribe(null, "Bahasa Indonesia", 12500, 4.5, "30 soal", "Rp 125.000"))
        dummyData.add(Package.Subscribe(null, "Bahasa Inggris", 12500, 4.5, "50 soal", "Rp 100.000"))
        dummyData.add(Package.Subscribe(null, "Ilmu Pengetahuan Sosial (IPS)", 12500, 4.5, "40 soal", "Rp 150.000"))

        recyclerPackage = findViewById(R.id.recycler_package_main)
        recyclerPackage.layoutManager = GridLayoutManager(this, 2)
        // decoration (nanti aja)
        adapterPackage = PackageAdapter(this, dummyData)
        recyclerPackage.adapter = adapterPackage
        
        adapterPackage.setOnItemClickListener(object : PackageAdapter.OnItemClickListener {
            override fun onItemClick(model: Package.Subscribe) {
                Toast.makeText(this@LanggananActivity, "${model.name}", Toast.LENGTH_SHORT).show()
                val manageDetailIntent = Intent(this@LanggananActivity, DetailLanggananActivity::class.java)
                    .apply {
                        putExtra(DetailLanggananActivity.EXTRA_NAME, model.name)
                        putExtra(DetailLanggananActivity.EXTRA_QUESTION, model.question)
                        putExtra(DetailLanggananActivity.EXTRA_PRICE, model.price)
                    }
                startActivity(manageDetailIntent)
            }
        })

    }
}