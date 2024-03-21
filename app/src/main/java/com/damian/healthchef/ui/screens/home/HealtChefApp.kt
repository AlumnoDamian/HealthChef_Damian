package com.damian.healthchef.ui.screens.home

import android.app.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.damian.healthchef.ui.navigation.Screens
import com.damian.healthchef.ui.screens.login.LoginScreen
import com.damian.healthchef.ui.screens.login.RegisterScreen
import com.damian.healthchef.ui.screens.other.BlogScreen
import com.damian.healthchef.ui.screens.other.PlanificationDateScreen
import com.damian.healthchef.ui.screens.other.RecipeScreen
import com.damian.healthchef.ui.screens.other.UploadRecipeScreen
import com.damian.healthchef.ui.screens.other.UserFeedScreen
import com.damian.healthchef.ui.viewmodel.HomeViewModel

@Composable
fun HealthChefApp(
    navController: NavHostController = rememberNavController()
) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screens.Login.name,
        ) {
            composable(route = Screens.Login.name) {
                LoginScreen(
                    onContinueRegister = { navController.navigate(Screens.Register.name) },
                    onLoginSuccess = { navController.navigate(Screens.Home.name)}
                )
            }
            composable(route = Screens.Register.name) {
                RegisterScreen(
                    onContinueLogin = { navController.navigate(Screens.Login.name) },
                    onRegisterSuccess = { navController.navigate(Screens.Login.name) },
                    onError = { errorMessage ->
                        AlertDialog.Builder(context)
                            .setTitle("Error")
                            .setMessage(errorMessage)
                            .setPositiveButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .show()
                    }
                )
            }
            composable(route = Screens.Home.name){
                val viewModel: HomeViewModel = viewModel()
                BlogScreen(
                    viewModel = viewModel,
                    onContinueHomeScreen = { navController.navigate(Screens.Home.name) },
                    onContinueRecipeScreen = { navController.navigate(Screens.Recipe.name) },
                    onContinueUplooadRecipeScreen = { navController.navigate(Screens.UploadRecipe.name) },
                    onContinuePlanificactionDateScreen = { navController.navigate(Screens.PlanificationDate.name) },
                    onContinueUserFeedScreen = { navController.navigate(Screens.UserFeed.name) }
                )
            }
            composable(route = Screens.Recipe.name){
                RecipeScreen(
                    onContinueHomeScreen = { navController.navigate(Screens.Home.name) },
                    onContinueRecipeScreen = { navController.navigate(Screens.Recipe.name) },
                    onContinueUplooadRecipeScreen = { navController.navigate(Screens.UploadRecipe.name) },
                    onContinuePlanificactionDateScreen = { navController.navigate(Screens.PlanificationDate.name) },
                    onContinueUserFeedScreen = { navController.navigate(Screens.UserFeed.name) }
                )
            }
            composable(route = Screens.UploadRecipe.name){
                UploadRecipeScreen(
                    onContinueHomeScreen = { navController.navigate(Screens.Home.name) },
                    onContinueRecipeScreen = { navController.navigate(Screens.Recipe.name) },
                    onContinueUplooadRecipeScreen = { navController.navigate(Screens.UploadRecipe.name) },
                    onContinuePlanificactionDateScreen = { navController.navigate(Screens.PlanificationDate.name) },
                    onContinueUserFeedScreen = { navController.navigate(Screens.UserFeed.name) }
                )
            }
            composable(route = Screens.PlanificationDate.name){
                PlanificationDateScreen(
                    onContinueHomeScreen = { navController.navigate(Screens.Home.name) },
                    onContinueRecipeScreen = { navController.navigate(Screens.Recipe.name) },
                    onContinueUplooadRecipeScreen = { navController.navigate(Screens.UploadRecipe.name) },
                    onContinuePlanificactionDateScreen = { navController.navigate(Screens.PlanificationDate.name) },
                    onContinueUserFeedScreen = { navController.navigate(Screens.UserFeed.name) }
                )
            }
            composable(route = Screens.UserFeed.name){
                UserFeedScreen(
                    onContinueHomeScreen = { navController.navigate(Screens.Home.name) },
                    onContinueRecipeScreen = { navController.navigate(Screens.Recipe.name) },
                    onContinueUplooadRecipeScreen = { navController.navigate(Screens.UploadRecipe.name) },
                    onContinuePlanificactionDateScreen = { navController.navigate(Screens.PlanificationDate.name) },
                    onContinueUserFeedScreen = { navController.navigate(Screens.UserFeed.name) },
                    onLogOut = { navController.navigate(Screens.Login.name)}
                )
            }
        }
    }