package com.example.mafriend

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
       //회원가입 페이지로 이동
        btn_join.setOnClickListener {
            val myIntent = Intent(this, Join::class.java)
            startActivity(myIntent)
        }

    }

    fun Login(v: View){
        if(et_id.text.toString() =="ma10" && et_pw.text.toString() =="1234" ){
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, Com::class.java))
        }
        else Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
    }*/
    }

}