package com.example.mafriend.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mafriend.Adapter.ReviewAdapter
import com.example.mafriend.DataClass.boardGetBody
import com.example.mafriend.R
import com.example.mafriend.Service.ReviewService
import kotlinx.android.synthetic.main.fragment_review_list.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment: Fragment() {
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

        var retrofit = Retrofit.Builder()
                .baseUrl(ReviewService.API_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
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

/*
                            Log.e("ddd", "Ss")
                            val teamaBody =mList.list[position]
                            App.prefs.teamSeq=teamaBody.teamSeq.toString()
                            Log.e("teamSq",App.prefs.teamSeq.toString())
                            val fragmentA = TeamMainFragment()
                            val bundle = Bundle()
                            bundle.putString("teamName",teamaBody.teamName.toString())
                         //   bundle.putString("teamMaker",teamaBody.user.userId)
                            fragmentA.arguments=bundle
                            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                            transaction.add(R.id.container,fragmentA)
                            transaction.replace(R.id.container, fragmentA.apply { arguments = bundle }).addToBackStack(null)
                          transaction.commit()  */

                        }
                    })
                } }

            override fun onFailure(call: Call<List<boardGetBody>>, t: Throwable) {
                Log.e("team", "OnFailuer+${t.message}")
            } })

        return root
    }
}