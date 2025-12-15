package com.dam.vicentemedinaapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dam.vicentemedinaapp.databinding.FilaPostBinding

class PostAdapter: RecyclerView.Adapter<PostViewHolder>() {

    private var arrayPost = mutableListOf<Post>(



    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val binding= FilaPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

         holder.bind(arrayPost[position])

    }

    override fun getItemCount(): Int {
        return arrayPost.size
    }

    fun setPosts(posts:List<Post>){
        this.arrayPost=posts as MutableList<Post>
        notifyDataSetChanged()
    }

}