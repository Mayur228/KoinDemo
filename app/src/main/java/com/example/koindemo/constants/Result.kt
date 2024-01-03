package com.example.koindemo.constants

sealed class Result<out T> {
    object Loading: Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}