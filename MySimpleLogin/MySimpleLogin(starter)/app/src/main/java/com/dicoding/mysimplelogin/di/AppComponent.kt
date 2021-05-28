package com.dicoding.mysimplelogin.di

import android.content.Context
import com.dicoding.mysimplelogin.HomeActivity
import com.dicoding.mysimplelogin.MainActivity
import com.dicoding.mysimplelogin.StorageModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**  kode untuk inject pada UserRepository  */

//@Singleton ada karena di dalam object yang di-inject terdapat @Singleton juga. Jif not, eror -> IncompatiblyScopedBindings.
@Singleton //Jangan lupa untuk menambahkan annotation @Singleton pada Component juga
@Component(modules = [StorageModule::class]) //Untuk buat component need annotation @Component. if not, eror -> @Component.Factory types must be nested within a @Component.
                      //Untuk menambahkan module di dalam parameter modules harus menggunakan format Array
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
                    //Jika ada objek yang membutuhkan Context, Anda perlu menambahkan @BindsInstance. if not, eror -> MissingBinding Context.
    }

    fun inject(activity: MainActivity)
    fun inject(activity: HomeActivity)
}

/**
 * Selanjutnya klik Build → Make Project atau menggunakan icon palu.
 * Langkah ini digunakan supaya Dagger men-generate kode yang ada pada AppComponent dan
 * membuat kelas DaggerAppComponent secara otomatis.
 * */