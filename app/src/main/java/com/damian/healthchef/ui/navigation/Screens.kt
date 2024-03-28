package com.damian.healthchef.ui.navigation

sealed class Screens(val route: String) {
    data object SplashScreen: Screens("splash_screen")
    data object Login: Screens("login_screen")
    data object Register: Screens("register_screen")
    data object Home: Screens("home_screen")
    data object RecipeCategories: Screens("recipe_category_screen")
    data object Recipe: Screens("recipe_list_screen")
    data object RecipeDetails: Screens("recipe")
    data object Upload: Screens("upload")
    data object PlanificationDate: Screens("planification_date")
    data object UserFeed: Screens("user_feed")
}