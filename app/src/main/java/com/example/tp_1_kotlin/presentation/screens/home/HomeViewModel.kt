package com.example.tp_1_kotlin.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    fun onShowTextFieldChange(isChecked: Boolean){
        state = state.copy(showTextField = isChecked)
    }

    fun onSelectedPlatformChange(platform: String){
        state = state.copy(selectedPlatform = platform)
    }

    fun onPreferenceCheckChange(isChecked: Boolean, preference: String) {
        val oldPreferences = state.preferences
        val newPreferences = if (isChecked) oldPreferences + preference else oldPreferences - preference
        state = state.copy(preferences = newPreferences)

    }
    fun onOtherPreferenceChange(preference : String) {
        state = state.copy(otherPreference = preference)
    }
}