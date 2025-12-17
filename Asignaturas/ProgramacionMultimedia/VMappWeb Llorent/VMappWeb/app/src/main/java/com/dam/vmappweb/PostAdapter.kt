package com.dam.vmappweb

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.dam.vmappweb.databinding.FilaPostBinding

class PostAdapter: RecyclerView.Adapter<PostViewHolder>() {

    private var arrayPosts=mutableListOf<Post>()

    private lateinit var listener: PostAdapterCallback

    interface PostAdapterCallback{
        fun postFav(post: Post)

    }

    fun setOnClickPostListener(listener: PostAdapterCallback){
        this.listener=listener
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val binding = FilaPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(arrayPosts[position])

        //guardar favorito
        holder.getToolbar().setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_fav ->{

                    //aqui realizo el guardado
                    listener.postFav(arrayPosts[position])

                    true
                }
                else -> {true}
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayPosts.size
    }

    fun setPosts(posts:List<Post>){
        this.arrayPosts=posts as MutableList<Post>
        notifyDataSetChanged()
    }

}