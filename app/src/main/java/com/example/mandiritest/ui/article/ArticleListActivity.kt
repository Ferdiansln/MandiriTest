package com.example.mandiritest.ui.article

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mandiritest.BaseActivity
import com.example.mandiritest.databinding.ActivityArticleListBinding
import com.example.mandiritest.ui.adapter.ArticleAdapter
import com.example.mandiritest.widget.RecyclerViewLoadMoreScroll
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticleListActivity : BaseActivity(), ArticleFilterBottomSheet.ArticleFilterSubmitListener {

    companion object {
        private const val SOURCE = "SOURCE"

        fun createIntent(context: Context, sourceId: String): Intent {
            val i = Intent(context, ArticleListActivity::class.java)
            i.putExtra(SOURCE, sourceId)
            return i
        }
    }

    @Inject
    lateinit var viewModel: ArticleListViewModel
    private lateinit var binding: ActivityArticleListBinding
    private lateinit var articleAdapter: ArticleAdapter
    private val sourceId by lazy { intent?.getStringExtra(SOURCE) }
    private lateinit var loadMoreScroll: RecyclerViewLoadMoreScroll

    override fun setViewBinding() {
        binding = ActivityArticleListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupView() {
        val lm = LinearLayoutManager(this)
        loadMoreScroll = RecyclerViewLoadMoreScroll(lm) {
            viewModel.loadData()
        }
        articleAdapter = ArticleAdapter()
        binding.rv.apply {
            layoutManager = lm
            adapter = articleAdapter
            addOnScrollListener(loadMoreScroll)
        }
        viewModel.onCreateView(sourceId ?: "")
        binding.filterBtn.setOnClickListener {
            viewModel.getFilter()
        }
    }

    override fun observeEvent() {
        viewModel.viewState.observe(this) {
            binding.progressBar.visibility = if (it.showLoading) View.VISIBLE else View.GONE
            if (!it.showLoading) loadMoreScroll.setLoaded()
        }
        viewModel.observeEvent.observe(this) {
            when (it) {
                is ArticleListViewModel.ArticleEvent.OnError -> showToast(it.message)
                is ArticleListViewModel.ArticleEvent.OnShowFilter -> {
                    ArticleFilterBottomSheet.createInstance(
                        it.from, it.to, it.q, it.qInTitle,
                        it.language, it.sources, this
                    ).show(supportFragmentManager, "btsheet")
                }
            }
        }
        viewModel.articleListLiveData.observe(this) {
            articleAdapter.addData(it)
        }
    }

    override fun onSubmit(
        from: String, to: String, q: String,
        qInTitle: String, language: String?, sources: String
    ) {
        viewModel.setFilter(from, to, q, qInTitle, language, sources)
    }

}
