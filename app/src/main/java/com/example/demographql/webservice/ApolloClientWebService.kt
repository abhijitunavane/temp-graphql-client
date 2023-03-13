package com.example.demographql.webservice

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import javax.inject.Inject

class ApolloClientWebService @Inject constructor() {

    fun getDemoGraphQLClient(): ApolloClient {
        val okHttpClient = OkHttpClient.Builder().build()
        return ApolloClient.Builder()
            .serverUrl("http://192.168.1.3:3000/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }
}
