package com.dicoding.mysimplelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.auliarosyida.core.UserRepository
import com.dicoding.mysimplelogin.databinding.ActivityMainBinding
import javax.inject.Inject

/** Menambahkan fungsi Inject untuk setiap Activity/Fragment yang membutuhkan, dalam hal ini yaitu MainActivity dan HomeActivity. */
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    /** Sudah tidak ada lagi kode untuk inisialisasi Objek di sini.
     * Anda cukup menambahkan by inject() pada field yang ingin di-inject. */
    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var userRepository2: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this) //Jangan lupa untuk meng-inject component sebelum super.onCreate terlebih dahulu.

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepository.checkInstance()
        userRepository2.checkInstance()

        if (userRepository.isUserLogin()) {
            moveToHomeActivity()
        }

        binding.btnLogin.setOnClickListener {
            saveSession()
        }
    }

    private fun saveSession() {
        userRepository.loginUser(binding.edUsername.text.toString())
        moveToHomeActivity()
    }

    private fun moveToHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
