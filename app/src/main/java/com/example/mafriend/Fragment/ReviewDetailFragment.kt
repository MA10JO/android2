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
import com.example.mafriend.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.LocalDateTime

class ReviewDetailFragment(): Fragment() {

    init{ instance = this }

    companion object{
        private var instance: ReviewDetailFragment? = null
        fun getInstance(): ReviewDetailFragment?
        { return instance  }}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_review_register, container, false)
        val layoutManager = LinearLayoutManager(requireActivity())
      /*  root.recyclerview_comment.layoutManager = layoutManager

        //init
        initBoard()
        root.emoticon_group.isVisible=false
        root.emoticon_add_button.setOnClickListener{
            root.emoticon_group.isVisible=true
            //기본 아바타 이모티콘 설정해주기
            setEmoticon()
            //이모티콘 클릭 시 채팅에
            selectEmoticon()
        }

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
        }

        //댓글
        root.comment_button.setOnClickListener{
            registerComment(root.comment_text.text.toString(),selectEmoticonUrl)
        }
*/
        return root
    }
/*
    @RequiresApi(Build.VERSION_CODES.O)
    fun initBoard(){

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter()).create()


        //채널별 게시글 가져오기
        val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
        var retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BoardService.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        var apiService = retrofit.create(BoardService::class.java)
        var tests = apiService.get_BoardById(App.prefs.boardSeq!!.toLong())
        tests.enqueue(object : Callback<boardGetBodyById> {
            override fun onResponse(call: Call<boardGetBodyById>, response: Response<boardGetBodyById>) {
                if (response.isSuccessful) {
                    var mList = response.body()!!
                    Log.e("board",mList.data.toString())

                    //날짜
                    var date = mList.data.createDate.toString().substring(0,10)+" "+mList.data.createDate.toString().substring(11,16)
                    Log.i("board",date)
                    board_detail_date.setText(date)
                    //제목
                    board_detail_title.setText(mList.data.title)
                    //이미지
                    //board_detail_image
                    Log.i("boardDetail",mList.data.userImage.toString())
                    Log.i("boardDetail2",mList.data.userImg.toString())
                    Glide.with(view!!).load(mList.data.userImage).into(board_detail_image)
                    //별명
                    board_detail_name.setText(mList.data.username)
                    //내용
                    board_detail_content.setText(mList.data.content)
                    //댓글 리스트
                    adapter = commentAdapter()
/*
                    for(i:commentBody in mList.data.comments){
                        if(i.username==App.prefs.userId){
                            comment_mores.visibility=View.VISIBLE
                        }else{
                            comment_mores.visibility=View.GONE
                        }
                    }*/

                    adapter.items=mList.data.comments
                    recyclerview_comment.adapter= adapter
                } }

            override fun onFailure(call: Call<boardGetBodyById>, t: Throwable) {
                Log.e("team", "OnFailuer+${t.message}")
            } })

    }


    fun registerComment(content:String,selectEmoticonUrl:String){
        val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
        var retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(CommentService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create()).build()

        var apiService = retrofit.create(CommentService::class.java)

        apiService.post_comment(App.prefs.boardSeq!!.toLong(),content,selectEmoticonUrl).enqueue(object : Callback<commentGetBody> {
            override fun onResponse(call: Call<commentGetBody>, response: Response<commentGetBody>) {
                val result = response.body()
                //Log.e("성공",result.toString())
                emoticon_group.visibility=View.GONE
                adapter.addItem(result!!.data)
                comment_text.setText("")
            }

            override fun onFailure(call: Call<commentGetBody>, t: Throwable) {
                Log.e("COMMENT", "OnFailuer+${t.message}")
            }
        })

    }*/

}

