package com.example.mandiritest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mandiritest.R
import com.example.mandiritest.databinding.ItemSourceBinding
import com.example.mandiritest.model.NewsSource
import com.example.mandiritest.ui.web.WebViewActivity


class SourceAdapter(private val clickAction: (String) -> Unit) :
    RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {
    private var list: List<NewsSource> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        return SourceViewHolder(
            ItemSourceBinding.inflate(LayoutInflater.from(parent.context)),
            clickAction
        )
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addData(newList: List<NewsSource>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class SourceViewHolder(
        private val binding: ItemSourceBinding,
        private val clickAction: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsSource: NewsSource) {
            binding.apply {
                sourceName.text = newsSource.name
                descTxt.text = newsSource.description
                urlTxt.text = newsSource.url
                urlTxt.setOnClickListener {
                    val i =
                        WebViewActivity.createIntent(root.context, newsSource.url)
                    root.context.startActivity(i)

                }
                langTxt.text = root.context.getString(
                    R.string.language_formatted,
                    newsSource.language
                )
                root.setOnClickListener { clickAction(newsSource.id) }
            }
        }
    }

}
