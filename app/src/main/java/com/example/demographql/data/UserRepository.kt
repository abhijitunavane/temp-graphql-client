package com.example.demographql.data

import com.example.demographql.webservice.ApolloClientWebService
import com.apollographql.apollo3.api.ApolloResponse
import com.example.demographql.GetAllUsersQuery
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apolloClientWebService: ApolloClientWebService
) {

   suspend fun fetchAllUsers(): ApolloResponse<GetAllUsersQuery.Data> {
        return apolloClientWebService.getDemoGraphQLClient().query(GetAllUsersQuery()).execute()
   }
}
