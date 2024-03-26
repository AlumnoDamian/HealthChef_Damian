@file:OptIn(ExperimentalFoundationApi::class)

package com.damian.healthchef.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.damian.healthchef.ui.screens.login.LoginScreen
import com.damian.healthchef.ui.screens.login.RegisterScreen
import com.damian.healthchef.ui.screens.other.BlogScreen
import com.damian.healthchef.ui.screens.other.PlanificationDateScreen
import com.damian.healthchef.ui.screens.other.UploadRecipeScreen
import com.damian.healthchef.ui.screens.other.UserFeedScreen
import com.damian.healthchef.ui.screens.recipe.RecipeDetailsScreen
import com.damian.healthchef.ui.screens.recipe.RecipeScreen
import com.damian.healthchef.ui.screens.splash.SplashScreen
import com.damian.healthchef.ui.viewmodel.BlogViewModel

@Composable
fun HealthChefAppNavigation(
    navController: NavHostController = rememberNavController()
) {

    val context = LocalContext.current
    val showErrorDialog = remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(
            route = Screens.SplashScreen.route
        ) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.Login.route) {
            LoginScreen(
                navController = navController,
                onContinueRegister = { navController.navigate(Screens.Register.route) },
                onLoginSuccess = { navController.navigate(Screens.Home.route)}
            )
        }
        composable(route = Screens.Register.route) {
            RegisterScreen(
                onContinueLogin = { navController.navigate(Screens.Login.route) },
                onRegisterSuccess = { navController.navigate(Screens.Login.route) },
                onError = { showErrorDialog.value = true }
            )
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
        composable(route = Screens.Recipe.route){
            RecipeScreen(
                viewModel = hiltViewModel(),
                onMealItemClick = { idMeal ->
                    navController.navigate("${Screens.RecipeDetails.route}/$idMeal")
                },
                onContinueHomeScreen = { navController.navigate(Screens.Home.route) },
                onContinueRecipeScreen = { navController.navigate(Screens.Recipe.route) },
                onContinueUplooadRecipeScreen = { navController.navigate(Screens.Upload.route) },
                onContinuePlanificactionDateScreen = { navController.navigate(Screens.PlanificationDate.route) },
                onContinueUserFeedScreen = { navController.navigate(Screens.UserFeed.route) }
            )
        }
        composable(
            route = "${Screens.RecipeDetails.route}/{idMeal}",
            arguments = listOf(navArgument("idMeal") { type = NavType.StringType })
        ){navBackStackEntry ->
            navBackStackEntry.arguments?.getString("idMeal")?.let {
                RecipeDetailsScreen(
                    navController = navController
                )
            }
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