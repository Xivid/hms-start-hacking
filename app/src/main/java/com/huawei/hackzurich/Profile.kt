package com.huawei.hackzurich

//
//data class Profile(
//    val age: Int,
//    val distance: Int,
//    val id: Int,
//    val name: String,
//    val profile_pic: String
//)

data class Profile(
    val name: String,
    val duration: Int,
    val calories: Int,
    val kilojoule: Int,
    val carbohydrates_percent: Float,
    val fat_percent: Float,
    val proteins_percent: Float,
    val ingredients: String,
    val pic_url: String
)