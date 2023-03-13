package com.example.demographql

import android.app.Application
import com.example.demographql.ui.UsersFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface ApplicationComponent {

    fun inject(usersFragment: UsersFragment)

    @Component.Factory
    interface Factory {

        fun newComponent(@BindsInstance application: Application): ApplicationComponent
    }
}
