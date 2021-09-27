package com.example.room.views

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp(MultiDexApplication::class)
class TaskApp:Application() {
}