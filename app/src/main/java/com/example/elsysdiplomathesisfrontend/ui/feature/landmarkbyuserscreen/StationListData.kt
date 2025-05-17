package com.example.elsysdiplomathesisfrontend.ui.feature.landmarkbyuserscreen

import androidx.compose.runtime.Composable

@Composable
fun getStationList() : List<Pair<String, Int>> {
    return listOf(
        Pair("Г. М. Димитров", 11),
        Pair("Мусагеница", 12),
        Pair("Младост 1", 13),
        Pair("Александър Малинов", 14)
    )
}