package com.example.coreandroid.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.coreandroid.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): BaseViewModel() {
    fun splash(done: () -> Unit) = viewModelScope.launch {
        delay(3000)
        done()
    }
}