package com.example.vinoteka.viewModel

import androidx.lifecycle.ViewModel
import com.example.vinoteka.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {



}