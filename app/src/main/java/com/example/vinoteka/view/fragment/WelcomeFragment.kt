package com.example.vinoteka.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.vinoteka.R
import com.example.vinoteka.databinding.FragmentWelcomeBinding
import com.example.vinoteka.utils.SessionManager
import com.example.vinoteka.viewModel.WelcomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment(R.layout.fragment_welcome) {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]
        observeLiveData()
        setOnClick()
    }

    override fun getToolbar(): Toolbar? {
       return null
    }

    private fun observeLiveData() {
        viewModel.emailValidationFail.observe(viewLifecycleOwner) {
            binding.tvEmailWarning.visibility = View.VISIBLE
        }

        viewModel.emailValidationSuccess.observe(viewLifecycleOwner) {
            binding.tvEmailWarning.visibility = View.GONE
            svm.navigate(WelcomeFragmentDirections.actionWelcomeFragmentToNavigationHome())
        }
    }

    private fun setOnClick() {
        binding.btnProceed.setOnClickListener {
            viewModel.validateEmail(binding.etEmail.text.trim().toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}