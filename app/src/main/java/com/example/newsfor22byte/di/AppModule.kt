package com.example.newsfor22byte.di

import com.example.newsfor22byte.common.Constants.BASE_URL
import com.example.newsfor22byte.data.api.ApiNews
import com.example.newsfor22byte.data.repository.BreakingNewsRepositoryImp
import com.example.newsfor22byte.domain.repository.BreakingNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideNewsApi(): ApiNews = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiNews::class.java)


    @Provides
    @Singleton
    fun provideBreakingNewsRepository(api: ApiNews): BreakingNewsRepository = BreakingNewsRepositoryImp(api)


}