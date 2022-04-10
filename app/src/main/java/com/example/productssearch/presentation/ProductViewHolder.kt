package com.example.productssearch.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productssearch.R
import com.example.productssearch.databinding.ItemProductBinding
import com.example.productssearch.domain.models.Product
import com.squareup.picasso.Picasso

class ProductViewHolder(private val binding: ItemProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        with(product) {
            binding.productName.text = productName
            binding.productDetails.text = USPs.joinToString (separator = "\n")
            binding.productPrice.text = salesPriceIncVat.toString()
            binding.productReviews.text = itemView.context.getString(
                R.string.product_reviews,
                reviewInformation.reviewSummary.reviewAverage,
                reviewInformation.reviewSummary.reviewCount,
            )
            Picasso.get().load(productImage).into(binding.productImage)
        }
    }

    companion object {
        fun create(parent: ViewGroup): ProductViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product,  parent,false)

            val binding = ItemProductBinding.bind(view)

            return ProductViewHolder(
                binding
            )
        }
    }
}