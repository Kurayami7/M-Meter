package com.coderops.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coderops.local.database.dto.GenresMoviesLocalDto
import com.coderops.local.database.dto.GenresTvsLocalDto
import com.coderops.local.database.dto.PopularPeopleLocalDto
import com.coderops.local.database.dto.ProfileLocalDto
import com.coderops.local.database.dto.SearchHistoryLocalDto
import com.coderops.local.database.dto.UserLocalDto
import com.coderops.local.database.dto.movie.MovieInWatchHistoryLocalDto
import com.coderops.local.database.dto.movie.MovieLocalDto
import com.coderops.local.database.dto.movie.NowPlayingMovieLocalDto
import com.coderops.local.database.dto.movie.PopularMovieLocalDto
import com.coderops.local.database.dto.movie.RecommendedMovieLocalDto
import com.coderops.local.database.dto.movie.TopRatedMovieLocalDto
import com.coderops.local.database.dto.movie.TrendingMoviesLocalDto
import com.coderops.local.database.dto.movie.UpcomingMovieLocalDto


@Database(
    entities = [
        PopularMovieLocalDto::class,
        TopRatedMovieLocalDto::class,
        UpcomingMovieLocalDto::class,
        NowPlayingMovieLocalDto::class,
        RecommendedMovieLocalDto::class,
        TrendingMoviesLocalDto::class,
        PopularPeopleLocalDto::class,
        SearchHistoryLocalDto::class,
        GenresMoviesLocalDto::class,
        ProfileLocalDto::class,
        GenresTvsLocalDto::class,
        MovieLocalDto::class,
        UserLocalDto::class,
        MovieInWatchHistoryLocalDto::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Convertors::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val triviaDao: TriviaDao
}