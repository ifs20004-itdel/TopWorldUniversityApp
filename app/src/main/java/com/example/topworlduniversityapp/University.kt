package com.example.topworlduniversityapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class University(
    val name: String,
    val description: String,
    val score: String,
    val photo: String,
    val country: String,
    val motto: String
) : Parcelable
