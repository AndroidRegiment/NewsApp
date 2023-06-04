package com.androidregiment.newsapp.screen.home

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.androidregiment.newsapp.app.NewsApplication
import com.androidregiment.newsapp.data.Category
import com.androidregiment.newsapp.model.Article
import com.androidregiment.newsapp.network.RetrofitInstance
import com.androidregiment.newsapp.utils.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : AndroidViewModel(app) {
    private val _articlesStateFlow = MutableStateFlow(listOf<Article>())
    val articleFlow: StateFlow<List<Article>> = _articlesStateFlow

    private val _categoryStateFlow = MutableStateFlow(Category.ALL)
    val categoryFlow: StateFlow<Category> = _categoryStateFlow

    private val _newsStateFlow = MutableStateFlow(ResultState.SUCCESS)
    val resultFlow: StateFlow<ResultState> = _newsStateFlow

    init {
        getNewsByCategory(Category.ALL)
    }


    fun getNewsByCategory(
        category: Category
    ) {
        if (hasInternetConnection()) {
            showNewsByCategory(category)
        } else {
            _categoryStateFlow.value = category
            _newsStateFlow.value = ResultState.ERROR
        }
    }


    private fun showNewsByCategory(category: Category) {
        _categoryStateFlow.value = category
        viewModelScope.launch {
            _newsStateFlow.value = ResultState.LOADING
            val newsResponse = RetrofitInstance.api.getTopHeadline(category.strValue).body()
            _articlesStateFlow.value = newsResponse?.articles ?: listOf()
            _newsStateFlow.value = ResultState.SUCCESS
        }
    }

    fun retry() {
        getNewsByCategory(_categoryStateFlow.value)
    }


    @SuppressLint("ObsoleteSdkInt")
    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<NewsApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR
            ) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo?.run {
                type == ConnectivityManager.TYPE_WIFI || type == ConnectivityManager.TYPE_MOBILE || type == ConnectivityManager.TYPE_ETHERNET
            } ?: false
        }
    }

}