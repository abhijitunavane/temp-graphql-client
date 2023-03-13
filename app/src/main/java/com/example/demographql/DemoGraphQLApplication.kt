package com.example.demographql

import android.app.Application

open class DemoGraphQLApplication: Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().newComponent(this)
    }
}
