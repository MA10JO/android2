package com.example.mafriend

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.mypage.*

class Mypage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage)

        //뒤로가기 >
//        btn_back.setOnClickListener {
//            val myIntent = Intent(this, 어떤페이지로 이동::class.java)
//            startActivity(myIntent)

        //로그아웃 > 로그인페이지로 이동
        btn_signout.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            ///소연
            startActivity(myIntent)
        }
    }



}