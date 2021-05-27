package com.dicoding.mysimplelogin

import org.koin.dsl.module

/**  menyiapkan inisialisasi SessionManager */

//Untuk membuat module perlu menggunakan fungsi module.
val storageModule = module {

    /** Jika parameter untuk membuat objek tersebut sudah di-provide, maka Anda bisa menggunakan get().
     * Jika Anda menulis get() namun belum ada fungsi yang mem-provide objek tersebut
     * maka akan muncul eror Could not create instance for ....*/

    //Untuk membuat objek perlu menggunakan fungsi factory
    factory {
        SessionManager(get())
    }

    //Untuk membuat objek dengan jenis singleton perlu menggunakan fungsi single
    single {
        UserRepository(get())
    }
}