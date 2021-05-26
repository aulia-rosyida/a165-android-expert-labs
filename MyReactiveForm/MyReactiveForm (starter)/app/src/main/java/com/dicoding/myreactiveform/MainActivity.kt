package com.dicoding.myreactiveform

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dicoding.myreactiveform.databinding.ActivityMainBinding

/**
Terdapat beberapa masalah yang dapat diatasi dengan Reactive Programming, yaitu seperti :
1. Menggunakan flag(tanda) berupa boolean untuk menentukan suatu form valid atau tidak,
dalam hal ini yaitu emailValid, passwordValid, passwordConfirmationValid.
Sehingga, harus meng-update dan memeriksa satu per satu flag tersebut.

2. Terdapat tiga TextWatcher yang semuanya memiliki beberapa method kosong
yang hanya sebagai syarat callback dari TextWatcher.

3. Kode untuk UI dan logic bercampur satu sama lain.
 * */
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun showEmailExistAlert(isNotValid: Boolean) {
        binding.edEmail.error = if (isNotValid) getString(R.string.email_not_valid) else null
    }

    private fun showPasswordMinimalAlert(isNotValid: Boolean) {
        binding.edPassword.error = if (isNotValid) getString(R.string.password_not_valid) else null
    }

    private fun showPasswordConfirmationAlert(isNotValid: Boolean) {
        binding.edConfirmPassword.error = if (isNotValid) getString(R.string.password_not_same) else null
    }
}
