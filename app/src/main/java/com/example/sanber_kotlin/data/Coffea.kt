package com.example.sanber_kotlin.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coffea(
    var nama : String,
    var deskripsi : String,
    var image: Int,
) : Parcelable
