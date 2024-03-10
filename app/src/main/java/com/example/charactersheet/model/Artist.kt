package com.example.charactersheet.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
data class Artist(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
