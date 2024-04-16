package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class Affirmation(
    //Create two val properties in the Affirmation data class.
    // Annotate the stringResourceId property with the @StringRes annotation
    // and
    // annotate the imageResourceId with the @DrawableRes annotation.
    @StringRes val stringResourceId: Int, //The stringResourceId represents an ID for the affirmation text stored in a string resource
    @DrawableRes val imageResourceId: Int //The imageResourceId represents an ID for the affirmation image stored in a drawable resource.
)
