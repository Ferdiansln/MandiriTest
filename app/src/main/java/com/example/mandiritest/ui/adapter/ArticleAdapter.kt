package com.example.mandiritest.ui.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mandiritest.R
import com.example.mandiritest.databinding.ItemArticleBinding
import com.example.mandiritest.model.Article
import com.example.mandiritest.ui.web.WebViewActivity
import com.example.mandiritest.utils.DateUtils


class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    private var list: List<Article> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addData(newList: List<Article>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.apply {
                titleTxt.text = article.title
                descTxt.text = Html.fromHtml(article.description)
                authorTxt.text = article.author
                sourceTxt.text = article.source?.name
                Glide.with(root.context)
                    .load(article.urlToImage)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .into(picImg)
                root.setOnClickListener {
                    val i =
                        WebViewActivity.createIntent(root.context, article.url)
                    root.context.startActivity(i)
                }
                dateTxt.text = DateUtils.convertToReadableTimestamp(article.publishedAt)
            }
        }
    }

}
