package com.example.demographql.data

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userFeature: UserFeature
): ViewModel() {

    fun getUserFeature(): UserFeature {
        return userFeature
    }
}
