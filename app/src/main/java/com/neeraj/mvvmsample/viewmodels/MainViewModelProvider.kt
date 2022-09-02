package com.neeraj.mvvmsample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neeraj.mvvmsample.repository.QuoteRepository

class MainViewModelProvider(private val quoteRepository: QuoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return MainViewModel(quoteRepository) as T
    }

}