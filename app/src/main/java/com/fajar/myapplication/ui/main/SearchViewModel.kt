package com.fajar.myapplication.ui.main

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajar.myapplication.data.local.Yugi
import com.fajar.myapplication.data.remote.ApiConfig
import com.fajar.myapplication.data.response.YugiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel (private val application: Application): ViewModel() {

    private val listCardMutable = MutableLiveData<ArrayList<Yugi>>()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    internal fun setListFruit(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getCard(query)
        client.enqueue(object : Callback<YugiResponse> {

            override fun onResponse(
                call: Call<YugiResponse>,
                response: Response<YugiResponse>
            ) {
                _isLoading.value = false
                val listFruit = ArrayList<Yugi>()
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        response.body()?.data?.forEach { card ->
                            listFruit.add(
                                Yugi(
                                    card.name,
                                    card.cardImages
                                )
                            )
                        }
                        listCardMutable.postValue(listFruit)
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

    internal fun getFruitSearch(): LiveData<ArrayList<Yugi>> = listCardMutable

    companion object {
        private const val TAG = "SearchViewModel"
    }

}