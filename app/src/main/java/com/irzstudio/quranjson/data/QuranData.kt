package com.irzstudio.quranjson.data

data class QuranData(
    val name : String,
    val name_translations: QuranTranslations,
    val number_of_ayah: Int,
    val number_of_surah: Int,
    val place: String
)
