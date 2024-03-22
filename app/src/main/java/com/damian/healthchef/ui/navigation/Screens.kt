package com.damian.healthchef.ui.navigation

import com.damian.healthchef.R

enum class Screens(val title : Int) {
    SplashScreen(title = R.string.splashScreen),
    Login(title = R.string.login),
    Register(title = R.string.register),
    Home(title = R.string.home),
    Recipe(title = R.string.recipes),
    UploadRecipe(title = R.string.uploadRecipes),
    PlanificationDate(title = R.string.planificationDate),
    UserFeed(title = R.string.userFeed),
}