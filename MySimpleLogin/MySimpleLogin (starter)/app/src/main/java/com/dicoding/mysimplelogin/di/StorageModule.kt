package com.dicoding.mysimplelogin

import android.content.Context
import dagger.Module
import dagger.Provides
//import org.koin.dsl.module

/**  menyiapkan inisialisasi SessionManager
 *
 *  bagaimana untuk inisialisasi UserRepository?
 *  Kita akan melakukan injection pada constructor sehingga tidak perlu di-provide di module ini*/
@Module
class StorageModule {
    @Provides
    fun provideSessionManager(context: Context): SessionManager = SessionManager(context)
}