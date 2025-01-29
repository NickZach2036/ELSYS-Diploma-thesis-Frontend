package com.example.elsysdiplomathesisfrontend

import com.example.elsysdiplomathesisfrontend.data.repository.AuthRepositoryImpl
import com.example.elsysdiplomathesisfrontend.data.service.AuthService
import com.example.elsysdiplomathesisfrontend.domain.repository.AuthRepository
import com.example.elsysdiplomathesisfrontend.ui.feature.loginscreen.LoginViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.DummyViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
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

    factory<AuthRepository> {
        AuthRepositoryImpl(get<AuthService>())
    }

    viewModel { DummyViewModel(get<AuthRepository>()) }

    viewModel { LoginViewModel() }

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

        val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        Retrofit.Builder().baseUrl("http://192.168.2.250:8080/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()
    }
}
