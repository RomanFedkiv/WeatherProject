package com.example.weatherproject.di

import android.arch.persistence.room.Room
import com.example.weatherproject.remote.WeatherForecastApi
import com.example.weatherproject.cache.DbAbstract
import com.example.weatherproject.cache.WeatherForecastCacheImpl
import com.example.weatherproject.data.WeatherForecastCache
import com.example.weatherproject.data.WeatherForecastRemote
import com.example.weatherproject.data.WeatherForecastRepositoryImpl
import com.example.weatherproject.domain.WeatherForecastCacheUseCase
import com.example.weatherproject.domain.WeatherForecastRemoteUseCase
import com.example.weatherproject.domain.WeatherForecastRepository
import com.example.weatherproject.remote.WeatherForecastRemoteImpl
import com.example.weatherproject.remote.mapper.WeatherForecastMapper
import com.example.weatherproject.viewModel.WeatherForecastViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

    val viewModelModule = module {
        viewModel { WeatherForecastViewModel(useCaseCache = get(),useCaseRemote = get()) }

    }

    val cacheModule = module {
        single { Room.databaseBuilder(
            androidApplication(),
            DbAbstract::class.java, "weatherDb"
        ).build() }

        single { get<DbAbstract>().weatherForecastDao()}

        factory {   WeatherForecastCacheImpl(db = get()) as WeatherForecastCache }
    }

    val remoteModule = module {
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            retrofit.create(WeatherForecastApi::class.java)
        }

        factory { WeatherForecastMapper() }

        factory { WeatherForecastRemoteImpl(api = get(), mapper = get()) as WeatherForecastRemote }


    }

    val repositoryModule = module {
        factory { WeatherForecastRepositoryImpl(cache = get(),remote = get()) as WeatherForecastRepository }
    }

    val useCaseModule = module {
        factory {WeatherForecastCacheUseCase(repository = get())}
        factory { WeatherForecastRemoteUseCase(repository = get()) }
    }






