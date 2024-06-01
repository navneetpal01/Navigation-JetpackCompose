package com.example.naigation_jetpackcompose.nvgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentComposer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.naigation_jetpackcompose.CustomNavType
import com.example.naigation_jetpackcompose.User
import com.example.naigation_jetpackcompose.presentation.DetailsScreen
import com.example.naigation_jetpackcompose.presentation.HomeScreen
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.reflect.typeOf


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
                        Route.Details(
                            user = User(
                                id = Random.nextInt(0..10),
                                name = "Jona",
                                age = 30
                            )
                        )
                    )
                }
            )
        }

        composable<Route.Details>(
            typeMap = mapOf(typeOf<User>() to CustomNavType)
        ) { backStackEntry ->
            val profile = backStackEntry.toRoute<Route.Details>()

            LaunchedEffect(key1 = profile) {
                println()
            }


            DetailsScreen(data = profile.user.id){
                if (navController.canGoBack){
                    navController.popBackStack()
                }
            }
        }

    }
}

val NavHostController.canGoBack : Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED