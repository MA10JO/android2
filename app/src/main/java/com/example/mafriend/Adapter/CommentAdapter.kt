package com.example.mafriend.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.mafriend.DataClass.commentGetBody
import com.example.mafriend.R
import kotlinx.android.synthetic.main.item_board_list.view.*
import kotlinx.android.synthetic.main.item_board_list.view.board_title
import kotlinx.android.synthetic.main.item_comment.view.*


class CommentAdapter():RecyclerView.Adapter<CommentAdapter.ViewHolder>(){
    var items= ArrayList<commentGetBody>()
        interface ItemClickListener {
            fun onClick(view: View, position: Int)
        }

    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener

    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CommentAdapter.ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentAdapter.ViewHolder, position: Int) {
        val item=items[position]
        holder.setItem(item)

       /* val BoardDetailFragment = BoardDetailFragment.getInstance()

        holder.itemView.comment_mores.setOnClickListener {
            BoardDetailFragment!!.clickMenu(item)

        }*/
    }

    override fun getItemCount()=items.size
    fun addItem(item: commentGetBody){
        items.add(item)
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun setItem(item:commentGetBody){
            itemView.comment_username.text = item.author_userid
            itemView.comment_content.text = item.content
           // var a= URLDecoder.decode(item.image!!.substring(ApiService.API_URL.length+1), "utf-8");


        }
    }

}