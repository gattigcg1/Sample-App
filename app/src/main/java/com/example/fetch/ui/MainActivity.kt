package com.example.fetch.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetch.Success
import com.example.fetch.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: FetchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FetchResultAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            mainViewModel.fetchFlow.collectLatest { fetchList ->
                fetchList?.let {
                    binding.progress.visibility = View.GONE
                    if (fetchList is Success) {
                        adapter.updateData(fetchList.data)
                    } else {
                        Snackbar.make(binding.root, "Error occurred", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}