package com.example.mandiritest.ui.source

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mandiritest.BaseActivity
import com.example.mandiritest.databinding.ActivitySourceListBinding
import com.example.mandiritest.ui.adapter.SourceAdapter
import com.example.mandiritest.ui.article.ArticleListActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SourceListActivity : BaseActivity(), SourceFilterBottomSheet.SourceFilterSubmitListener {

    companion object {
        private const val CATEGORY = "CATEGORY"

        fun createIntent(context: Context, category: String): Intent {
            val i = Intent(context, SourceListActivity::class.java)
            i.putExtra(CATEGORY, category)
            return i
        }
    }

    @Inject
    lateinit var viewModel: SourceListViewModel
    private lateinit var binding: ActivitySourceListBinding
    private lateinit var adapter: SourceAdapter
    private val category by lazy {
        intent?.getStringExtra(CATEGORY)
    }

    override fun setViewBinding() {
        binding = ActivitySourceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupView() {
        binding.rv.layoutManager = LinearLayoutManager(this)
        adapter = SourceAdapter {
            startActivity(ArticleListActivity.createIntent(this, it))
        }
        binding.rv.adapter = adapter
        viewModel.onCreateView(category ?: "")
        binding.filterBtn.setOnClickListener {
            viewModel.getFilter()
        }
    }

    override fun observeEvent() {
        viewModel.viewState.observe(this) {
            adapter.addData(it.newsSources)
            binding.progressBar.visibility = if (it.showLoading) View.VISIBLE else View.GONE
        }
        viewModel.observeEvent.observe(this) {
            when (it) {
                is SourceListViewModel.SourceEvent.OnError -> showToast(it.message)
                is SourceListViewModel.SourceEvent.OnShowFilter -> {
                    SourceFilterBottomSheet.createInstance(
                        it.country, it.category , it.language, this
                    ).show(supportFragmentManager, "btsheet")}
            }
        }
    }

    override fun onSubmit(country: String?, category: String?, language: String?) {
        viewModel.setFilter(country, category, language)
    }

}
