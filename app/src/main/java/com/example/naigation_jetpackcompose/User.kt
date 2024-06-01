package com.example.naigation_jetpackcompose

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Serializable
@Parcelize
class User(
    val id: Int,
    val name: String,
    val age: Int
) : Parcelable

val CustomNavType = object : NavType<User>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): User? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            bundle.getParcelable(key,User::class.java) as User
        }else{
            bundle.getParcelable<User>(key) as User
        }
    }

    override fun parseValue(value: String): User {
        return Json.decodeFromString<User>(value)
    }

    override fun put(bundle: Bundle, key: String, value: User) {
        bundle.putParcelable(key,value)
    }

    override fun serializeAsValue(value: User): String {
        return Json.encodeToString(value)
    }


}