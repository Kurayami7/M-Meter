package com.coderops.repository.mappers.domain

import com.coderops.entities.movieDetails.CastEntity
import com.coderops.entities.movieDetails.CreditsEntity
import com.coderops.entities.movieDetails.CrewEntity
import com.coderops.entities.movieDetails.MovieDetailsEntity
import com.coderops.entities.movieDetails.MovieVideoEntity
import com.coderops.entities.movieDetails.RecommendationsEntity
import com.coderops.entities.movieDetails.RecommendedMovieEntity
import com.coderops.entities.movieDetails.ReviewEntity
import com.coderops.entities.movieDetails.ReviewResponseEntity
import com.coderops.entities.movieDetails.VideosEntity
import com.coderops.remote.response.movieDetails.Credits
import com.coderops.remote.response.movieDetails.MovieDetailsDto
import com.coderops.remote.response.movieDetails.Recommendations
import com.coderops.remote.response.movieDetails.ReviewsDto
import com.coderops.remote.response.movieDetails.Videos
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainMovieDetailsMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetailsEntity> {
    override fun map(input: MovieDetailsDto): MovieDetailsEntity {
        return MovieDetailsEntity(
            backdropPath = BuildConfig.IMAGE_BASE_PATH + input.backdropPath,
            credits = mapCredits(input.credits),
            genres = input.genres?.map { it.name!! }?: emptyList(),
            id = input.id?:0,
            overview = input.overview?:"",
            recommendations = mapRecommendations(input.recommendations),
            title = input.title?:"",
            video = input.video?:false,
            videos = mapvideos(input.videos),
            voteAverage = input.voteAverage?:0.0,
            reviewEntity = mapReviews(input.reviews),
            year = input.releaseDate ?: ""
        )
    }

    private fun mapCredits(credits: Credits?): CreditsEntity {
        return CreditsEntity(
            cast = credits?.cast?.map {
                CastEntity(
                    adult = it.adult?:false,
                    castId = it.castId?:0,
                    character = it.character?:"",
                    creditId = it.creditId?:"",
                    gender = it.gender?:0,
                    id = it.id?:0,
                    knownForDepartment = it.knownForDepartment?:"",
                    name = it.name?:"",
                    order = it.order?:0,
                    originalName = it.originalName?:"",
                    popularity = it.popularity?:0.0,
                    profilePath = BuildConfig.IMAGE_BASE_PATH+it.profilePath
                )
            }?: emptyList(),
            crew = credits?.crew?.map {
                CrewEntity(
                    adult = it.adult?:false,
                    creditId = it.creditId?:"",
                    department = it.department?:"",
                    gender = it.gender?:0,
                    id = it.id?:0,
                    knownForDepartment = it.knownForDepartment?:"",
                    name = it.name?:"",
                    job = it.job?:"",
                    originalName = it.originalName?:"",
                    popularity = it.popularity?:0.0,
                    profilePath = it.profilePath?:""
                )
            }?: emptyList(),
        )
    }

    private fun mapRecommendations(recommendations: Recommendations?): RecommendationsEntity {
        return RecommendationsEntity(
            page = recommendations?.page?:0,
            recommendedMovies = recommendations?.recommendedMovies?.map {
                RecommendedMovieEntity(
                    adult = it.adult?:false,
                    backdropPath = BuildConfig.IMAGE_BASE_PATH+it.backdropPath,
                    genreIds = it.genreIds?: emptyList(),
                    id = it.id?:0,
                    mediaType = it.mediaType?:"",
                    originalLanguage = it.originalLanguage?:"",
                    originalTitle = it.originalTitle?:"",
                    overview = it.overview?:"",
                    popularity = it.popularity?:0.0,
                    posterPath = BuildConfig.IMAGE_BASE_PATH+it.posterPath,
                    releaseDate = it.releaseDate?:"",
                    title = it.title?:"",
                    video = it.video?:false,
                    voteAverage = it.voteAverage?:0.0,
                    voteCount = it.voteCount?:0,
                )
            }?: emptyList(),
            totalPages = recommendations?.totalPages?:0,
            totalResults = recommendations?.totalResults?:0
        )
    }

    private fun mapvideos(videos: Videos?): VideosEntity {
        return VideosEntity(
            results = videos?.results?.map {
                MovieVideoEntity(
                    id =it.id?:"",
                    iso31661 =it.iso31661?:"",
                    iso6391 =it.iso6391?:"",
                    key =it.key?:"",
                    name =it.name?:"",
                    official =it.official?:false,
                    publishedAt =it.publishedAt?:"",
                    site =it.site?:"",
                    size =it.size?:0,
                    type =it.type?:""
                )
            }?: emptyList()
        )
    }

    private fun mapReviews(reviews: ReviewsDto?): ReviewResponseEntity{
        return ReviewResponseEntity(
            reviews = reviews?.results?.map {
                ReviewEntity(
                    name = it.author?:"",
                    avatar_path = it.authorDetails?.avatarPath?:"",
                    content = it.content?:"",
                    created_at = it.createdAt?:""
                )
            }?: emptyList(),
            page = reviews?.page?:0,
            totalResults = reviews?.totalResults?:0,
            totalPages = reviews?.totalPages?:0

        )
    }
}