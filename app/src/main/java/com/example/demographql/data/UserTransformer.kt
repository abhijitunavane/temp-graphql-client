package com.example.demographql.data

import com.example.demographql.GetAllUsersQuery
import javax.inject.Inject

class UserTransformer @Inject constructor() {

    private fun transformToUser(input: GetAllUsersQuery.GetAllUser): UserViewData {
        return UserViewData(id = input.id, name = input.name, age = input.age)
    }

    fun transformToUserList(input: List<GetAllUsersQuery.GetAllUser>): List<UserViewData> {
        val userList = ArrayList<UserViewData>()
        for(user in input) {
            userList.add(transformToUser(user))
        }
        return userList
    }
}
