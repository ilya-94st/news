package com.example.newsfor22byte.presentation.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.newsfor22byte.presentation.tools.SnackBar
import com.example.newsfor22byte.presentation.tools.toast

abstract class BaseFragment<out T : ViewBinding> : Fragment() {

    private var _binding: ViewBinding? = null
    private lateinit var mProgressDialog: Dialog


    @Suppress("UNCHECKED_CAST")
    protected val binding: T
        get() = _binding as T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater(inflater)
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract val bindingInflater: (LayoutInflater) -> ViewBinding


    fun toast(message: String) {
        requireContext().toast(message)
    }

    fun snackBar(text: String) {
        view?.let {
            SnackBar(requireView() ,text)
        }
    }

    }
