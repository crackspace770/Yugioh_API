package com.fajar.myapplication.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.myapplication.R
import com.fajar.myapplication.data.local.Yugi
import com.fajar.myapplication.databinding.SearchActivityBinding
import com.fajar.myapplication.ui.adapter.SearchAdapter
import com.fajar.myapplication.ui.detail.DetailActivity
import com.fajar.myapplication.ui.interfaces.ItemClickCallback
import com.fajar.myapplication.ui.viewmodel.ViewModelFactory
import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.fajar.myapplication.ui.detail.DetailViewModel


class SearchActivity:AppCompatActivity() {

    private lateinit var binding: SearchActivityBinding
    private lateinit var adapter: SearchAdapter
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchCardData()

        viewModel = obtainViewModel(this@SearchActivity)

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        getDataCard()

    }

    private fun obtainViewModel(activity: AppCompatActivity): SearchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[SearchViewModel::class.java]

    }

    private fun searchCardData() {
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.cardList.clear()
                query.let {
                    binding.searchView?.clearFocus() // hide keyboard after searching
                    finalState()
                    viewModel.setListFruit(it.toString())
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun getDataCard() {
        binding.apply {
            rvCard.setHasFixedSize(true)
            rvCard.layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = SearchAdapter()
            rvCard.adapter = adapter

            viewModel.getFruitSearch().observe(this@SearchActivity) { listUser ->
                listUser.let {
                    if (it.size == 0) {
                        emptyState()
                    } else {
                        adapter.setData(it)
                        finalState()
                    }
                }
            }
            adapter.setOnItemClickCallback(object : ItemClickCallback {
                override fun onItemClicked(fruitItem: Yugi) {
                    showClickedFruitItem(fruitItem)
                }
            })
        }
    }

    private fun emptyState() {
        binding?.apply {
            rvCard.visibility = View.GONE
            layoutEmptyData.root.visibility = View.VISIBLE
        }
    }

    private fun finalState() {
        binding.apply {
            rvCard.visibility = View.VISIBLE
            layoutEmptyData.root.visibility = View.GONE
        }
    }

    private fun showClickedFruitItem(yugi: Yugi) {
        Toast.makeText(this, "You Choose " + yugi.name, Toast.LENGTH_SHORT).show()

        val moveWithObjectIntent = Intent(this@SearchActivity, DetailActivity::class.java)
        moveWithObjectIntent.putExtra(DetailActivity.EXTRA_DATA_DETAIL, yugi)

        startActivity(moveWithObjectIntent)
        viewModel = SearchViewModel()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}