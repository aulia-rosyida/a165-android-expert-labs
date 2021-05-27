package com.dicoding.mysimplelogin

import android.app.Application
import com.dicoding.mysimplelogin.di.AppComponent
import com.dicoding.mysimplelogin.di.DaggerAppComponent

/** Kelas ini berfungsi sebaga supaya kita bisa mengimplementasikan Dagger di semua kelas dengan membuat custom Application.
 *
 * kode untuk inject*/
open class MyApplication : Application(){
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(
            applicationContext
        )
    }
}