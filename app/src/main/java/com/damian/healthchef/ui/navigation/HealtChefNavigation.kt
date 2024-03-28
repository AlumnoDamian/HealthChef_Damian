@file:OptIn(ExperimentalFoundationApi::class)

package com.damian.healthchef.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.damian.healthchef.ui.screens.LoginScreen
import com.damian.healthchef.ui.screens.other.BlogScreen
import com.damian.healthchef.ui.screens.other.PlanificationDateScreen
import com.damian.healthchef.ui.screens.other.UploadRecipeScreen
import com.damian.healthchef.ui.screens.other.UserFeedScreen
import com.damian.healthchef.ui.screens.recipe.RecipeScreen
import com.damian.healthchef.ui.screens.splash.SplashScreen
import com.damian.healthchef.viewmodel.BlogViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HealthChefAppNavigation(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screens.Home.route){
            val viewModel: BlogViewModel = viewModel()
            BlogScreen(
                viewModel = viewModel,
                onContinueHomeScreen = { navController.navigate(Screens.Home.route) },
                onContinueRecipeScreen = { navController.navigate(Screens.Recipe.route) },
                onContinueUplooadRecipeScreen = { navController.navigate(Screens.Upload.route) },
                onContinuePlanificactionDateScreen = { navController.navigate(Screens.PlanificationDate.route) },
                onContinueUserFeedScreen = { navController.navigate(Screens.UserFeed.route) }
            )
        }

        /********************************** SCREEN RECIPE ********************************/
        composable(route = Screens.Recipe.route) {
            RecipeScreen(
                onContinueHomeScreen = { navController.navigate(Screens.Home.route) },
                onContinueRecipeScreen = { navController.navigate(Screens.Recipe.route) },
                onContinueUplooadRecipeScreen = { navController.navigate(Screens.Upload.route) },
                onContinuePlanificactionDateScreen = { navController.navigate(Screens.PlanificationDate.route) },
                onContinueUserFeedScreen = { navController.navigate(Screens.UserFeed.route) }
            )
        }
        /********************************** SCREEN RECIPE ********************************/

        composable(route = Screens.Upload.route){
            UploadRecipeScreen(
                onContinueHomeScreen = { navController.navigate(Screens.Home.route) },
                onContinueRecipeScreen = { navController.navigate(Screens.Recipe.route) },
                onContinueUplooadRecipeScreen = { navController.navigate(Screens.Upload.route) },
                onContinuePlanificactionDateScreen = { navController.navigate(Screens.PlanificationDate.route) },
                onContinueUserFeedScreen = { navController.navigate(Screens.UserFeed.route) }
            )
        }
        composable(route = Screens.PlanificationDate.route){
            PlanificationDateScreen(
                onContinueHomeScreen = { navController.navigate(Screens.Home.route) },
                onContinueRecipeScreen = { navController.navigate(Screens.Recipe.route) },
                onContinueUplooadRecipeScreen = { navController.navigate(Screens.Upload.route) },
                onContinuePlanificactionDateScreen = { navController.navigate(Screens.PlanificationDate.route) },
                onContinueUserFeedScreen = { navController.navigate(Screens.UserFeed.route) }
            )
        }
        composable(route = Screens.UserFeed.route){
            UserFeedScreen(
                onContinueHomeScreen = { navController.navigate(Screens.Home.route) },
                onContinueRecipeScreen = { navController.navigate(Screens.Recipe.route) },
                onContinueUplooadRecipeScreen = { navController.navigate(Screens.Upload.route) },
                onContinuePlanificactionDateScreen = { navController.navigate(Screens.PlanificationDate.route) },
                onContinueUserFeedScreen = { navController.navigate(Screens.UserFeed.route) },
                onLogOut = { navController.navigate(Screens.Login.route)}
            )
        }
    }
}