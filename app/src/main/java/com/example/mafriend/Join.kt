package com.example.mafriend

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mafriend.DataClass.joinPost
import com.example.mafriend.Service.ReviewService
import kotlinx.android.synthetic.main.join.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class Join: AppCompatActivity() {

    //val TAG:String = :"Join" ??
    var isExistBlank = false
    var isPWSame = false

    var join: joinPost? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.join)

        //취소버튼 클릭시 로그인페이지로
        btn_can.setOnClickListener {
            val myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)
        }

        et_num.setOnClickListener() {
            var retrofit = Retrofit.Builder()
                .baseUrl(ReviewService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create()).build()

           // var joinService: ReviewService = retrofit.create(ReviewService::class.java)
            var joinService = retrofit.create(ReviewService::class.java)

            //joinPost(val email:String?, val password:String?, val name:String?, val nickname: String?, val phoneNum:String?)
            val data = joinPost(et_id.text.toString(), et_pw.text.toString(),et_name.text.toString(),et_nick.text.toString(),et_num.text.toString())
           // joinService.post_join(data)
        }
    }
}

/*      val name = et_name.text.toString()
        val id = et_id.text.toString()
        val pw = et_pw.text.toString()
        val pw_re = et_repw.text.toString()
        val num = et_num.text.toString()
        val nick = et_nick.text.toString()
        //빈 항목이 있을 경우
        if (name.isEmpty() || id.isEmpty() || pw.isEmpty() || pw_re.isEmpty() || num.isEmpty() || nick.isEmpty()) {
            isExistBlank = true
        } else {
            if (pw == pw_re) {
                isPWSame = true
            }
        }

        if (!isExistBlank && isPWSame) {
            //가입 성공 메세지지
            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

            //입력한  값들 넘겨주기


            //로그인 화면으로
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            if (isExistBlank) {//미작성
                dialog("blank")
            } else if (!isPWSame) {//비밀번호 다름
                dialog("not same")
            }
        }


    }

    fun dialog(type: String) {
        val dialog = AlertDialog.Builder(this)
        //미작성 항목
        if (type.equals("black")) {
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("모든 정보를 입력하세요")
        }
        //입력 비밀번호 다름
        else if (type.equals("not same")) {
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("비밀번호가 다릅니다")
        }
    }*/



