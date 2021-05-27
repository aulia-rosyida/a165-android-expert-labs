package com.dicoding.mysimplelogin

import javax.inject.Inject
import javax.inject.Singleton

/** tambahkan injection pada constructor dan scope singleton.
 * Dengan begitu Anda bisa menghapus kode untuk membuat Singleton yang secara manual.*/

@Singleton
class UserRepository @Inject constructor(private val sesi: SessionManager) {

    fun loginUser(username: String) {
        sesi.createLoginSession()
        sesi.saveToPreference(SessionManager.KEY_USERNAME, username)
    }

    fun getUser() = sesi.getFromPreference(SessionManager.KEY_USERNAME)

    fun isUserLogin() = sesi.isLogin

    fun logoutUser() = sesi.logout()
}