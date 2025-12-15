package com.dam.vicentemedinaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dam.vicentemedinaapp.databinding.NoticiasFragmentBinding

class NoticiasFragment: Fragment() {

    private val NOTICIAS_CATEGORY_ID = 15

    private lateinit var adapterPost: PostAdapter

    //viewBinding
    private var _binding: NoticiasFragmentBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = NoticiasFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNoticias.layoutManager = LinearLayoutManager(context)
        adapterPost = PostAdapter()
        binding.rvNoticias.adapter=adapterPost
        

    }



}