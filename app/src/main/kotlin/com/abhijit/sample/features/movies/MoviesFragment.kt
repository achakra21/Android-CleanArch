
package com.abhijit.sample.features.movies

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.abhijit.sample.core.platform.BaseFragment
import com.abhijit.sample.R
import com.abhijit.sample.features.movies.MovieFailure.ListNotAvailable
import com.abhijit.sample.core.exception.Failure
import com.abhijit.sample.core.exception.Failure.NetworkConnection
import com.abhijit.sample.core.exception.Failure.ServerError
import com.abhijit.sample.core.extension.failure
import com.abhijit.sample.core.extension.invisible
import com.abhijit.sample.core.extension.observe
import com.abhijit.sample.core.extension.viewModel
import com.abhijit.sample.core.extension.visible
import com.abhijit.sample.core.navigation.Navigator
import kotlinx.android.synthetic.main.fragment_movies.emptyView
import kotlinx.android.synthetic.main.fragment_movies.movieList
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var moviesAdapter: MoviesAdapter

    private lateinit var moviesViewModel: MoviesViewModel

    override fun layoutId() = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        moviesViewModel = viewModel(viewModelFactory) {
            observe(movies, ::renderMoviesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMoviesList()
    }


    private fun initializeView() {
        movieList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        movieList.adapter = moviesAdapter
        moviesAdapter.clickListener = { movie, navigationExtras ->
                    navigator.showMovieDetails(activity!!, movie, navigationExtras) }
    }

    private fun loadMoviesList() {
        emptyView.invisible()
        movieList.visible()
        showProgress()
        moviesViewModel.loadMovies()
    }

    private fun renderMoviesList(movies: List<MovieView>?) {
        moviesAdapter.collection = movies.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_server_error)
            is ListNotAvailable -> renderFailure(R.string.failure_movies_list_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        movieList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadMoviesList)
    }
}
