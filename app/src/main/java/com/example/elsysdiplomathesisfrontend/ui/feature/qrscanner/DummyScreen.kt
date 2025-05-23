package com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.elsysdiplomathesisfrontend.R

@Composable
fun DummyScreen(stateValue: String, id: String, login: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Text(stringResource(R.string.app_name), color = Color.White, modifier = Modifier.clickable { login() })
    }
}
