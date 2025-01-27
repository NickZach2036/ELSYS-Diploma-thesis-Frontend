package com.example.elsysdiplomathesisfrontend.ui.theme.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elsysdiplomathesisfrontend.ui.theme.feature.qrscanner.DummyScreen
import com.example.elsysdiplomathesisfrontend.ui.theme.feature.qrscanner.QRScannerScreenWithUI

@Composable
fun ThNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.QR_SCANNER,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }

    ) {
        composable(Screen.QR_SCANNER) {
            QRScannerScreenWithUI(navController)
        }
        composable(Screen.DUMMY) {
            DummyScreen()
        }
    }


}

