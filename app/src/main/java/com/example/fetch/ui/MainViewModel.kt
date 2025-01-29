package com.example.fetch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetch.NetworkResult
import com.example.fetch.Success
import com.example.fetch.data.repository.FetchRepository
import com.example.fetch.data.model.FetchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FetchRepository
): ViewModel() {

    private val _fetchFlow = MutableStateFlow<NetworkResult<List<FetchResult>>?>(null)
    val fetchFlow: StateFlow<NetworkResult<List<FetchResult>>?> = _fetchFlow

    init {
        getFetchResults()
    }

    private fun getFetchResults() {
        viewModelScope.launch {
            val networkResult = repository.getFetchResult()
            if (networkResult is Success) {
                _fetchFlow.update {
                    Success(
                        networkResult
                            .data
                            .filter {
                                !it.name.isNullOrEmpty()
                            }
                            .sortedWith(compareBy<FetchResult> { it.listId }.thenBy { it.name })
                    )
                }
            } else {
                _fetchFlow.update { networkResult }
            }
        }
    }
}