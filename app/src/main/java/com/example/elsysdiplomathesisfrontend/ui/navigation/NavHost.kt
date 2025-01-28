package com.example.elsysdiplomathesisfrontend.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.os.bundleOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.DummyScreen
import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.DummyViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.QRScannerScreenWithUI
import org.koin.androidx.compose.getViewModel

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
            QRScannerScreenWithUI {
                navController.navigate(Screen.DUMMY, bundleOf("id" to "123"))
            }
        }
        composable(Screen.DUMMY) { backStack ->
            val viewModel = getViewModel<DummyViewModel>()
            val state by viewModel.text.collectAsStateWithLifecycle()
            val value = backStack.arguments?.getString("id") ?: ""
            DummyScreen(stateValue = state, id = value, login = { viewModel.login() })
        }
    }
}
