package com.ics342.labs

import com.squareup.moshi.Json

// Add the data classes here.
data class PersonalDetails (
    @Json(name = "id") val id : Int,
    @Json(name = "give_name") val firstName : String,
    @Json(name = "family_name") val lastName : String,
    @Json(name = "age") val age : Int
)

