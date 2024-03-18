package com.ifs21049.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Family(
    var name: String,
    var icon: Int,
    var deskripsi: String,
    var periode: String,
    var karakteristik: String,
    var habitat: String,
    var perilaku: String,
    var klasifikasi: String,
) : Parcelable