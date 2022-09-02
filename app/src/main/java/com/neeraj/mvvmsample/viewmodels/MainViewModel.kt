package com.neeraj.mvvmsample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neeraj.mvvmsample.database.Quote
import com.neeraj.mvvmsample.model.QuoteList
import com.neeraj.mvvmsample.repository.QuoteRepository
import com.neeraj.mvvmsample.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
             quoteRepository.getQuotes(1)
        }
    }

    val quoteList : LiveData<Response<QuoteList>>
    get() = quoteRepository.quoteListLiveData

    val quoteDBList : LiveData<Response<List<Quote>>>
    get() = quoteRepository.quoteDBListLiveData


}