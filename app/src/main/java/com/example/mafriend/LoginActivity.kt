package com.example.mafriend
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mafriend.DataClass.loginPost
import com.example.mafriend.Fragment.MyPageFragment
import com.example.mafriend.Service.ReviewService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_id
import kotlinx.android.synthetic.main.activity_login.et_pw
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.join.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    var login: loginPost? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var retrofit = Retrofit.Builder()
            .baseUrl(ReviewService.API_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var loginService: ReviewService = retrofit.create(ReviewService::class.java)

        //회원가입 버튼
        btn_join.setOnClickListener {
            val myIntent = Intent(this, Join::class.java)
            startActivity(myIntent)
        }
        //로그인 버튼
        btn_login.setOnClickListener {
            //입력값 받아옴
            var email = et_id.text.toString()
            var password = et_pw.text.toString()

            /*if(et_id.text.toString() =="ma10" && et_pw.text.toString() =="1234" ){
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

                val Intent = Intent(this, MyPageFragment::class.java)
                startActivity(Intent)
                 //>> 메인페이지로 이동하도록
            }
            else Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()*/

           loginService.requestLogin(email, password).enqueue(object : Callback<loginPost>{

            override fun onFailure(call: Call<loginPost>, t: Throwable) {
                Log.e("Login", t.message.toString())
                var dialog = AlertDialog.Builder(this@LoginActivity)
                dialog.setTitle("에러")
                dialog.setMessage("호출 실패")
                dialog.show()
            }

            override fun onResponse(call: Call<loginPost>, response: Response<loginPost>) {
                login = response.body()
                Log.d("LOGIN", "email : " + login?.email)
                Log.d("LOGIN", "password : " + login?.password)
                var dialog = AlertDialog.Builder(this@LoginActivity)
                dialog.setTitle(login?.email)
                dialog.setMessage(login?.password)
                dialog.show()
                // >> 메인페이지로 이동하도록


            }

            })

        }
    }
}

/*  //로그인 후 결과를 보여줌
   fun dialog(type: String){
       var dialog = AlertDialog.Builder(this)

       if(type.equals("success")){
           dialog.setTitle("로그인 성공")
           dialog.setMessage("로그인 성공")
       }
       else if(type.equals("fail")){
           dialog.setTitle("로그인 실패")
           dialog.setMessage("입력한 아이디, 비밀번호를 확인하세요")
       }
   }

   fun Ln(v: View){
       if(et_id.text.toString() =="ma10" && et_pw.text.toString() =="1234" ){
           Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

           startActivity(Intent(this, MyPageFragment::class.java)) >> 메인페이지로 이동하도록
       }
       else Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
   }*/