package com.example.mafriend

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.com_w.*

class Com_w: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.com_w)


    btn_search.setOnClickListener {
        val myIntent = Intent(this, Search::class.java)
        startActivity(myIntent)
    }
    }
}