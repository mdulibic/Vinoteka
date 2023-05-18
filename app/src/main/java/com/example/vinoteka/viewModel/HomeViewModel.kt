package com.example.vinoteka.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinoteka.model.Maltster
import com.example.vinoteka.model.Sort
import com.example.vinoteka.model.Wine
import com.example.vinoteka.networking.WineRepository
import com.example.vinoteka.networking.model.AddWineRequest
import com.example.vinoteka.networking.model.WineResponse
import com.example.vinoteka.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val wineRepository: WineRepository,
) : ViewModel() {

    private val _wineListSuccess = MutableLiveData<List<Wine>>()
    val wineListSuccess: LiveData<List<Wine>>
        get() = _wineListSuccess

    private val _sortsSuccess = MutableLiveData<List<Sort>>()
    val sortsSuccess: LiveData<List<Sort>>
        get() = _sortsSuccess

    fun getAdminEmail() = sessionManager.adminEmail

    fun getWines() = viewModelScope.launch {
        val list = wineRepository.getWines().body()?.map {
            it.toWine()
        }
        _wineListSuccess.value = list!!
    }

    fun getSorts() = viewModelScope.launch {
        val list = wineRepository.getSorts().body()
        _sortsSuccess.value = list!!
    }

    fun addWine(wine: AddWineRequest) = viewModelScope.launch { wineRepository.addWine(wine = wine) }

    fun deleteWine(id: String) = viewModelScope.launch { wineRepository.deleteWine(id = id) }

    private fun WineResponse.toWine(): Wine {
        return Wine(
            id = this.id,
            name = this.name,
            harvest = this.harvest,
            alcoholPercentage = this.alcoholPercentage,
            maltster = this.maltster.toMaltster(),
            quality = this.quality,
            vineyard = this.vineyard,
            temperatureOfServing = this.temperatureOfServing,
            gastroRecommendation = this.gastroRecommendation,
            description = this.description,
            price = this.price,
            sort = this.sort,
        )
    }

    private fun String.toMaltster(): Maltster {
        return when (this) {
            "dry" -> Maltster.DRY
            "semi_dry" -> Maltster.SEMI_DRY
            "sweet" -> Maltster.SWEET
            "semi_sweet" -> Maltster.SEMI_SWEET
            else -> {
                Timber.d("Unknown maltster type $this")
                Maltster.UNKNOWN
            }
        }
    }
}
