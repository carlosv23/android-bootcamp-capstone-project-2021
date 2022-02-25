package com.example.cryptochallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.OrderItemBinding
import com.example.cryptochallenge.domain.model.Order
import com.example.cryptochallenge.getCurrency

class OrderAdapter : ListAdapter<Order, OrderAdapter.OrderViewHolder>(DIIF_CALL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return LayoutInflater.from(parent.context)
            .let { layoutInflater ->
                OrderItemBinding.inflate(layoutInflater, parent, false)
            }
            .let { binding ->
                OrderViewHolder(binding)
            }
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DIIF_CALL : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean =
            oldItem.book == newItem.book && oldItem.price == newItem.price

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean =
            oldItem == newItem
    }

    inner class OrderViewHolder(private var binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Order) {
            binding.apply {
                val currency = item.getCurrency()
                priceItemTextView.text = root.context.getString(R.string.currency_display, item.price, currency)
                amountItemTextView.text = root.context.getString(R.string.currency_display, item.amount, currency)
            }
        }
    }
}
