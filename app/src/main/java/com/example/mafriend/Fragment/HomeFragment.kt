package com.example.mafriend.Fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avatwin.Converter.LocalDateTimeConverter
import com.example.mafriend.Adapter.ReviewAdapter
import com.example.mafriend.DataClass.boardGetBody
import com.example.mafriend.R
import com.example.mafriend.Service.ReviewService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_review_list.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

class HomeFragment: Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_review_list, container, false)

        //생성 페이지로 이동
        root.btn_write.setOnClickListener {
            val fragmentA = ReviewRegisterFragment()
            val bundle = Bundle()
            fragmentA.arguments=bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.container,fragmentA)
            transaction.replace(R.id.container, fragmentA.apply { arguments = bundle }).addToBackStack(null)
            transaction.commit()
        }


        //layoutmanager설정
        val layoutManager = LinearLayoutManager(requireActivity())
        root.recyclerview_review.layoutManager = layoutManager
        lateinit var adapter: ReviewAdapter

        //리뷰 목록 가져오기
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter()).create()

        var retrofit = Retrofit.Builder()
                .baseUrl(ReviewService.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build()

        var apiService = retrofit.create(ReviewService::class.java)
        var tests = apiService.get_review()
        tests.enqueue(object : Callback<List<boardGetBody>> {
            override fun onResponse(call: Call<List<boardGetBody>>, response: Response<List<boardGetBody>>) {
                if (response.isSuccessful) {
                    var mList = response.body()!!
                    Log.e("team",mList.toString())
                    adapter = ReviewAdapter(mList)
                    root.recyclerview_review.adapter= adapter

                    adapter.setItemClickListener(object : ReviewAdapter.ItemClickListener {
                        override fun onClick(view: View, position: Int) {


                            Log.e("ddd", "Ss")

                            val fragmentA = ReviewDetailFragment()
                            val bundle = Bundle()
                            bundle.putInt("reviewNum", mList[position].pk!!)
                            fragmentA.arguments=bundle
                            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                            transaction.add(R.id.container,fragmentA)
                            transaction.replace(R.id.container, fragmentA.apply { arguments = bundle }).addToBackStack(null)
                          transaction.commit()

                        }
                    })
                } }

            override fun onFailure(call: Call<List<boardGetBody>>, t: Throwable) {
                Log.e("team", "OnFailuer+${t.message}")
            } })

        return root
    }
}