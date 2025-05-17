package com.example.elsysdiplomathesisfrontend

import com.example.elsysdiplomathesisfrontend.data.TokenInterceptor
import com.example.elsysdiplomathesisfrontend.data.repository.AuthRepositoryImpl
import com.example.elsysdiplomathesisfrontend.data.repository.CommentRepositoryImpl
import com.example.elsysdiplomathesisfrontend.data.repository.LandmarkRepositoryImpl
import com.example.elsysdiplomathesisfrontend.data.service.AuthService
import com.example.elsysdiplomathesisfrontend.data.service.CommentService
import com.example.elsysdiplomathesisfrontend.data.service.LandmarkService
import com.example.elsysdiplomathesisfrontend.data.store.DataStoreManager
import com.example.elsysdiplomathesisfrontend.data.store.DataStoreManagerImpl
import com.example.elsysdiplomathesisfrontend.domain.repository.AuthRepository
import com.example.elsysdiplomathesisfrontend.domain.repository.CommentRepository
import com.example.elsysdiplomathesisfrontend.domain.repository.LandmarkRepository
import com.example.elsysdiplomathesisfrontend.ui.feature.landmarkbyuserscreen.LandmarkByUserViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.loginscreen.LoginViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner.DummyViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.signupscreen.SignUpViewModel
import com.example.elsysdiplomathesisfrontend.ui.feature.stationscreen.StationViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
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

    single<CommentService> {
        get<Retrofit>().create(CommentService::class.java)
    }

    factory<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }

    factory<LandmarkRepository> {
        LandmarkRepositoryImpl(get())
    }

    factory<CommentRepository> {
        CommentRepositoryImpl(get())
    }

    viewModel { DummyViewModel(get()) }

    viewModel { LoginViewModel(get()) }

    viewModel { SignUpViewModel(get()) }

    viewModel { StationViewModel(get(), get() ) }

    viewModel { LandmarkByUserViewModel(get()) }

    single <DataStoreManager> {
        DataStoreManagerImpl(androidContext().dataStore)
    }

    single<OkHttpClient> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(TokenInterceptor(get<DataStoreManager>()))
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