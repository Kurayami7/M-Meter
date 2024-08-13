package com.coderops.mmeter.di

import com.coderops.bases.NavigationRes
import com.coderops.bases.StringsRes
import com.coderops.mmeter.resourses_helper.NavigationResImpl
import com.coderops.mmeter.resourses_helper.StringsResImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourcesModule {

    @Binds
    @Singleton
    abstract fun bindStringsRes(stringsResImpl: StringsResImpl): StringsRes

    @Binds
    @Singleton
    abstract fun bindNavigationRes(navigationResImpl: NavigationResImpl): NavigationRes
}