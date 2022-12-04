package com.example.mafriend.Service

import com.example.mafriend.DataClass.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface AdvService {
    companion object{
        public val API_URL = "http://10.0.2.2:8000/"
    }


    //게시판 등록
    @POST("adv/")
    fun post_adv(@Body request:advPostBody

    ):Call<boardGetBody>

    //댓글 등록
    @POST("adv/comment/")
    fun post_comment(@Body  request:commentPostBody

    ):Call<commentGetBody>


    //게시판 단건 조회
    @GET("adv/{advId}/")
    fun get_advById(@Path("radvId") advId:Int):Call<boardGetBody>

    //카테고리 목록 조회
    @GET("adv/categories")
    fun get_categories():Call<List<categories>>

    //카테고리 별 게시글 조회
    @GET("adv/categories/{pk}/")
    fun get_advByCt(@Path("pk") pk:Int):Call<categoryBody>

    //게시판 목록 조회
    @GET("adv/")
    fun get_adv():Call<List<boardGetBody>>

}

