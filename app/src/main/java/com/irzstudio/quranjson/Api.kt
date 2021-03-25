package com.irzstudio.quranjson

import com.irzstudio.quranjson.dataquran.QuranData
import com.irzstudio.quranjson.datasurah.SurahData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("quran.json")
    fun getQuran() : Call<ArrayList<QuranData>>

    @GET("surah/{id_surah}.json")
    fun getSurah(@Path("id_surah") idSurah: Int) : Call<SurahData>
}