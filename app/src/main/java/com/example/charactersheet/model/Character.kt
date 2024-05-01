package com.example.charactersheet.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Character(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val artist: Int,
)
