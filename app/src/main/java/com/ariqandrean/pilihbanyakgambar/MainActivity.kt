package com.ariqandrean.pilihbanyakgambar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var image: ArrayList<Uri>? = null
    private val GET_IMAGES_CODE = 123
    private var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = ArrayList()
        Is_pengalihGambar.setFactory { ImageView(applicationContext) }

        Btn_ambilGambar.setOnClickListener {
            ambilGambarTujuan()
        }
        btn_next.setOnClickListener {
            if (position < image!!.size - 1){
                position++
                Is_pengalihGambar.setImageURI(image!![position])
            } else {
                Toast.makeText(this, "Tidak ada gambar lagi ..", Toast.LENGTH_LONG).show()
            }
        }
        btn_Back.setOnClickListener {
            if (position > 0){
                position--
                Is_pengalihGambar.setImageURI(image!![position])
            } else {
                Toast.makeText(this, "Tidak ada gambar lagi ..", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun ambilGambarTujuan() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"),
            GET_IMAGES_CODE)
    }
}