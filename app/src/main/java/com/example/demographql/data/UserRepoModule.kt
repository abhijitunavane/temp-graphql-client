package com.example.demographql.data

import dagger.Binds
import dagger.Module

@Module
abstract class UserRepoModule {

    @Binds
    abstract fun provideUserRepository(userRepository: UserRepository): UserRepository
}
