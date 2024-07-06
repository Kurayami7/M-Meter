package com.chocolatecake.repository

import com.chocolatecake.entities.BoardEntity
import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.entities.QuestionEntity
import com.chocolatecake.entities.TVShowsEntity
import com.chocolatecake.entities.UserEntity
import com.chocolatecake.local.database.MovieDao
import com.chocolatecake.local.database.TriviaDao
import com.chocolatecake.local.database.dto.UserLocalDto
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.mappers.domain.tv.TVShowsDomainMapper
import javax.inject.Inject

class TriviaRepositoryIml @Inject constructor(
    private val triviaDao: TriviaDao,
    private val movieRepository: MovieRepository,
    private val fakeQuestions: FakeQuestions,
    private val movieDao: MovieDao,
    private val movieService: MovieService,
    private val tvShowsDomainMapper: TVShowsDomainMapper
) : BaseRepository(), TriviaRepository {

    /// region user
    override suspend fun getUserByUsername(username: String): UserEntity {
        return triviaDao.findUserByUsername(username)
            .takeIf { it.isNotEmpty() }
            ?.first()?.toEntity() ?: throw NoUserFoundThrowable()
    }

    override suspend fun deleteUserByUsername(username: String) {
        triviaDao.deleteUserByUsername(username)
    }

    override suspend fun addUserByUsername(username: String) {
        val userEntity = UserEntity(username)
        triviaDao.insertUser(userEntity.toLocalDto())
    }

    override suspend fun updateUser(userEntity: UserEntity) {
        triviaDao.insertUser(userEntity.toLocalDto())
    }
    /// endregion

    /// region game
    override suspend fun getPeopleQuestion(level: Int, questionNumber: Int): QuestionEntity {
        val people = movieRepository.getPopularPeopleFromRemote()
        val filteredPeople = filterPeople(people, level)
        return getQuestionEntity(filteredPeople)
    }

    private fun filterPeople(
        people: List<PeopleEntity>,
        level: Int,
    ): List<PeopleEntity> {
        val filteredPeople =
            people.filter { it.popularity in getPopularityRange(level) && !it.imageUrl.contains("null") }
                .shuffled().take(4)
        return if (filteredPeople.size != 4) {
            filteredPeople + people.minus(filteredPeople.toSet()).take(4 - filteredPeople.size)
        } else {
            filteredPeople
        }
    }

    private fun getPopularityRange(level: Int): ClosedFloatingPointRange<Double> {
        return when (level) {
            1 -> 300.0..Double.MAX_VALUE
            2 -> 200.0..300.0
            else -> 0.0..200.0
        }
    }

    private fun getQuestionEntity(filteredPeople: List<PeopleEntity>): QuestionEntity {
        val peopleChosen = filteredPeople.random()
        val choices = filteredPeople.map { it.name }
        val selectedQuestion = fakeQuestions.getPeopleQuestion()
        val imageUrl = peopleChosen.imageUrl
        val correctAnswerPosition = choices.indexOfFirst { it == peopleChosen.name }
        return QuestionEntity(
            question = selectedQuestion.first,
            imageUrl = imageUrl,
            choices = choices,
            correctAnswerPosition = correctAnswerPosition
        )
    }


    override suspend fun getMovieQuestion(level: Int, questionNumber: Int): QuestionEntity {
        val movies = movieRepository.getPopularMoviesFromRemote()
            .filter { !it.imageUrl.contains("null") }
            .shuffled().take(4)
        val question = fakeQuestions.getMovieQuestion(level)
        val selectedMovie = movies.random()
        val selectedGenre =
            movieRepository.getMoviesDetails(selectedMovie.id).genres.first() ?: "Action"

        val choices: List<String> = when (question.second) {
            FakeQuestions.Companion.QuestionType.NAME -> movies.map { it.title }
            FakeQuestions.Companion.QuestionType.RATE -> movies.map { it.rate.toString() }
            FakeQuestions.Companion.QuestionType.GENRE -> getOtherGenresMovies(selectedMovie.genreEntities) + selectedGenre
        }

        val answer = when (question.second) {
            FakeQuestions.Companion.QuestionType.NAME -> choices.indexOf(selectedMovie.title)
            FakeQuestions.Companion.QuestionType.RATE -> choices.indexOf(selectedMovie.rate.toString())
            FakeQuestions.Companion.QuestionType.GENRE -> choices.indexOf(selectedGenre)
        }

        return QuestionEntity(
            question = question.first,
            imageUrl = selectedMovie.imageUrl,
            choices = choices,
            correctAnswerPosition = answer
        )
    }

    private suspend fun getOtherGenresMovies(genreEntities: List<GenreEntity>): List<String> {
        val allGenres = movieRepository.getGenresMovies()
        val otherGenres = allGenres.subtract(genreEntities.toSet()).distinct()
        return otherGenres.map { it.genreName }.take(3)
    }

    private suspend fun getOtherGenresTvShows(genreEntities: List<GenreEntity>): List<String> {
        val allGenres = movieRepository.getGenresTvs()
        val otherGenres = allGenres.subtract(genreEntities.toSet()).distinct()
        return otherGenres.map { it.genreName }.take(3)
    }

    override suspend fun getPopularTvShows(): List<TVShowsEntity> {
        val result = wrapApiCall { movieService.getPopularTVShows() }
      return tvShowsDomainMapper.map(result.results?.filterNotNull() ?: emptyList())
    }

    override suspend fun getTvShowQuestion(level: Int, questionNumber: Int): QuestionEntity {
        val tvShows = getPopularTvShows().filter { !it.imageUrl.contains("null") }
            .shuffled().take(4)
        val question = fakeQuestions.getTvQuestion(level)
        val selectedTvShow = tvShows.random()
        val selectedGenre = movieRepository.getTvDetailsInfo(selectedTvShow.id).genres.first().genreName

        val choices: List<String> = when (question.second) {
            FakeQuestions.Companion.QuestionType.NAME -> tvShows.map { it.title }
            FakeQuestions.Companion.QuestionType.RATE -> tvShows.map { it.rate.toString() }
            FakeQuestions.Companion.QuestionType.GENRE -> getOtherGenresTvShows(selectedTvShow.genreEntities) + selectedGenre.toString()
        }
         val answer = when (question.second) {
             FakeQuestions.Companion.QuestionType.NAME -> choices.indexOf(selectedTvShow.title)
             FakeQuestions.Companion.QuestionType.RATE -> choices.indexOf(selectedTvShow.rate.toString())
             FakeQuestions.Companion.QuestionType.GENRE -> choices.indexOf(selectedGenre)
         }

        return QuestionEntity(
            question = question.first,
            imageUrl = selectedTvShow.imageUrl,
            choices = choices,
            correctAnswerPosition = answer
        )
    }

    override suspend fun getBoard(level: Int): BoardEntity {
        val size = when (level) {
            1 -> 12
            2 -> 16
            else -> 20
        }
        val movies = movieRepository.getPopularMoviesFromRemote().take(size / 2)
        val itemsEntity =
            (movies + movies).shuffled().mapIndexed { position, item ->
                BoardEntity.ItemBoardEntity(item.imageUrl, position)
            }
        return BoardEntity(itemsEntity)
    }
    /// endregion

}

private fun UserEntity.toLocalDto(): UserLocalDto {
    return UserLocalDto(
        username = username,
        peopleGameLevel = peopleGameLevel,
        numPeopleQuestionsPassed = numPeopleQuestionsPassed,
        tvShowGameLevel = tvShowGameLevel,
        numTvShowQuestionsPassed = numTvShowQuestionsPassed,
        moviesGameLevel = moviesGameLevel,
        numMoviesQuestionsPassed = numMoviesQuestionsPassed,
        memorizeGameLevel = memorizeGameLevel,
        totalPoints = totalPoints
    )
}

private fun UserLocalDto.toEntity(): UserEntity {
    return UserEntity(
        username = username,
        peopleGameLevel = peopleGameLevel,
        numPeopleQuestionsPassed = numPeopleQuestionsPassed,
        tvShowGameLevel = tvShowGameLevel,
        numTvShowQuestionsPassed = numTvShowQuestionsPassed,
        moviesGameLevel = moviesGameLevel,
        numMoviesQuestionsPassed = numMoviesQuestionsPassed,
        memorizeGameLevel = memorizeGameLevel,
        totalPoints = totalPoints
    )
}
