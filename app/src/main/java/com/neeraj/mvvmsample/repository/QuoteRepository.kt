package com.neeraj.mvvmsample.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neeraj.mvvmsample.api.QuoteService
import com.neeraj.mvvmsample.database.Quote
import com.neeraj.mvvmsample.database.QuoteDao
import com.neeraj.mvvmsample.model.QuoteList
import com.neeraj.mvvmsample.utils.NetworkUtil
import java.lang.Exception

class QuoteRepository(private val quoteService: QuoteService, private val quoteDao: QuoteDao, private val context: Context) {

    private val quoteListMutableLiveData = MutableLiveData<Response<QuoteList>>()
    private val quoteDBListMutableLiveData = MutableLiveData<Response<List<Quote>>>()


    val quoteListLiveData: LiveData<Response<QuoteList>>
        get() = quoteListMutableLiveData

    val quoteDBListLiveData: LiveData<Response<List<Quote>>>
        get() = quoteDBListMutableLiveData


    suspend fun getQuotes(page: Int) {

        if (NetworkUtil.isNetworkAvailable(context)) {

            try {
                val quoteList = quoteService.getQuotes(page)
                if (quoteList?.body() != null) {
                    for (item in quoteList.body()!!.results) {
                        var quote = Quote(item.author, item.content)
                        quoteDao.insertQuote(quote)
                    }
                    quoteListMutableLiveData.postValue(Response.Success(quoteList.body()))
                }
            } catch (ex : Exception) {
                quoteListMutableLiveData.postValue(Response.Error(ex.message.toString()))
            }
        } else {
            try {
                val quoteList = quoteDao.getQuotes()
                quoteDBListMutableLiveData.postValue(Response.Success(quoteList))
            } catch (ex : Exception) {
                quoteDBListMutableLiveData.postValue(Response.Error("Database Error"))
            }

        }

    }


    suspend fun getQuotesFromBackground() {
        val random = (Math.random() * 10).toInt()
        val quoteList = quoteService.getQuotes(random)
        if (quoteList?.body() != null) {
            for (item in quoteList.body()!!.results) {
                var quote = Quote(item.author, item.content)
                quoteDao.insertQuote(quote)
            }
        }
    }

}