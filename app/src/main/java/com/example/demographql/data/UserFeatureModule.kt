package com.example.demographql.data

import dagger.Binds
import dagger.Module

@Module
abstract class UserFeatureModule {

    @Binds
    abstract fun provideUserFeature(userFeature: UserFeature): UserFeature
}
