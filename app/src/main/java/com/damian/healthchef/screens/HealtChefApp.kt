package com.damian.healthchef.screens


import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.damian.healthchef.R
import com.damian.healthchef.screens.login.LoginScreen
import com.damian.healthchef.screens.login.RegisterScreen
import com.damian.healthchef.screens.other.HomeScreen
import com.damian.healthchef.screens.other.PlanificationDateScreen
import com.damian.healthchef.screens.other.RecipeScreen
import com.damian.healthchef.screens.other.UploadRecipeScreen
import com.damian.healthchef.screens.other.UserFeedScreen

enum class Screens(val title : Int) {
    Login(title = R.string.login),
    Register(title = R.string.register),
    Home(title = R.string.home),
    Recipe(title = R.string.recipes),
    UploadRecipe(title = R.string.uploadRecipes),
    PlanificationDate(title = R.string.planificationDate),
    UserFeed(title = R.string.userFeed)
}

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
                HomeScreen(
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