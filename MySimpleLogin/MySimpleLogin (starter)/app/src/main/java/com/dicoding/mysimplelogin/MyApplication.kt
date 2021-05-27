package com.dicoding.mysimplelogin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/** Fungsinya yaitu supaya kita bisa mengimplementasikan Koin di semua kelas yang extend ke Application
 *
 * kode untuk inject*/
open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        /** startKoin di dalam custom application berguna untuk membuat Koin melakukan injection di semua turunan application,
         * seperti Activity dan Fragment. Jika tidak ada kode ini akan muncul eror No Koin Context configured.*/
        startKoin {

            /** androidContext berguna untuk mem-provide fungsi yang membutuhkan context.
             * Jika tidak maka akan muncul eror NoBeanDefFoundException: No definition found for class:'android.content.Context'.*/
            androidContext(this@MyApplication)
            modules(storageModule) //modules berguna untuk menambahkan module di dalam Koin.
                // Jika tidak ada maka akan muncul eror NoBeanDefFoundException pada komponen yang dipanggil pertama kali.
        }
    }
}