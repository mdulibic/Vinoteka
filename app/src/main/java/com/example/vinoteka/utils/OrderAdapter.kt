package com.example.vinoteka.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vinoteka.R
import com.example.vinoteka.databinding.ItemOrderBinding
import com.example.vinoteka.databinding.ItemWineProductBinding
import com.example.vinoteka.model.OrderResponse
import com.example.vinoteka.model.Wine

/**
 * Adapter for the list of wines.
 * @param wineList List of wines.
 * @see [RecyclerView.Adapter](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter)
 */
class OrderAdapter(
    private var orderList: ArrayList<OrderResponse> = arrayListOf()
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {


    /**
     * View holder for the adapter.
     * @param binding Binding for the item layout.
     */
    inner class OrderViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: OrderResponse) {
            binding.apply {
                textOrderId.text =
                    binding.root.context.resources.getString(R.string.id_1_s, order.id.toString())
                textCreationTimestamp.text =
                    binding.root.context.resources.getString(R.string.timestamp_1_s, order.creationTimestamp.toString())
                textOrderStatus.text =
                    binding.root.context.resources.getString(R.string.status_1_s, order.orderStatus.status)
                textTotalPrice.text =
                    binding.root.context.resources.getString(R.string.total_price_1_s, order.totalPrice.toString())
                textPaymentMethod.text =
                    binding.root.context.resources.getString(R.string.payment_method_1_s, order.paymentMethod.method)
            }
        }
    }

    /**
     * Function to create the view holder.
     * @param parent Parent view group.
     * @param viewType Type of the view.
     * @return New instance of the view holder.
     * @see [WineViewHolder](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding =
            ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    /**
     * Function to bind the view holder to the data.
     * @param holder View holder to be bound.
     * @param position Position of the item in the list.
     */
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = orderList[position]
        holder.bind(item)
    }

    /**
     * Function to get the size of the list.
     * @return Size of the list.
     */
    override fun getItemCount(): Int {
        return orderList.size
    }

    /**
     * Function to set new data to the adapter.
     * @param newData List of new data to be set.
     * @see [DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)
     * for more info on how to optimize this.
     */

    fun setData(newData: List<OrderResponse>) {
        orderList.clear()
        orderList.addAll(newData)
        notifyDataSetChanged()
    }
}