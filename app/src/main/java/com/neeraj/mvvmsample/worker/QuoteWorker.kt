package com.neeraj.mvvmsample.worker

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.neeraj.mvvmsample.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker (private val context: Context, params : WorkerParameters) : Worker(context, params)  {

    override fun doWork(): Result {

        CoroutineScope(Dispatchers.IO).launch {
            val quoteRepository = (context as QuoteApplication).quoteRepository
            quoteRepository.getQuotesFromBackground()
        }
        return Result.success()
    }


}