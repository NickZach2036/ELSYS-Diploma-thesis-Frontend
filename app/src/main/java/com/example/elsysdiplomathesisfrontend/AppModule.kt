package com.example.elsysdiplomathesisfrontend

import com.example.elsysdiplomathesisfrontend.data.repository.AuthRepositoryImpl
import com.example.elsysdiplomathesisfrontend.data.repository.LandmarkRepositoryImpl
import com.example.elsysdiplomathesisfrontend.data.service.AuthService
import com.example.elsysdiplomathesisfrontend.data.service.LandmarkService
import com.example.elsysdiplomathesisfrontend.domain.repository.AuthRepository
import com.example.elsysdiplomathesisfrontend.domain.repository.LandmarkRepository
import com.example.elsysdiplomathesisfrontend.ui.feature.loginscreen.LoginViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.DummyViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.signupscreen.SignUpViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.stationscreen.StationViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConstants {
    const val TIME = 60L
}

val appModules = module {
    single<AuthService> {
        get<Retrofit>().create(AuthService::class.java)
    }

    single<LandmarkService> {
        get<Retrofit>().create(LandmarkService::class.java)
    }

    factory<AuthRepository> {
        AuthRepositoryImpl(get())
    }

    factory<LandmarkRepository> {
        LandmarkRepositoryImpl(get())
    }

    viewModel { DummyViewModel(get()) }

    viewModel { LoginViewModel(get()) }

    viewModel { SignUpViewModel(get()) }

    viewModel { StationViewModel(get()) }

    single<OkHttpClient> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient().newBuilder().addInterceptor(interceptor)
            .connectTimeout(RetrofitConstants.TIME, TimeUnit.SECONDS)
            .readTimeout(RetrofitConstants.TIME, TimeUnit.SECONDS)
            .writeTimeout(RetrofitConstants.TIME, TimeUnit.SECONDS).build()
    }

    single {
        val okHttpClient = get<OkHttpClient>()
        Retrofit.Builder()
            .baseUrl("http://172.161.136.71:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}