package com.example.cryptochallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.AvailablebookItemBinding
import com.example.cryptochallenge.domain.model.Book
import com.example.cryptochallenge.getBaseCurrency
import com.example.cryptochallenge.getCurrency

class AvailableBooksAdapter(val adapterOnClick : (Any) -> Unit) :
    ListAdapter<Book, AvailableBooksAdapter.AvailableBookViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableBookViewHolder {
        return LayoutInflater.from(parent.context)
            .let { layoutInflater ->
                AvailablebookItemBinding.inflate(layoutInflater, parent, false)
            }.let { binding ->
                AvailableBookViewHolder(binding)
            }
    }

    override fun onBindViewHolder(holder: AvailableBookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DIFF : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
            oldItem == newItem
    }

    inner class AvailableBookViewHolder(val binding: AvailablebookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book) {
            binding.apply {
                val currency = item.getCurrency()
                val resImg = root.context.resources.getIdentifier(item.getBaseCurrency(), "drawable",
                    root.context.packageName
                )
                //TODO: change it to something more "kotlin"
                if(resImg>0){
                    cyptoImageView.setImageResource(resImg)
                }else{
                    cyptoImageView.setImageResource(R.drawable.unknow)
                }

                minAmountText.text = root.context.getString(R.string.currency_display, item.min_amount, currency )
                maxAmountText.text = root.context.getString(R.string.currency_display, item.max_amount, currency )
                minPriceText.text = root.context.getString(R.string.currency_display, item.min_price, currency )
                maxPriceText.text = root.context.getString(R.string.currency_display, item.max_price, currency )
                minValueText.text = root.context.getString(R.string.currency_display, item.min_value, currency )
                maxValueText.text = root.context.getString(R.string.currency_display, item.max_value, currency )
                //Click listener
                availableBookItemCard.setOnClickListener { adapterOnClick(item) }
            }
        }



    }
}