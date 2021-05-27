package com.dicoding.mysimplelogin

import org.koin.dsl.module

/**  menyiapkan inisialisasi SessionManager */
val storageModule = module {
    factory {
        SessionManager(get())
    }

    single {
        UserRepository(get())
    }
}