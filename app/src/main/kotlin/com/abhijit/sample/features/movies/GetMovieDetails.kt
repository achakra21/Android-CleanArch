
package com.abhijit.sample.features.movies

import com.abhijit.sample.features.movies.GetMovieDetails.Params
import com.abhijit.sample.core.interactor.UseCase
import javax.inject.Inject

class GetMovieDetails
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<MovieDetails, Params>() {

    override suspend fun run(params: Params) = moviesRepository.movieDetails(params.id)

    data class Params(val id: Int)
}
