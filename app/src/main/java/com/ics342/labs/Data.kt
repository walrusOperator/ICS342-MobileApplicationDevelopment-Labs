package com.ics342.labs

// Add the data classes here.
data class PersonalDetails (
    @Json(name = "id") val id : Int,
    @Json(name = "given_name") val firstName : String,
    @Json(name = "family_name") val lastName : String,
    @Json(name = "age") val age : Int
)

