package com.dicoding.mysimplelogin

import android.content.Context
import com.dicoding.auliarosyida.core.SessionManager
import dagger.Module
import dagger.Provides

/**  menyiapkan inisialisasi SessionManager
 *
 *  bagaimana untuk inisialisasi UserRepository?
 *  Kita akan melakukan injection pada constructor sehingga tidak perlu di-provide di module ini*/

//Untuk membuat module perlu annotation @Module. if not eror -> @Provides methods can only be present within a @Module.
@Module
class StorageModule {

    //Untuk membuat fungsi untuk inisialisasi perlu annotation @Provides. if not. eror -> MissingBinding SessionManager.
    @Provides
    fun provideSessionManager(context: Context): SessionManager = SessionManager(context)
    //Nama fungsi tidak berpengaruh, boleh apa pun, yang penting nilai kembaliannya.
    //Jika Anda membutuhkan Context, Anda cukup menambahkan @BindsInstance pada Component nantinya.
}