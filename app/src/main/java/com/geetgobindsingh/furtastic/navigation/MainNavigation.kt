package com.geetgobindsingh.furtastic.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.ImageLoader
import com.geetgobindsingh.dogdetails.DogDetailsScreen
import com.geetgobindsingh.doglist.DogListScreen

@Composable
fun MainNavigation(imageLoader: ImageLoader) {

    val navController = rememberNavController()

    val context = LocalContext.current

    val actions = remember(navController) { Actions(navController, context) }

    NavHost(navController = navController, startDestination = Destinations.DogList) {
        dogList(imageLoader, actions)
        dogDetails(imageLoader, actions)
    }
}

private fun NavGraphBuilder.dogList(imageLoader: ImageLoader, actions: Actions) {
    composable(
        route = Destinations.DogList
    ) { backStackEntry ->
        DogListScreen(imageLoader, actions.openDogDetails)
    }
}

private fun NavGraphBuilder.dogDetails(imageLoader: ImageLoader, actions: Actions) {
    composable(
        route = "${Destinations.DogDetails}/{${DestinationArgs.DogId}}",
        arguments = listOf(navArgument(DestinationArgs.DogId) { type = NavType.IntType }),
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        DogDetailsScreen(
            dogId = arguments.getInt(DestinationArgs.DogId),
            onBackPress = actions.navigateUp,
            imageLoader = imageLoader
        )
    }
}

internal data class Actions(val navController: NavHostController, val context: Context) {

    val openDogDetails: (Int) -> Unit = { dogId ->
        navController.navigate("${Destinations.DogDetails}/$dogId")
    }

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}