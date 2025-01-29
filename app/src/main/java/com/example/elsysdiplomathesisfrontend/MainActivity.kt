package com.example.elsysdiplomathesisfrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.elsysdiplomathesisfrontend.ui.navigation.ThNavHost
import com.example.elsysdiplomathesisfrontend.ui.theme.ElsysDiplomaThesisFrontendTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHost = rememberNavController()
            ElsysDiplomaThesisFrontendTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        ThNavHost(navHost)
                    }
                }
            }
        }
    }
}
