package com.dicoding.auliarosyida.core

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

/** tambahkan injection pada constructor dan scope singleton.
 * Dengan begitu Anda bisa menghapus kode untuk membuat Singleton yang secara manual.
 *
 * Karena UserRepository hanya membutuhkan SessionManager — dan ingat bahwa SessionManager sudah kita provide di dalam module —,
 * maka Anda dapat menggunakan Injection pada constructor.
 * Caranya yaitu dengan menambahkan @Inject constructor.
 * if not, eror -> MissingBinding UserRepository.*/

@Singleton //tidak ada lagi fungsi getInstance. Anda cukup menambahkan annotation @Singleton
class UserRepository @Inject constructor(private val sesi: SessionManager) {

    /** belajar memeriksa apakah benar object UserRepository yang dibuat pada latihan ini
     * benar-benar sesuai dengan pengertian Singleton, yakni hanya satu dan sama.*/

    // untuk mencetak instance pada Logcat.
    fun checkInstance() = Log.d("Singleton", "checkInstance: $this")

    fun loginUser(username: String) {
        sesi.createLoginSession()
        sesi.saveToPreference(SessionManager.KEY_USERNAME, username)
    }

    fun getUser() = sesi.getFromPreference(SessionManager.KEY_USERNAME)

    fun isUserLogin() = sesi.isLogin

    fun logoutUser() = sesi.logout()
}