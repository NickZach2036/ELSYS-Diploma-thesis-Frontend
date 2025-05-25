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
import com.example.elsysdiplomathesisfrontend.ui.feature.loginscreen.LoginScreen
import com.example.elsysdiplomathesisfrontend.ui.feature.loginscreen.LoginViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.DummyScreen
import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.DummyViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.QRScannerScreenWithUI
import com.example.elsysdiplomathesisfrontend.ui.feature.signupscreen.SignUpScreen
import com.example.elsysdiplomathesisfrontend.ui.feature.signupscreen.SignUpViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ThNavHost(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = Screen.QR_SCANNER,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }

    ) {
        composable(Screen.QR_SCANNER) {
            QRScannerScreenWithUI(onQRScanned = {
                navController.navigate(
                    route = Screen.DUMMY, args = bundleOf("id" to "123")
                )
            }, onLoginClicked = {
                navController.navigate(
                    route = Screen.LOGIN
                )
            })
        }

        composable(Screen.DUMMY) { backStack ->
            val viewModel = getViewModel<DummyViewModel>()
            val state by viewModel.text.collectAsStateWithLifecycle()
            val value = backStack.arguments?.getString("id") ?: ""
            DummyScreen(stateValue = state, id = value, login = { viewModel.login() })
        }

        composable(Screen.LOGIN) { backStack ->
            val viewModel = getViewModel<LoginViewModel>()
            val state by viewModel.loginData.collectAsStateWithLifecycle()

            LoginScreen(
                stateValue = state,
                onLoginClicked = {
                    navController.navigate(
                        route = Screen.QR_SCANNER
                    )
                },
                onUsernameChange = { username -> viewModel.updateUsername(username) },
                onPasswordChange = { password -> viewModel.updatePassword(password) },
                onVisibilityChange = { isVisible -> viewModel.updateVisibility(isVisible) },
                onSignUpClicked = {
                    navController.navigate(
                        route = Screen.SIGN_UP
                    )
                },
            )

        }

        composable(Screen.SIGN_UP) { backStack ->
            val viewModel = getViewModel<SignUpViewModel>()
            val state by viewModel.signUpData.collectAsStateWithLifecycle()

            SignUpScreen(
                stateValue = state,
                onUsernameChange = { username -> viewModel.updateUsername(username) },
                onPasswordChange = { password -> viewModel.updatePassword(password) },
                onVisibilityChange = { isVisible -> viewModel.updateVisibility(isVisible) },
                onSignUpClicked = {
                    navController.navigate(
                        route = Screen.QR_SCANNER
                    )
                },
            )
        }
    }
}
