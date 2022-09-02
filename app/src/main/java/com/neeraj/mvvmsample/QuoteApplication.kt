package com.neeraj.mvvmsample

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.neeraj.mvvmsample.api.QuoteService
import com.neeraj.mvvmsample.api.RetrofitHelper
import com.neeraj.mvvmsample.database.QuoteDatabase
import com.neeraj.mvvmsample.repository.QuoteRepository
import com.neeraj.mvvmsample.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()

        initialize()
        setUpWorker()

    }

    private fun initialize() {

        val quoteService = RetrofitHelper.getRetrofitInstance().create(QuoteService::class.java)

        val quoteDao = QuoteDatabase.getDataInstance(applicationContext).quoteDao

        quoteRepository = QuoteRepository(quoteService, quoteDao, applicationContext)

    }


    private fun setUpWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java, 1, TimeUnit.MINUTES).setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

}