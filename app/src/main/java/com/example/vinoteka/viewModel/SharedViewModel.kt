package com.example.vinoteka.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.vinoteka.utils.Event
import com.example.vinoteka.utils.NavCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * used for navigation
 */
@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel() {

    private val _navCommand = MutableLiveData<Event<NavCommand>>()
    val navCommand: LiveData<Event<NavCommand>> = _navCommand

    fun navigate(directions: NavDirections) {
        _navCommand.postValue(Event(NavCommand.To(directions)))
    }

    fun navigateUp() {
        _navCommand.postValue(Event(NavCommand.Up))
    }
}
