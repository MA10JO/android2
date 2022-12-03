package com.example.mafriend.Service

import com.example.mafriend.DataClass.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ReviewService {
    companion object{
        public val API_URL = "http://10.0.2.2:8000/"
    }


    //게시판 등록
    @POST("review/")
    fun post_review(@Body  request:boardPostBody

    ):Call<boardGetBody>

    //댓글 등록
    @POST("review/comment/")
    fun post_comment(@Body  request:commentPostBody

    ):Call<commentGetBody>


    //게시판 단건 조회
    @GET("review/{reviewId}/")
    fun get_reviewById(@Path("reviewId") reivewId:Int):Call<boardGetBody>

    //카테고리 목록 조회
    @GET("review/categories")
    fun get_categories():Call<List<categories>>

    //카테고리 별 게시글 조회
    @GET("review/categories/{pk}/")
    fun get_reviewByCt(@Path("pk") pk:Int):Call<categoryBody>

    //게시판 목록 조회
    @GET("review/")
    fun get_review():Call<List<boardGetBody>>

}

