package com.example.mafriend.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mafriend.R

class CommunityDetailFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //var root = inflater.inflate(R.layout.fragment_review_list, container, false)  ??
        var root = inflater.inflate(R.layout.com, container, false)

        //


        return root
    }
}