package com.example.vinoteka.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinoteka.model.Maltster
import com.example.vinoteka.model.Sort
import com.example.vinoteka.model.Wine
import com.example.vinoteka.networking.WineRepository
import com.example.vinoteka.networking.model.WineRequest
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

    private val _wineDetailsSuccess = MutableLiveData<Wine>()
    val wineDetailsSuccess: LiveData<Wine>
        get() = _wineDetailsSuccess

    private val _wineDetailsUpdateSuccess = MutableLiveData<Unit>()
    val wineDetailsUpdateSuccess: LiveData<Unit>
        get() = _wineDetailsUpdateSuccess

    private val _deleteWineSuccess = MutableLiveData<Unit>()
    val deleteWineSuccess: LiveData<Unit>
        get() = _deleteWineSuccess

    fun getAdminEmail() = sessionManager.adminEmail

    fun updateWineDetails(id: String, wine: WineRequest) = viewModelScope.launch {
        wineRepository.updateWineById(id = id.toLong(), wine = wine)
        _wineDetailsUpdateSuccess.value = Unit
    }

    fun getWineById(id: String) = viewModelScope.launch {
        val wine = wineRepository.getWineById(id.toLong()).body()?.toWine()

        _wineDetailsSuccess.value = wine!!
    }

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

    fun addWine(wine: WineRequest) = viewModelScope.launch { wineRepository.addWine(wine = wine) }

    fun deleteWine(id: String) =
        viewModelScope.launch {
            wineRepository.deleteWine(id = id.toLong())
            _deleteWineSuccess.value = Unit
        }

    private fun Wine.toWineRequest(): WineRequest {
        return WineRequest(
            name = this.name,
            harvest = this.harvest,
            alcoholPercentage = this.alcoholPercentage,
            maltster = this.maltster.value,
            quality = this.quality,
            vineyard = this.vineyard,
            temperatureOfServing = this.temperatureOfServing,
            gastroRecommendation = this.gastroRecommendation,
            description = this.description,
            price = this.price,
            sortId = this.sort.id,
        )
    }

    private fun WineResponse.toWine(): Wine {
        return Wine(
            id = this.id,
            name = this.name,
            harvest = this.harvest.toInt(),
            alcoholPercentage = this.alcoholPercentage,
            maltster = this.maltster.toMaltster(),
            quality = this.quality,
            vineyard = this.vineyard,
            temperatureOfServing = this.temperatureOfServing,
            gastroRecommendation = this.gastroRecommendation,
            description = this.description,
            price = this.price,
            sort = this.sort,
            orders = this.orders
        )
    }

    private fun String.toMaltster(): Maltster {
        return when (this) {
            "Dry" -> Maltster.DRY
            "Semi Dry" -> Maltster.SEMI_DRY
            "Sweet" -> Maltster.SWEET
            "Semi Sweet" -> Maltster.SEMI_SWEET
            else -> {
                Timber.d("Unknown maltster type $this")
                Maltster.UNKNOWN
            }
        }
    }
}
