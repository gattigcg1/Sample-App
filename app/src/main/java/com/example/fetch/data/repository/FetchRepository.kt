package com.example.fetch.data.repository

import com.example.fetch.NetworkResult
import com.example.fetch.data.model.FetchResult
import com.example.fetch.data.service.FetchService
import com.example.fetch.handleApi
import javax.inject.Inject

class FetchRepository @Inject constructor(private val fetchService: FetchService) {

    suspend fun getFetchResult(): NetworkResult<List<FetchResult>> {
        return handleApi { fetchService.getFetchResults() }
    }
}