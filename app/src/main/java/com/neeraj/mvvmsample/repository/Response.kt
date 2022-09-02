package com.neeraj.mvvmsample.repository

sealed class Response <T>(val data : T? = null, val errorMessage : String? = null) {

    class Loading<T>() : Response<T>()
    class Success<T>(successData : T? = null) : Response<T>(data = successData)
    class Error<T>(errorData : String) : Response<T>(errorMessage = errorData)

}