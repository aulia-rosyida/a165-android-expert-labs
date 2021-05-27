package com.dicoding.mysimplelogin.di

import android.content.Context
import com.dicoding.mysimplelogin.HomeActivity
import com.dicoding.mysimplelogin.MainActivity
import com.dicoding.mysimplelogin.StorageModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: HomeActivity)
}

/**
 * Selanjutnya klik Build → Make Project atau menggunakan icon palu.
 * Langkah ini digunakan supaya Dagger men-generate kode yang ada pada AppComponent dan
 * membuat kelas DaggerAppComponent secara otomatis.
 * */