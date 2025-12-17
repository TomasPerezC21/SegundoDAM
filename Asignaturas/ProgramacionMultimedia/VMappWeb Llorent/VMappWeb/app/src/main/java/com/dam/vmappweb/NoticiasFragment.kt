package com.dam.vmappweb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dam.vmappweb.bbdd.PostBBDD
import com.dam.vmappweb.bbdd.PostFavoritos
import com.dam.vmappweb.databinding.NoticiasFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoticiasFragment: Fragment() {
    private var NOTICIAS_CATEGORY_ID=15
    private lateinit var adapterPost: PostAdapter
    //viewbinding
    private var _binding: NoticiasFragmentBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= NoticiasFragmentBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNoticias.layoutManager= LinearLayoutManager(context)
        adapterPost= PostAdapter()
        binding.rvNoticias.adapter=adapterPost
        recuperarPosts()
        adapterPost.setOnClickPostListener(object: PostAdapter.PostAdapterCallback{
            override fun postFav(post: Post) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val database= PostBBDD.DatabaseBuilder.getInstance(requireContext())
                    database.favDAO().insert(PostFavoritos(
                        post.id,
                        post.date,
                        post.link,
                        post.title.rendered,
                        post.content.rendered
                    ))
                }
            }

        })
    }

    fun recuperarPosts(){
        lifecycleScope.launch(Dispatchers.IO) {
            val posts= WordpressRetrofitClient.api.getPosts(NOTICIAS_CATEGORY_ID)
            if (posts.isNotEmpty()){
                withContext(Dispatchers.Main){
                    adapterPost.setPosts(posts)
                }
            }
        }
    }
}
