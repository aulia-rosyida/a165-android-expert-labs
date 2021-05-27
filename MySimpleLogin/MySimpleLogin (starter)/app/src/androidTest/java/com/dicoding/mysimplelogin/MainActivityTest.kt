package com.dicoding.mysimplelogin

import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class MainActivityTest : KoinTest {

    private val userRepository: UserRepository by inject()
    private val username = "dicoding"

    /** Untuk melakukan Testing pada Koin Anda perlu memanggil module pada koin dengan menggunakan loadKoinModules.
     * Dan jika testing sudah usai Anda dapat menutupnya dengan menggunakan stopKoin.*/
    @Before
    fun before() {
        loadKoinModules(storageModule)
        userRepository.loginUser(username)
    }

    @After
    fun after() {
        stopKoin()
    }

    /**  testing untuk memastikan nilai Repository yang artinya injection berjalan dengan baik. */
    @Test
    fun getUsernameFromRepository() {
        val requestedUsername = userRepository.getUser()
        assertEquals(username, requestedUsername)
    }
}