package com.fajar.myapplication.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fajar.myapplication.data.model.Yugi
import com.fajar.myapplication.databinding.ActivityDetailBinding
import com.fajar.myapplication.ui.viewmodel.ViewModelFactory
import com.fajar.myapplication.util.Constant.Companion.IMAGE_BASE_URL
import com.fajar.myapplication.util.Utils.loadImageUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var card: Yugi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailViewModel = obtainViewModel(this)
        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        card = intent.extras?.getParcelable<Yugi>(EXTRA_DATA_FRUIT) as Yugi

        card.name.let {
            val nama = Bundle()
            nama.putString(EXTRA_DATA_DETAIL, card.name)
            detailViewModel.setDetailFruit(it.toString())
            setActionBarTitle(card.name.toString())
        }

        getDataFruit(card)


    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DetailViewModel::class.java]
    }

    private fun getDataFruit(fruitItem: Yugi) {
        binding.apply {
            detailViewModel.getDetailFruit().observe(this@DetailActivity) { listFruit ->
                listFruit?.let { fruit ->
                    fruit.forEach { detailFruit ->

                        //  imgFruit.loadImageUrl(detailFruit.image.toString())
                        imgView.loadImageUrl("${IMAGE_BASE_URL}${detailFruit.cardImage.toString()}")
                        tvName.text = detailFruit.name
                        tvType.text = detailFruit.type
                        tvRace.text = detailFruit.race
                        detailEdDescription.text = detailFruit.desc
                        tvAttack.text = detailFruit.atk.toString()
                        tvDefense.text = detailFruit.def.toString()


                    }
                }
            }
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setActionBarTitle(nama: String) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = nama
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_DATA_FRUIT = "extra_data_fruit"
        const val EXTRA_DATA_DETAIL = "extra_data_detail"


    }

}