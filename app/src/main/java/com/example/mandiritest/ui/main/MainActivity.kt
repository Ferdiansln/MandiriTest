package com.example.mandiritest.ui.main

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mandiritest.BaseActivity
import com.example.mandiritest.databinding.ActivityMainBinding
import com.example.mandiritest.ui.adapter.CategoryAdapter
import com.example.mandiritest.ui.source.SourceListActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CategoryAdapter
    override fun setViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupView() {
        binding.rv.layoutManager = LinearLayoutManager(this)
        adapter = CategoryAdapter {
            startActivity(SourceListActivity.createIntent(this, it))
        }
        binding.rv.adapter = adapter
    }

    override fun observeEvent() {
        viewModel.viewState.observe(this) {
            adapter.setData(it.categories)
        }
    }
}
