
package com.abhijit.sample.core.di

import com.abhijit.sample.AndroidApplication
import com.abhijit.sample.core.di.viewmodel.ViewModelModule
import com.abhijit.sample.features.movies.MovieDetailsFragment
import com.abhijit.sample.features.movies.MoviesFragment
import com.abhijit.sample.core.navigation.RouteActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}
