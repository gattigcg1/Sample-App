package com.example.fetch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch.R
import com.example.fetch.data.model.FetchResult
import com.example.fetch.databinding.ItemFetchStringBinding

class FetchResultAdapter(private var fetchResultList: List<FetchResult>) : RecyclerView.Adapter<FetchResultAdapter.StringViewHolder>() {

    inner class StringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fetch_string, parent, false)
        return StringViewHolder(view)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        val binding = ItemFetchStringBinding.bind(holder.itemView)
        val fetchResult = fetchResultList[position]
        binding.textViewString.text =
            holder.itemView.context.getString(
                R.string.fetch_list_string,
                fetchResult.id,
                fetchResult.listId,
                fetchResult.name
            )
    }

    fun updateData(newItems: List<FetchResult>) {
        fetchResultList = newItems
        notifyDataSetChanged() // Refresh the entire list
    }

    override fun getItemCount(): Int {
        return fetchResultList.size
    }
}