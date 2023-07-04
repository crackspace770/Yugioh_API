package com.fajar.myapplication.ui.detail

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajar.myapplication.data.model.YugiDetail
import com.fajar.myapplication.data.remote.ApiConfig
import com.fajar.myapplication.data.response.YugiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val application: Application): ViewModel() {

    private val listDetailFruitMutable = MutableLiveData<ArrayList<YugiDetail>>()
   // private val favoriteFruitRepository: FavoriteRepository = FavoriteRepository(application)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    internal fun setDetailFruit(nama: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailCard(nama)
        client.enqueue(object : Callback<YugiResponse> {

            override fun onResponse(
                call: Call<YugiResponse>,
                response: Response<YugiResponse>
            ) {
                _isLoading.value = false
                val listDetailFruit = ArrayList<YugiDetail>()
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        response.body()?.data?.forEach { detailFruit ->
                            listDetailFruit.add(
                                YugiDetail(
                                    detailFruit.name,
                                    detailFruit.type,
                                    detailFruit.frameType,
                                    detailFruit.desc,
                                    detailFruit.race,
                                    detailFruit.archetype,
                                    detailFruit.cardImages,
                                    detailFruit.atk,
                                    detailFruit.def,
                                    detailFruit.level,
                                    detailFruit.attribute,

                                )
                            )
                        }
                        listDetailFruitMutable.postValue(listDetailFruit)
                    }
                } else {
                    Toast.makeText(
                        application.applicationContext,
                        response.message(),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<YugiResponse>, t: Throwable) {
                _isLoading.value = false
                Toast.makeText(
                    application.applicationContext,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
                Log.e(TAG, "onFailure ${t.message}")
            }
        })
    }


    internal fun getDetailFruit(): LiveData<ArrayList<YugiDetail>> = listDetailFruitMutable



    companion object {
        const val TAG = "DetailViewModel"
    }

}