package com.example.fetch.data.service

import com.example.fetch.data.model.FetchResult
import retrofit2.Response
import retrofit2.http.GET

interface FetchService {

    @GET("https://fetch-hiring.s3.amazonaws.com/hiring.json")
    suspend fun getFetchResults(): Response<List<FetchResult>>
}