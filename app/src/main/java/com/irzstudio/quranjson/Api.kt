package com.irzstudio.quranjson

import com.irzstudio.quranjson.data.QuranData
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("quran.json")
    fun getQuran() : Call<ArrayList<QuranData>>
}