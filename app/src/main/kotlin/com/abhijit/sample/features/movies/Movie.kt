
package com.abhijit.sample.features.movies

import com.abhijit.sample.core.extension.empty

data class Movie(val id: Int, val poster: String) {

    companion object {
        fun empty() = Movie(0, String.empty())
    }
}
