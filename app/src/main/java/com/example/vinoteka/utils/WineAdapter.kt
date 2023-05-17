package com.example.vinoteka.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vinoteka.databinding.ItemEuroCentRateBinding
import com.example.vinoteka.model.Wine

/**
 * Adapter for the list of wines.
 * @param wineList List of wines.
 * @see [RecyclerView.Adapter](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter)
 */
class WineAdapter(
    private var wineList: ArrayList<Wine> = arrayListOf(),
) : RecyclerView.Adapter<WineAdapter.WineViewHolder>() {

    /**
     * View holder for the adapter.
     * @param binding Binding for the item layout.
     * @see [ItemEuroCentRateBinding](https://developer.android.com/topic/libraries/view-binding#kotlin)
     */
    inner class WineViewHolder(private val binding: ItemEuroCentRateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wine: Wine) {
        }
    }

    /**
     * Function to create the view holder.
     * @param parent Parent view group.
     * @param viewType Type of the view.
     * @return New instance of the view holder.
     * @see [WineViewHolder](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WineViewHolder {
        val binding =
            ItemEuroCentRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WineViewHolder(binding)
    }

    /**
     * Function to bind the view holder to the data.
     * @param holder View holder to be bound.
     * @param position Position of the item in the list.
     */
    override fun onBindViewHolder(holder: WineViewHolder, position: Int) {
        val item = wineList[position]
        holder.bind(item)
    }

    /**
     * Function to get the size of the list.
     * @return Size of the list.
     */
    override fun getItemCount(): Int {
        return wineList.size
    }

    /**
     * Function to set new data to the adapter.
     * @param newData List of new data to be set.
     * @see [DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)
     * for more info on how to optimize this.
     */

    fun setData(newData: List<Wine>) {
        wineList.clear()
        wineList.addAll(newData)
        notifyDataSetChanged()
    }
}