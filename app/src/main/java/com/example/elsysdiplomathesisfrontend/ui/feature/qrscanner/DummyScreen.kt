package com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DummyScreen(stateValue: String, id: String) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Text("$stateValue $id", color = Color.White)
    }
}
