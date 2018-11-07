
package com.abhijit.sample.features.movies

import com.abhijit.sample.core.exception.Failure.FeatureFailure

class MovieFailure {
    class ListNotAvailable: FeatureFailure()
    class NonExistentMovie: FeatureFailure()
}

