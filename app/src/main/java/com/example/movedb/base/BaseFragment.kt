package com.example.movedb.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow

abstract class BaseFragment <T : ViewBinding> : Fragment() {
    private var _binding: T? = null
    val binding get() = _binding!!

    open val viewModel: BaseViewModel? get() = null

    abstract fun initBindingObject(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initBindingObject(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}


