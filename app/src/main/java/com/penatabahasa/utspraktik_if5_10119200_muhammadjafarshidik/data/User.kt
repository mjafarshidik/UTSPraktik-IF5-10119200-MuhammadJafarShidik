package com.penatabahasa.utspraktik_if5_10119200_muhammadjafarshidik.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
2 Juni 2022
10119200
Muhammad Jafar Shidik
IF-5
*/

@Parcelize
data class User(
    var typeTest: String? = "",
    var dateConfirmed: String? = "",
    var nik: String? = "",
    var name: String? = "",
    var dateOfBirth: String? = "",
    var gender: String? = "",
    var relationship: String? = "",
    var status: String? = ""
) : Parcelable