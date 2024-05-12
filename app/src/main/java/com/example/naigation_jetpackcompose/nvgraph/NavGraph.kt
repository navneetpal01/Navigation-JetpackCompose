package com.example.naigation_jetpackcompose.nvgraph

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.naigation_jetpackcompose.presentation.DetailsScreen
import com.example.naigation_jetpackcompose.presentation.HomeScreen
import kotlin.random.Random


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: Route = Route.Home
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable<Route.Home> {
            HomeScreen(
                onClick = {
                    navController.navigate(
                        Route.Details(id = Random.nextInt(1, 10))
                    )
                }
            )
        }

        composable<Route.Details> { backStackEntry ->
            val profile = backStackEntry.toRoute<Route.Details>()
            DetailsScreen(data = profile.id){
                if (navController.canGoBack){
                    navController.popBackStack()
                }
            }
        }

    }
}

val NavHostController.canGoBack : Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED