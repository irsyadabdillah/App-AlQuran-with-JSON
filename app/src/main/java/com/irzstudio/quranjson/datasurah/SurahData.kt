package com.irzstudio.quranjson.datasurah

data class SurahData(
    val name : String,
    val name_translations : SurahTranslationsData ,
    val number_of_ayah : Int,
    val number_of_surah : Int,
    val place: String,
    val verses : ArrayList<SurahVerses>
)