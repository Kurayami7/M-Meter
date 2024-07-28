package com.coderops.remote.service

import com.coderops.remote.response.spotify.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {

    /// region search
    @GET("search")
    suspend fun searchTracks(
        @Query("q") query: String,
        @Query("type") type: String = "track"
    ): Response<SpotifySearchResponse>

    @GET("search")
    suspend fun searchArtists(
        @Query("q") query: String,
        @Query("type") type: String = "artist"
    ): Response<SpotifySearchResponse>

    @GET("search")
    suspend fun searchAlbums(
        @Query("q") query: String,
        @Query("type") type: String = "album"
    ): Response<SpotifySearchResponse>
    /// endregion

    /// region tracks
    @GET("tracks/{id}")
    suspend fun getTrackDetails(@Path("id") trackId: String): Response<Track>

    @GET("tracks")
    suspend fun getSeveralTracks(@Query("ids") trackIds: String): Response<SeveralTracksResponse>
    /// endregion

    /// region artists
    @GET("artists/{id}")
    suspend fun getArtistDetails(@Path("id") artistId: String): Response<Artist>

    @GET("artists/{id}/albums")
    suspend fun getArtistAlbums(
        @Path("id") artistId: String,
        @Query("include_groups") includeGroups: String? = null,
        @Query("market") market: String? = null,
        @Query("limit") limit: Int? = 20,
        @Query("offset") offset: Int? = 0
    ): Response<AlbumsResponse>
    /// endregion

    /// region albums
    @GET("albums/{id}")
    suspend fun getAlbumDetails(@Path("id") albumId: String): Response<Album>

    @GET("albums")
    suspend fun getSeveralAlbums(@Query("ids") albumIds: String): Response<SeveralAlbumsResponse>

    @GET("albums/{id}/tracks")
    suspend fun getAlbumTracks(
        @Path("id") albumId: String,
        @Query("market") market: String? = null,
        @Query("limit") limit: Int? = 20,
        @Query("offset") offset: Int? = 0
    ): Response<TracksResponse>
    /// endregion
}
