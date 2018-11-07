
package com.abhijit.sample.features.movies

import android.content.Context
import com.abhijit.sample.features.movies.PlayMovie.Params
import com.abhijit.sample.core.exception.Failure
import com.abhijit.sample.core.functional.Either
import com.abhijit.sample.core.functional.Either.Right
import com.abhijit.sample.core.interactor.UseCase
import com.abhijit.sample.core.interactor.UseCase.None
import com.abhijit.sample.core.navigation.Navigator
import javax.inject.Inject

class PlayMovie
@Inject constructor(private val context: Context,
                    private val navigator: Navigator) : UseCase<None, Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openVideo(context, params.url)
        return Right(None())
    }

    data class Params(val url: String)
}
