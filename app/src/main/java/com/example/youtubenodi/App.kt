package com.example.youtubenodi

import android.app.Application
import com.example.youtubenodi.repository.Repository

class App : Application() {
    val repository: Repository by lazy {
        Repository()
    }
}