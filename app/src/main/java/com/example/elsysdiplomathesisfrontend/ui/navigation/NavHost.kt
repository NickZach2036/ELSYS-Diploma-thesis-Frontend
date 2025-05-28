package com.example.elsysdiplomathesisfrontend.ui.navigation

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.os.bundleOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elsysdiplomathesisfrontend.ui.feature.landmarkbyuserscreen.LandmarkByUserScreen
import com.example.elsysdiplomathesisfrontend.ui.feature.landmarkbyuserscreen.LandmarkByUserViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.loginscreen.LoginScreen
import com.example.elsysdiplomathesisfrontend.ui.feature.loginscreen.LoginViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.QRScannerScreenWithUI
import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.QRScannerViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.signupscreen.SignUpScreen
import com.example.elsysdiplomathesisfrontend.ui.feature.signupscreen.SignUpViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.stationscreen.StationScreen
import com.example.elsysdiplomathesisfrontend.ui.feature.stationscreen.StationViewModel
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
        composable(Screen.QR_SCANNER) { backStack ->
            val isLogged = backStack.arguments?.getBoolean("isLogged") ?: false
            val viewModel = getViewModel<QRScannerViewModel>()
            val state by viewModel.isLoggedIn.collectAsStateWithLifecycle()

            LaunchedEffect(null) { viewModel.getState(isLogged) }

            QRScannerScreenWithUI(onQRScanned = { scannedValue ->
                navController.navigate(Screen.STATION, bundleOf("stationId" to scannedValue))
            }, onLoginClicked = {
                navController.navigate(Screen.LOGIN)
            }, onAddLandmarkClicked = {
                navController.navigate(Screen.LANDMARK_BY_USER)
            }, isLoggedIn = state)
        }

        composable(Screen.STATION) { backStack ->
            val stationId = backStack.arguments?.getString("stationId") ?: ""
            val viewModel = getViewModel<StationViewModel>()
            val state by viewModel.stationData.collectAsStateWithLifecycle()

            LaunchedEffect(null) {
                stationId.toIntOrNull()?.let { viewModel.loadStationData(it) }
            }

            StationScreen(
                navController = navController,
                stationData = state,
                bottomSheetVisibility = { isBottomScreenVisible, landmarkId ->
                    viewModel.bottomSheetVisibility(
                        isBottomScreenVisible,
                        landmarkId
                    )
                },
            )
        }

        composable(Screen.LANDMARK_BY_USER) { backStack ->
            val viewModel = getViewModel<LandmarkByUserViewModel>()
            val state by viewModel.landmarkByUserData.collectAsStateWithLifecycle()

            LandmarkByUserScreen(
                stateValue = state,
                onNameChange = { name -> viewModel.updateName(name) },
                onDescriptionChange = { description -> viewModel.updateDescription(description) },
                userAddLandmark = { viewModel.userAddLandmark() },
                onDropdownVisibility = { isDropdownVisible -> viewModel.onVisibilityDropdownMenu(isDropdownVisible) },
                onLandmarkClick = { stationId -> viewModel.onDropClick(stationId) }
            )
        }

        composable(Screen.LOGIN) { backStack ->
            val viewModel = getViewModel<LoginViewModel>()
            val state by viewModel.loginData.collectAsStateWithLifecycle()

            LoginScreen(
                stateValue = state,
                onLoginClicked = {
                    viewModel.login(
                        onSuccess = {
                            navController.navigate(Screen.QR_SCANNER, bundleOf("isLogged" to true) )
                        },
                        onError = { error ->
                            Log.e("LoginScreen", "Login failed: $error")
                        }
                    )
                },
                onUsernameChange = { username -> viewModel.updateUsername(username) },
                onPasswordChange = { password -> viewModel.updatePassword(password) },
                onVisibilityChange = { isVisible -> viewModel.updateVisibility(isVisible) },
                onSignUpClicked = {
                    navController.navigate(
                        route = Screen.LANDMARK_BY_USER
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
                    viewModel.register(
                        onSuccess = {
                            navController.navigate(Screen.QR_SCANNER) {
                                popUpTo(Screen.SIGN_UP) { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                        onError = { error ->
                            Log.e("SignUpScreen", "Registration failed: $error")
                        }
                    )
                },
            )
        }
    }
}
