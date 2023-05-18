package com.example.vinoteka.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinoteka.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val sessionManager: SessionManager,
) : ViewModel() {

    private val _emailValidationSuccess = MutableLiveData<Unit>()
    val emailValidationSuccess: LiveData<Unit>
        get() = _emailValidationSuccess

    private val _emailValidationFail = MutableLiveData<Unit>()
    val emailValidationFail: LiveData<Unit>
        get() = _emailValidationFail

    fun validateEmail(email: String) {
        val emailPattern = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        if (emailPattern.matches(email)) {
            _emailValidationSuccess.value = Unit
            sessionManager.adminEmail = email
        } else {
            _emailValidationFail.value = Unit
        }
    }

    fun checkIfEmailValidated() {
        sessionManager.adminEmail?.let {
            _emailValidationSuccess.value = Unit
        }
    }
}
