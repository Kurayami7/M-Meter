package com.coderops.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.coderops.entities.EpisodeDetailsEntity
import com.coderops.entities.GenreEntity
import com.coderops.entities.MovieEntity
import com.coderops.entities.PeopleDetailsEntity
import com.coderops.entities.PeopleEntity
import com.coderops.entities.RatingEpisodeDetailsStatusEntity
import com.coderops.entities.ReviewEntity
import com.coderops.entities.SeasonEntity
import com.coderops.entities.StatusEntity
import com.coderops.entities.TVShowsEntity
import com.coderops.entities.TvDetailsInfoEntity
import com.coderops.entities.TvEntity
import com.coderops.entities.TvShowEntity
import com.coderops.entities.UserListEntity
import com.coderops.entities.YoutubeVideoDetailsEntity
import com.coderops.entities.movieDetails.MovieDetailsEntity
import com.coderops.entities.movieDetails.ReviewResponseEntity
import com.coderops.entities.myList.ListCreatedEntity
import com.coderops.entities.my_rated.MyRatedMovieEntity
import com.coderops.entities.my_rated.MyRatedTvShowEntity
import com.coderops.entities.season_details.SeasonDetailsEntity
import com.coderops.local.PreferenceStorage
import com.coderops.local.database.MovieDao
import com.coderops.local.database.dto.SearchHistoryLocalDto
import com.coderops.remote.request.AddMediaToListRequest
import com.coderops.remote.request.CreateUserListRequest
import com.coderops.remote.request.DeleteMovieRequest
import com.coderops.remote.request.FavoriteRequest
import com.coderops.remote.request.ListRequest
import com.coderops.remote.request.RateRequest
import com.coderops.remote.request.RatingEpisodeDetailsRequest
import com.coderops.remote.request.RatingRequest
import com.coderops.remote.request.WatchlistRequest
import com.coderops.remote.response.dto.YoutubeVideoDetailsRemoteDto
import com.coderops.remote.service.MovieService
import com.coderops.repository.mappers.cash.LocalGenresMovieMapper
import com.coderops.repository.mappers.cash.LocalGenresTvMapper
import com.coderops.repository.mappers.cash.LocalPopularPeopleMapper
import com.coderops.repository.mappers.cash.movie.LocalNowPlayingMovieMapper
import com.coderops.repository.mappers.cash.movie.LocalPopularMovieMapper
import com.coderops.repository.mappers.cash.movie.LocalTopRatedMovieMapper
import com.coderops.repository.mappers.cash.movie.LocalTrendingMoviesMapper
import com.coderops.repository.mappers.cash.movie.LocalUpcomingMovieMapper
import com.coderops.repository.mappers.domain.DomainGenreMapper
import com.coderops.repository.mappers.domain.DomainGenreTvMapper
import com.coderops.repository.mappers.domain.DomainMovieDetailsMapper
import com.coderops.repository.mappers.domain.DomainMoviesByPeopleMapper
import com.coderops.repository.mappers.domain.DomainPeopleDetailsMapper
import com.coderops.repository.mappers.domain.DomainPeopleMapper
import com.coderops.repository.mappers.domain.DomainPeopleRemoteMapper
import com.coderops.repository.mappers.domain.DomainReviewsMapper
import com.coderops.repository.mappers.domain.DomainSeasonDetailsMapper
import com.coderops.repository.mappers.domain.DomainStatusMapper
import com.coderops.repository.mappers.domain.DomainTvDetailsCreditMapper
import com.coderops.repository.mappers.domain.DomainTvDetailsMapper
import com.coderops.repository.mappers.domain.DomainTvDetailsReviewMapper
import com.coderops.repository.mappers.domain.DomainTvDetailsSeasonMapper
import com.coderops.repository.mappers.domain.DomainTvShowMapper
import com.coderops.repository.mappers.domain.DomainTvShowsByPeopleMapper
import com.coderops.repository.mappers.domain.DomainUserListsMapper
import com.coderops.repository.mappers.domain.DomainYoutubeDetailsMapper
import com.coderops.repository.mappers.domain.episode.DomainCastMapper
import com.coderops.repository.mappers.domain.episode.DomainEpisodeDetailsMapper
import com.coderops.repository.mappers.domain.episode.DomainRatingEpisodeMapper
import com.coderops.repository.mappers.domain.movie.DomainMovieMapper
import com.coderops.repository.mappers.domain.movie.DomainNowPlayingMovieMapper
import com.coderops.repository.mappers.domain.movie.DomainPopularMovieMapper
import com.coderops.repository.mappers.domain.movie.DomainTopRatedMovieMapper
import com.coderops.repository.mappers.domain.movie.DomainTrendingMoviesMapper
import com.coderops.repository.mappers.domain.movie.DomainTvMapper
import com.coderops.repository.mappers.domain.movie.DomainUpcomingMovieMapper
import com.coderops.repository.mappers.domain.showmore.PopularMoviesShowMorePagingSource
import com.coderops.repository.mappers.domain.showmore.TopRatedShowMorePagingSource
import com.coderops.repository.mappers.domain.showmore.TrendingShowMorePagingSource
import com.coderops.repository.my_rated.RatedMoviesPagingSource
import com.coderops.repository.my_rated.RatedTvShowPagingSource
import com.coderops.repository.tv_shows.AiringTodayTVShowsPagingSource
import com.coderops.repository.tv_shows.OnTheAirTVShowsPagingSource
import com.coderops.repository.tv_shows.PopularTVShowsPagingSource
import com.coderops.repository.tv_shows.TopRatedTVShowsPagingSource
import java.util.Random
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val ratedMoviesPagingSource: RatedMoviesPagingSource,
    private val ratedTvShowPagingSource: RatedTvShowPagingSource,
    private val airingTodayTvShowsPagingSource: AiringTodayTVShowsPagingSource,
    private val topRatedTvShowsPagingSource: TopRatedTVShowsPagingSource,
    private val onTheAirTVShowsPagingSource: OnTheAirTVShowsPagingSource,
    private val popularTVShowsPagingSource: PopularTVShowsPagingSource,
    private val preferenceStorage: PreferenceStorage,
    private val localGenresMovieMapper: LocalGenresMovieMapper,
    private val localGenresTvMapper: LocalGenresTvMapper,
    private val localPopularMovieMapper: LocalPopularMovieMapper,
    private val localPopularPeopleMapper: LocalPopularPeopleMapper,
    private val localNowPlayingMovieMapper: LocalNowPlayingMovieMapper,
    private val localTopRatedMovieMapper: LocalTopRatedMovieMapper,
    private val localTrendingMoviesMapper: LocalTrendingMoviesMapper,
    private val localUpcomingMovieMapper: LocalUpcomingMovieMapper,
    private val domainPopularMovieMapper: DomainPopularMovieMapper,
    private val domainNowPlayingMovieMapper: DomainNowPlayingMovieMapper,
    private val domainTopRatedMovieMapper: DomainTopRatedMovieMapper,
    private val domainUpcomingMovieMapper: DomainUpcomingMovieMapper,
    private val domainTrendingMovieMapper: DomainTrendingMoviesMapper,
    private val domainPeopleMapper: DomainPeopleMapper,
    private val domainGenreMapper: DomainGenreMapper,
    private val domainMovieDetailsMapper: DomainMovieDetailsMapper,
    private val domainStatusMapper: DomainStatusMapper,
    private val domainRatingEpisodeMapper: DomainRatingEpisodeMapper,
    private val domainCastMapper: DomainCastMapper,
    private val domainEpisodeDetailsMapper: DomainEpisodeDetailsMapper,
    private val domainGenreTvMapper: DomainGenreTvMapper,
    private val domainSeasonDetailsMapper: DomainSeasonDetailsMapper,
    private val domainPeopleRemoteMapper: DomainPeopleRemoteMapper,
    private val popularMovieMapperShowMore: PopularMoviesShowMorePagingSource,
    private val topRatedShowMorePagingSource: TopRatedShowMorePagingSource,
    private val trendingShowMorePagingSource: TrendingShowMorePagingSource,
    private val domainTvDetailsMapper: DomainTvDetailsMapper,
    private val domainYoutubeDetailsMapper: DomainYoutubeDetailsMapper,
    private val domainTvDetailsCreditMapper: DomainTvDetailsCreditMapper,
    private val domainTvDetailsReviewMapper: DomainTvDetailsReviewMapper,
    private val domainTvShowMapper: DomainTvShowMapper,
    private val domainTvDetailsSeasonMapper: DomainTvDetailsSeasonMapper,
    private val domainMovieMapper: DomainMovieMapper,
    private val domainTvMapper: DomainTvMapper,
    private val domainReviewsMapper: DomainReviewsMapper,
    private val domainUserListsMapper: DomainUserListsMapper,
    private val random: Random,
    private val domainPeopleDetailsMapper: DomainPeopleDetailsMapper,
    private val domainMoviesByPeopleMapper: DomainMoviesByPeopleMapper,
    private val tvShowsByPeopleMapper: DomainTvShowsByPeopleMapper,
) : BaseRepository(), MovieRepository {

    /// region showMore
    override suspend fun getPopularMoviesPaging(): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { popularMovieMapperShowMore }
        )
    }

    override suspend fun getTopRateMoviesPaging(): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { topRatedShowMorePagingSource }
        )
    }

    override suspend fun getTrendingMoviesPaging(): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { trendingShowMorePagingSource }
        )
    }
    /// endregion

    /// region movies
    override suspend fun getPopularMoviesFromDatabase(): List<MovieEntity> {
        return domainPopularMovieMapper.map(movieDao.getPopularMovies())
    }

    override suspend fun getPopularMoviesFromRemote(): List<MovieEntity> {
        val page = random.nextInt(500) + 1
        val moviesRemoteDTOs =
            wrapApiCall { movieService.getPopularMovies(page = page) }.results?.filterNotNull()
                ?: emptyList()
        val genres = getGenresMovies()
        return domainMovieMapper.map(moviesRemoteDTOs, genres)
    }

    override suspend fun refreshPopularMovies() {
        refreshWrapper(
            { movieService.getPopularMovies(random.nextInt(20) + 1) },
            localPopularMovieMapper::map,
            movieDao::insertPopularMovies,
            clearOldLocalData = movieDao::clearAllPopularMovies
        )
    }

    override suspend fun getNowPlayingMovies(): List<MovieEntity> {
        return domainNowPlayingMovieMapper.map(movieDao.getNowPlayingMovies())
    }

    override suspend fun refreshNowPlayingMovies() {
        refreshWrapper(
            { movieService.getNowPlayingMovies(random.nextInt(20) + 1) },
            localNowPlayingMovieMapper::map,
            movieDao::insertNowPlayingMovies,
            clearOldLocalData = movieDao::clearAllNowPlayingMovies
        )
    }

    override suspend fun getTopRatedMovies(): List<MovieEntity> {
        return domainTopRatedMovieMapper.map(movieDao.getTopRatedMovies())
    }

    override suspend fun refreshTopRatedMovies() {
        refreshWrapper(
            { movieService.getTopRatedMovies(random.nextInt(20) + 1) },
            localTopRatedMovieMapper::map,
            movieDao::insertTopRatedMovies,
            clearOldLocalData = movieDao::clearAllTopRatedMovies
        )
    }

    override suspend fun getUpcomingMovies(): List<MovieEntity> {
        return domainUpcomingMovieMapper.map(movieDao.getUpcomingMovies())
    }

    override suspend fun refreshUpcomingMovies() {
        refreshWrapper(
            { movieService.getUpcomingMovies(random.nextInt(20) + 1) },
            localUpcomingMovieMapper::map,
            movieDao::insertUpcomingMovies,
            clearOldLocalData = movieDao::clearAllUpcomingMovies
        )
    }

    override suspend fun getTrendingMovies(): List<MovieEntity> {
        return domainTrendingMovieMapper.map(movieDao.getTrendingMovies())
    }

    override suspend fun refreshTrendingMovies() {
        refreshWrapper(
            movieService::getTrendingMovies,
            localTrendingMoviesMapper::map,
            movieDao::insertTrendingMovies
        )
    }

    override suspend fun getPopularPeopleFromDatabase(): List<PeopleEntity> {
        return domainPeopleMapper.map(movieDao.getPopularPeople())
    }

    override suspend fun getPopularPeopleFromRemote(): List<PeopleEntity> {
        val page = random.nextInt(500) + 1
        val call =
            wrapApiCall { movieService.getPopularPeople(page = page) }.results?.filterNotNull()
                ?: emptyList()
        return domainPeopleRemoteMapper.map(call)
    }

    override suspend fun refreshPopularPeople() {
        refreshWrapper(
            { movieService.getPopularPeople(random.nextInt(20) + 1) },
            localPopularPeopleMapper::map,
            movieDao::insertPopularPeople,
            clearOldLocalData = movieDao::clearAllPopularPeople
        )
    }
    /// endregion

    /// region search history
    override suspend fun getSearchHistory(keyword: String): List<String> {
        return movieDao.getSearchHistory("%${keyword}%").map { it.keyword }

    }

    override suspend fun searchHistory(): List<String> {
        return movieDao.getSearchHistory().map { it.keyword }
    }

    override suspend fun insertSearchHistory(keyword: String) {
        return movieDao.insertSearchHistory(SearchHistoryLocalDto(keyword))
    }

    override suspend fun clearAllSearchHistory() {
        return movieDao.clearAllSearchHistory()
    }

    override suspend fun deleteSearchHistory(keyword: String) {
        movieDao.deleteSearchHistory(keyword)
    }
    /// endregion

    ///region search
    override suspend fun searchForMovies(keyword: String): List<MovieEntity> {
        val genresEntities = getGenresMovies()
        return wrapApiCall { movieService.searchForMovies(keyword) }.results
            ?.filterNotNull()?.let { movieDtos ->
                movieDtos.map { input ->
                    MovieEntity(
                        id = input.id ?: 0,
                        rate = input.voteAverage ?: 0.0,
                        title = input.title ?: "",
                        genreEntities = filterGenres(
                            input.genreIds?.filterNotNull() ?: emptyList(),
                            genresEntities
                        ),
                        imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
                        year = input.releaseDate ?: ""
                    )
                }
            } ?: emptyList()
    }

    override suspend fun searchForTv(keyword: String): List<TvEntity> {
        val genresTvEntities = getGenresTvs()
        return wrapApiCall { movieService.searchForTv(keyword) }.results
            ?.filterNotNull()?.let { tvDtos ->
                tvDtos.map { input ->
                    TvEntity(
                        id = input.id ?: 0,
                        title = input.name ?: "",
                        rate = input.voteAverage ?: 0.0,
                        imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
                        genreEntities = filterGenres(
                            input.genreIds?.filterNotNull() ?: emptyList(),
                            genresTvEntities
                        ),
                        year = input.firstAirDate ?: ""
                    )
                }
            } ?: emptyList()
    }

    override suspend fun searchForPeople(keyword: String): List<PeopleEntity> {
        return wrapApiCall { movieService.searchForPeople(keyword) }.results
            ?.filterNotNull()?.map {
                domainPeopleRemoteMapper.map(it)
            } ?: emptyList()

    }

    private fun filterGenres(
        genresIds: List<Int>,
        genresEntities: List<GenreEntity>
    ): List<GenreEntity> = genresEntities.filter { it.genreID in genresIds }
    //endregion

    /// region genres
    override suspend fun getGenresMovies(): List<GenreEntity> {
        return domainGenreMapper.map(movieDao.getGenresMovies())
    }

    override suspend fun refreshGenres() {
        try {
            wrapApiCall { movieService.getListOfGenresForMovies() }.results
                ?.let { remoteGenres ->
                    movieDao.insertGenresMovies(localGenresMovieMapper.map(remoteGenres))
                }

        } catch (_: Throwable) {
        }
    }


    override suspend fun getGenresTvs(): List<GenreEntity> {
        return domainGenreTvMapper.map(movieDao.getGenresTvs())
    }

    override suspend fun refreshGenresTv() {
        try {
            wrapApiCall { movieService.getListOfGenresForTvs() }.results
                ?.let { remoteGenres ->
                    movieDao.insertGenresTvs(localGenresTvMapper.map(remoteGenres))
                }

        } catch (_: Throwable) {
        }
    }
    /// endregion

    /// region refresh time
    override suspend fun getLastRefreshTime(): Long? {
        return preferenceStorage.lastRefreshTime
    }

    override suspend fun setLastRefreshTime(time: Long) {
        preferenceStorage.setLastRefreshTime(time)
    }

    override suspend fun refreshAll() {
        refreshGenres()
        refreshGenresTv()
        refreshPopularMovies()
        refreshPopularPeople()
        refreshNowPlayingMovies()
        refreshTopRatedMovies()
        refreshTrendingMovies()
        refreshUpcomingMovies()
    }

    /// endregion

    /// region tv

    override suspend fun getAiringTodayTVShows(): Pager<Int, TVShowsEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { airingTodayTvShowsPagingSource }
        )
    }

    override suspend fun getTopRatedTVShows(): Pager<Int, TVShowsEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { topRatedTvShowsPagingSource }
        )
    }

    override suspend fun getPopularTVShows(): Pager<Int, TVShowsEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { popularTVShowsPagingSource }
        )
    }

    override suspend fun getOnTheAirTVShows(): Pager<Int, TVShowsEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { onTheAirTVShowsPagingSource }
        )
    }
    /// endregion

    /// region movies details
    override suspend fun getMoviesDetails(movieId: Int): MovieDetailsEntity {
        return domainMovieDetailsMapper.map(wrapApiCall { movieService.getMovieDetails(movieId) })
    }
    /// endregion

    /// region season details
    override suspend fun getSeasonDetails(seriesId: Int, seasonId: Int): SeasonDetailsEntity {
        val result = wrapApiCall { movieService.getSeasonDetails(seriesId, seasonId) }
        return domainSeasonDetailsMapper.map(result)
    }

    /// endregion

    ///region tv
    override suspend fun getTvDetailsInfo(tvShowID: Int): TvDetailsInfoEntity {
        return domainTvDetailsMapper.map(wrapApiCall { movieService.getTvDetails(tvShowID) })
    }

    override suspend fun getTvDetailsCredit(tvShowID: Int): List<PeopleEntity> {
        return domainTvDetailsCreditMapper.map(
            wrapApiCall {
                movieService.getTvDetailsCredit(tvShowID)
            })
    }

    override suspend fun rateTvShow(rate: Double, tvShowID: Int): StatusEntity {
        val newRate = RateRequest(value = rate)
        return domainStatusMapper.map(wrapApiCall {
            movieService.rateTvShow(
                newRate,
                tvShowID
            )
        })
    }


    //region my list
    override suspend fun getFavoriteMovies(): List<MovieEntity> {
        val genresEntities = getGenresMovies()
        val result = wrapApiCall { movieService.getFavoriteMovies() }.results
        return result?.map { item ->
            domainMovieMapper.map(
                input = item!!,
                genres = filterGenres(
                    item.genreIds?.filterNotNull() ?: emptyList(),
                    genresEntities
                ),
                mediaType = "movie",
            )
        } ?: emptyList()
    }

    override suspend fun getFavoriteTv(): List<MovieEntity> {
        val genresEntities = getGenresMovies()
        val result = wrapApiCall { movieService.getFavoriteTv() }.results
        return result?.map { item ->
            domainTvMapper.map(
                input = item!!,
                genres = filterGenres(
                    item.genreIds?.filterNotNull() ?: emptyList(),
                    genresEntities
                ),
                mediaType = "tv",
            )
        } ?: emptyList()
    }

    override suspend fun getWatchlistMovies(): List<MovieEntity> {
        val genresEntities = getGenresMovies()
        val result = wrapApiCall { movieService.getWatchlist() }.results
        return result?.map { item ->
            domainMovieMapper.map(
                input = item!!,
                genres = filterGenres(
                    item.genreIds?.filterNotNull() ?: emptyList(),
                    genresEntities
                ),
                mediaType = "movie",
            )
        } ?: emptyList()
    }

    override suspend fun getWatchlistTv(): List<MovieEntity> {
        val genresEntities = getGenresMovies()
        val result = wrapApiCall { movieService.getWatchlistTv() }.results
        return result?.map { item ->
            domainTvMapper.map(
                input = item!!,
                genres = filterGenres(
                    item.genreIds?.filterNotNull() ?: emptyList(),
                    genresEntities
                ),
                mediaType = "tv"
            )
        } ?: emptyList()
    }

    override suspend fun setMovieRate(movieId: Int, rate: Float): StatusEntity {
        return domainStatusMapper.map(wrapApiCall {
            movieService.setMovieRate(RatingRequest(rate), movieId)
        })
    }

    override suspend fun getMovieReviews(movieId: Int, page: Int): ReviewResponseEntity {
        return domainReviewsMapper.map(wrapApiCall { movieService.getMovieReviews(movieId, page) })
    }

    override suspend fun addWatchlist(
        mediaId: Int,
        mediaType: String,
        isWatchList: Boolean
    ): StatusEntity {
        val watchlistRequest = WatchlistRequest(
            mediaId = mediaId,
            mediaType = mediaType,
            watchlist = isWatchList
        )
        val call = wrapApiCall { movieService.addWatchlist(watchlistRequest) }
        return domainStatusMapper.map(call)
    }

    override suspend fun addFavouriteList(
        mediaId: Int,
        mediaType: String,
        isFavourite: Boolean
    ): StatusEntity {
        val favoriteRequest = FavoriteRequest(
            mediaId = mediaId,
            mediaType = mediaType,
            favorite = isFavourite
        )
        val call = wrapApiCall { movieService.addFavorite(favoriteRequest) }
        return domainStatusMapper.map(call)
    }

    override suspend fun addList(name: String): Boolean {
        return movieService.addList(ListRequest(name = name)).isSuccessful
    }

    override suspend fun getTvShowReviews(tvShowID: Int): List<ReviewEntity> {
        val call = wrapApiCall { movieService.getTvShowReviews(tvShowID) }.results?.filterNotNull()
            ?: emptyList()
        return domainTvDetailsReviewMapper.map(call)

    }

    override suspend fun getTvShowRecommendations(tvShowID: Int): List<TvShowEntity> {
        val call =
            wrapApiCall { movieService.getTvShowRecomendations(tvShowID) }.results?.filterNotNull()
                ?: emptyList()
        return domainTvShowMapper.map(call)
    }

    override suspend fun getTvDetailsSeasons(tvShowID: Int): List<SeasonEntity> {
        return domainTvDetailsSeasonMapper.map(wrapApiCall { movieService.getTvDetails(tvShowID) })
    }


    /// endregion

    /// region user list
    override suspend fun getUserLists(): List<UserListEntity> {
        val call =
            wrapApiCall { movieService.getUserLists() }.results?.filterNotNull() ?: emptyList()
        return domainUserListsMapper.map(call)
    }

    override suspend fun postUserLists(listId: Int, mediaId: Int): StatusEntity {
        val addMediaRequest = AddMediaToListRequest(mediaId)
        val call = wrapApiCall { movieService.postUserMedia(listId, addMediaRequest) }
        return domainStatusMapper.map(call)
    }

    override suspend fun createUserList(listName: String): StatusEntity {
        val newList = CreateUserListRequest(listName)
        val call = wrapApiCall { movieService.createUserList(newList) }
        return domainStatusMapper.map(call)
    }
    /// endregion

    override suspend fun getDetailsList(listId: Int): List<MovieEntity> {
        val genresEntities = getGenresMovies()
        val result = wrapApiCall { movieService.getDetailsList(listId) }.items
        return result?.map { item ->
            domainMovieMapper.map(
                input = item,
                genres = filterGenres(
                    item.genreIds?.filterNotNull() ?: emptyList(),
                    genresEntities
                )
            )
        } ?: emptyList()
    }

    override suspend fun deleteMovieDetailsList(listId: Int, mediaId: Int): StatusEntity {
        val call = wrapApiCall {
            movieService.deleteMovieDetailsList(
                listId = listId,
                DeleteMovieRequest(mediaId = mediaId)
            )
        }
        return domainStatusMapper.map(call)

    }

    override suspend fun deleteList(listId: Int): StatusEntity {
        return domainStatusMapper.map(wrapApiCall { movieService.deleteList(listId = listId) })
    }


    override suspend fun getListCreated(): List<ListCreatedEntity> {
        return wrapApiCall { movieService.getLists() }.results
            ?.filterNotNull()?.let { lists ->
                lists.map { input ->
                    ListCreatedEntity(
                        id = input.id,
                        itemCount = input.itemCount,
                        listType = input.listType,
                        name = input.name,
                        posterPath = getDetailsList(input.id ?: 0)
                            .map { items ->
                                items.imageUrl
                            }
                    )
                }
            } ?: emptyList()
    }

    //endregion

    /// region episode

    override suspend fun getCastForEpisode(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): List<PeopleEntity> {
        val dataDto = wrapApiCall { movieService.getEpisodeCast(id, seasonNumber, episodeNumber) }
        return domainCastMapper.map(dataDto)

    }

    override suspend fun getEpisodeDetails(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): EpisodeDetailsEntity {
        return domainEpisodeDetailsMapper.map(wrapApiCall {
            movieService.getEpisodeDetails(
                seriesId,
                seasonNumber,
                episodeNumber
            )
        })
    }

    override suspend fun setRatingForEpisode(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int,
        value: Float
    ): RatingEpisodeDetailsStatusEntity {
        val rateRequest = RatingEpisodeDetailsRequest(value)
        return domainRatingEpisodeMapper.map(wrapApiCall {
            movieService.postEpisodeRating(
                rateRequest,
                seriesId,
                seasonNumber,
                episodeNumber
            )
        })
    }

    /// endregion

    /// region trailer
    override suspend fun getTrailerVideoForTvShow(tvShowID: Int): YoutubeVideoDetailsEntity {
        val call =
            wrapApiCall { movieService.getTrailerVideoForTvShow(tvShowID) }.results?.first()
                ?: YoutubeVideoDetailsRemoteDto()
        return domainYoutubeDetailsMapper.map(call)
    }

    override suspend fun getTrailerVideoForMovie(movieID: Int): YoutubeVideoDetailsEntity {
        val call =
            wrapApiCall { movieService.getTrailerVideoForMovie(movieID) }.results?.first()
                ?: YoutubeVideoDetailsRemoteDto()
        return domainYoutubeDetailsMapper.map(call)
    }

    override suspend fun getVideoEpisodeDetails(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): YoutubeVideoDetailsEntity {
        val response =
            wrapApiCall { movieService.getEpisodeVideos(seriesId, seasonNumber, episodeNumber) }
                .results?.first() ?: YoutubeVideoDetailsRemoteDto()

        return domainYoutubeDetailsMapper.map(response)
    }

    /// endregion

    override fun isLoginedOrNot(): Boolean {
        return !preferenceStorage.sessionId.isNullOrBlank()
    }


    /// region myRated
    override suspend fun getRatedMovies(): Pager<Int, MyRatedMovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { ratedMoviesPagingSource }
        )
    }

    override suspend fun getRatedTvShows(): Pager<Int, MyRatedTvShowEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { ratedTvShowPagingSource }
        )
    }
    /// endregion

    // region people details
    override suspend fun getPersonDetails(person_id: Int): PeopleDetailsEntity {
        return domainPeopleDetailsMapper.map(wrapApiCall { movieService.getPerson(person_id) })
    }

    override suspend fun getMoviesByPerson(person_id: Int): List<MovieEntity> {
        return domainMoviesByPeopleMapper.map(wrapApiCall { movieService.getMoviesByPerson(person_id) }.cast!!)
    }

    override suspend fun getTvShowsByPerson(person_id: Int): List<TvShowEntity> {
        return tvShowsByPeopleMapper.map(wrapApiCall {
            movieService.getTvShowsByPerson(
                person_id
            )
        }.cast!!)
    }
    // endregion
}