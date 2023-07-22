package com.andremw96.movie_app.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.andremw96.movie_app.ui.screen.genrelist.GenreListScreen
import com.andremw96.movie_app.ui.screen.genrelist.GenreListViewModel
import com.andremw96.movie_app.ui.screen.movielistbygenre.MovieListByGenreScreen
import com.andremw96.movie_app.ui.screen.movielistbygenre.MovieListByGenreViewModel

@Composable
fun MovieAppNavigation(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = NavGraphConstant.GENRE_LIST) {
        composable(route = NavGraphConstant.GENRE_LIST) {
            val viewModel: GenreListViewModel = viewModel()
            val viewState by viewModel.viewState.collectAsState()

            LaunchedEffect(key1 = Unit, block = {
                viewModel.loadGenreList()
            })

            GenreListScreen(
                viewState = viewState,
                callbacks = viewModel,
                navController = navController,
            )
        }
        composable(
            route = "${NavGraphConstant.MOVIE_LIST_BY_GENRE}/${NavGraphConstant.GENRE_ID}",
            arguments = listOf(
                navArgument(NavGraphConstant.GENRE_ID) { type = NavType.Companion.StringType }
            )
        ) {
            val viewModel: MovieListByGenreViewModel = viewModel()
            val viewState by viewModel.viewState.collectAsState()

            val genreId = it.arguments?.getString(NavGraphConstant.GENRE_ID)

            LaunchedEffect(key1 = Unit, block = {
                viewModel.loadMovieListByGenre(genreId ?: "")
            })

            MovieListByGenreScreen(
                genreId = genreId ?: "",
                viewState = viewState,
                callbacks = viewModel
            )
        }
    }
}
