package com.example.vinoteka.viewModel

import androidx.lifecycle.ViewModel
import com.example.vinoteka.networking.WineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor() : ViewModel() {

    private val wineRepository: WineRepository = WineRepository()
}
