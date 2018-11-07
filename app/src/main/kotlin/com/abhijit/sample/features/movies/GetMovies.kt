
package com.abhijit.sample.features.movies

import com.abhijit.sample.core.interactor.UseCase
import com.abhijit.sample.core.interactor.UseCase.None
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, None>() {

    override suspend fun run(params: None) = moviesRepository.movies()
}
