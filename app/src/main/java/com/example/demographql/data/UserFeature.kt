package com.example.demographql.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.example.demographql.GetAllUsersQuery
import com.example.demographql.Resource
import javax.inject.Inject

class UserFeature @Inject constructor(
    private val userRepository: UserRepository
) {

    private val _usersLiveData by lazy {
        MutableLiveData<Resource<ApolloResponse<GetAllUsersQuery.Data>>>()
    }
    val usersLiveData: LiveData<Resource<ApolloResponse<GetAllUsersQuery.Data>>>
        get() = _usersLiveData

    suspend fun getUsersLiveData() {
        _usersLiveData.postValue(Resource.Loading())
        try {
            val response = userRepository.fetchAllUsers()
            _usersLiveData.postValue(Resource.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _usersLiveData.postValue(Resource.Error())
        }
    }
}
