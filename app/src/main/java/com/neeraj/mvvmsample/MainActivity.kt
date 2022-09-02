package com.neeraj.mvvmsample

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.neeraj.mvvmsample.api.QuoteService
import com.neeraj.mvvmsample.api.RetrofitHelper
import com.neeraj.mvvmsample.repository.QuoteRepository
import com.neeraj.mvvmsample.viewmodels.MainViewModel
import com.neeraj.mvvmsample.viewmodels.MainViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quoteRepository = (application as QuoteApplication).quoteRepository

        mViewModel = ViewModelProvider(this, MainViewModelProvider(quoteRepository)).get(MainViewModel::class.java)

        mViewModel.quoteList.observe(this, Observer { quoteList ->
            quoteList.data.let {
               if(it?.results != null) {
                   for (item in it.results)
                       Log.d("Quote = ", item.content)
               }
           }

            quoteList.errorMessage.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }


        })

        mViewModel.quoteDBList.observe(this, Observer { quoteList ->
            quoteList.data.let {
                if(it != null) {
                    for (item in it)
                        Log.d("DBQuote = ", item.content)
                }
            }

            quoteList.errorMessage.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }

        })

    }
}