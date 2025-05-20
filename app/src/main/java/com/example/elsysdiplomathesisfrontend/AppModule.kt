package com.example.elsysdiplomathesisfrontend

import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.DummyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { DummyViewModel() }
}