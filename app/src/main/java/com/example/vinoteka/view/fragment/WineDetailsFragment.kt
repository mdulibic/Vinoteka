package com.example.vinoteka.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.vinoteka.R
import com.example.vinoteka.databinding.FragmentHomeBinding
import com.example.vinoteka.databinding.FragmentWineDetailsBinding
import com.example.vinoteka.viewModel.ExchangeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WineDetailsFragment : BaseFragment(R.layout.fragment_wine_details) {

    private var _binding: FragmentWineDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ExchangeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWineDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ExchangeViewModel::class.java]
        observeLiveData()
        setOnClickClickListeners()
    }

    override fun getToolbar(): Toolbar? {
        TODO("Not yet implemented")
    }

    private fun observeLiveData() {
    }

    private fun setOnClickClickListeners() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
