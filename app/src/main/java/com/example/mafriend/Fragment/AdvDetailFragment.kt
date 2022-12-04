package com.example.mafriend.Fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avatwin.Converter.LocalDateTimeConverter
import com.example.mafriend.Adapter.CommentAdapter
import com.example.mafriend.DataClass.boardGetBody
import com.example.mafriend.DataClass.commentGetBody
import com.example.mafriend.DataClass.commentPostBody
import com.example.mafriend.R
import com.example.mafriend.Service.ReviewService
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.*
import kotlinx.android.synthetic.main.fragment_review.*
import kotlinx.android.synthetic.main.fragment_review.view.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.LocalDateTime

class AdvDetailFragment(): Fragment() {
    var pk=0
    var author=0
    var category=""
    init{ instance = this }

    lateinit var adapter: CommentAdapter
    companion object{
        private var instance: AdvDetailFragment? = null
        fun getInstance(): AdvDetailFragment?
        { return instance  }}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_adv, container, false)
        val layoutManager = LinearLayoutManager(requireActivity())
        root.recyclerview_comment.layoutManager = layoutManager

        val bundle = arguments

        if(bundle != null) {
            pk= bundle.getInt("reviewNum")!!
            category=bundle.getString("reviewCategory")!!

        }
        //init
        initBoard()

        /*
        //수정
        root.board_update.setOnClickListener {
            val fragmentA = BoardUpdateFragment()
            val bundle = Bundle()
            fragmentA.arguments=bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, fragmentA)
            transaction.replace(R.id.container, fragmentA.apply { arguments = bundle }).addToBackStack(null)
            transaction.commit()
        }

        //삭제
        root.board_delete.setOnClickListener{
            deleteBoard()
        }*/

        //댓글
        root.comment_button.setOnClickListener{
            registerComment(root.comment_text.text.toString())
        }

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initBoard(){

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter()).create()


        //채널별 게시글 가져오기
        var retrofit = Retrofit.Builder()
                .baseUrl(ReviewService.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        var apiService = retrofit.create(ReviewService::class.java)

        apiService.get_reviewById(pk).enqueue(object : Callback<boardGetBody> {
            override fun onResponse(call: Call<boardGetBody>, response: Response<boardGetBody>) {
                if (response.isSuccessful) {
                    var mList = response.body()!!
                    Log.e("board",mList.toString())

                    //날짜
                    var date = mList.created_at.toString().substring(0,10)+" "+mList.created_at.toString().substring(11,16)
                    board_detail_date.setText(date)
                    /////////동네 정보 메인페이지에서 name 번들로 받아오기
                    board_category.setText(category)
                    //제목
                    board_detail_title.setText(mList.title)
                    //이미지
                    //Glide.with(view!!).load(mList.data.userImage).into(board_detail_image)
                    //작성자
                    board_detail_name.setText(mList.nickname)
                    //author=mList.name!!
                    //내용
                    board_detail_content.setText(mList.content)
                    //댓글 리스트
                    adapter = CommentAdapter()

                    adapter.items=mList.post_comment
                    recyclerview_comment.adapter= adapter

                } }

            override fun onFailure(call: Call<boardGetBody>, t: Throwable) {
                Log.e("team", "OnFailuer+${t.message}")
            } })

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun registerComment(content:String){
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter()).create()
        var retrofit = Retrofit.Builder()
                .baseUrl(ReviewService.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create()).build()

        var apiService = retrofit.create(ReviewService::class.java)

        val request =commentPostBody(pk,2,content)
        apiService.post_comment(request).enqueue(object : Callback<commentGetBody> {
            override fun onResponse(call: Call<commentGetBody>, response: Response<commentGetBody>) {
                val result = response.body()
                adapter.addItem(result!!)
                comment_text.setText("")
            }

            override fun onFailure(call: Call<commentGetBody>, t: Throwable) {
                Log.e("COMMENT", "OnFailuer+${t.message}")
            }
        })

    }

}

