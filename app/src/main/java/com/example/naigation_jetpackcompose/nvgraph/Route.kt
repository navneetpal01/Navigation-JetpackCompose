package com.example.naigation_jetpackcompose.nvgraph

import kotlinx.serialization.Serializable


@Serializable
sealed class Route{
    @Serializable
    data object Home : Route()

    @Serializable
    data class Details(
        val id : Int
    ): Route()
}

