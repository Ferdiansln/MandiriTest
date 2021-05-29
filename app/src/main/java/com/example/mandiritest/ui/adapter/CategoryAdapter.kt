package com.example.mandiritest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mandiritest.databinding.ItemCategoryBinding
import java.util.*

class CategoryAdapter(private val clickAction: (String) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private val list: MutableList<String> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context)), clickAction)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setData(newList: List<String>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding, private val clickAction: (String) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String) {
            binding.categoryName.text = category.capitalize(Locale.getDefault())
            binding.root.setOnClickListener { clickAction(category) }
        }
    }

}
