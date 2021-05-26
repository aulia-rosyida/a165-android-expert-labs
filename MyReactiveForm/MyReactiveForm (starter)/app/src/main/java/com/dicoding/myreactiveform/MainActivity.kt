package com.dicoding.myreactiveform

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dicoding.myreactiveform.databinding.ActivityMainBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.functions.Function3

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

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Anda membuat variabel data stream baru saat melakukan subscribe
         *
         * mengapa perlu dibuat variabel seperti emailStream, passwordStream, dll?
         * Hal ini karena Anda memerlukan data stream tersebut untuk menentukan Button aktif atau tidak.
         * Ingat bahwa Button aktif ketika semua validasi tidak menghasilkan eror
         * */

        //data stream dari inputan Email dilanjutkan dengan subscribe ke stream tersebut
        val emailStream = RxTextView.textChanges(binding.edEmail) /**untuk membaca setiap perubahan pada EditText dan mengubahnya menjadi data stream. */
            .skipInitialValue() /**  operator skipInitialValue() untuk menghiraukan input awal.
                                        Hal ini bertujuan supaya aplikasi tidak langsung menampilkan eror pada saat pertama kali dijalankan. */
            .map { email -> //operator map dan memeriksa apakah format valid.
                            // Jika format tidak valid maka ia akan mengembalikan nilai TRUE
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            showEmailExistAlert(it) // saat subscribe, kita memanggil fungsi showEmailExistAlert(it) untuk menampilkan peringatan jika hasilnya TRUE.
        }

        /**
        emailStream.subscribe { isValid ->
        showEmailExistAlert(isValid)
        }
         * */

        //hal yang sama untuk inputan password
        val passwordStream = RxTextView.textChanges(binding.edPassword)
            .skipInitialValue()
            .map { password ->
                password.length < 6
            }
        passwordStream.subscribe {
            showPasswordMinimalAlert(it)
        }

        //confirm password sedikit berbeda, karena perlu mengecek  pada dua inputan sekaligus,
        // yaitu pada ed_password dan ed_confirm_password.
        // Karena itulah kita perlu menggabungkan dua data tersebut dengan operator merge
        val passwordConfirmationStream = Observable.merge( /** Menggabungkan Observable dengan Merge.  operator merge hanya menggabungkan datanya saja */
            RxTextView.textChanges(binding.edPassword)
                .map { password ->
                    password.toString() != binding.edConfirmPassword.text.toString()
                },
            RxTextView.textChanges(binding.edConfirmPassword)
                .map { confirmPassword ->
                    confirmPassword.toString() != binding.edPassword.text.toString()
                }
        )
        passwordConfirmationStream.subscribe {
            showPasswordConfirmationAlert(it)
        }


        /**  operator combineLatest untuk menggabungkan ketiga data stream dan menghasilkan 1 output data stream baru
         *   Dalam kasus ini, Anda mengubah inputan dari ketiga data stream.
         *   Di mana apabila semua inputan valid, maka akan menghasilkan nilai baru, yaitu TRUE.
         * */

        val invalidFieldsStream = Observable.combineLatest( /**combineLatest kita menggabungkan dan mengubah data di dalamnya */
            emailStream,
            passwordStream,
            passwordConfirmationStream,
            Function3 { emailInvalid: Boolean, passwordInvalid: Boolean, passwordConfirmationInvalid: Boolean ->
                !emailInvalid && !passwordInvalid && !passwordConfirmationInvalid
            })
        invalidFieldsStream.subscribe { isValid ->
            if (isValid) {
                binding.btnRegister.isEnabled = true
                binding.btnRegister.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
            } else {
                binding.btnRegister.isEnabled = false
                binding.btnRegister.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
            }
        }
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
