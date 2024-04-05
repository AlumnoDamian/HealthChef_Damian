package com.damian.healthchef.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarContent(
    navController: NavController
) {
    // Lista de pantallas en la barra inferior
    val screens: List<Screens.BottomBarScreens> = listOf(
        Screens.BottomBarScreens.Recipe,
        Screens.BottomBarScreens.AddRecipe,
        Screens.BottomBarScreens.Perfil
    )

    // Obtiene la entrada en el stack de navegación actual
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Barra inferior de la aplicación
    BottomAppBar {
        // Itera sobre cada pantalla en la barra inferior
        screens.forEach { screen ->
            // Define un elemento de navegación para cada pantalla
            NavigationBarItem(
                // Verifica si la pantalla actual es la pantalla correspondiente al elemento de navegación actual
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                // Maneja el clic en el elemento de navegación
                onClick = {
                    // Navega a la pantalla correspondiente solo si no es la pantalla actual
                    if (navController.currentDestination?.route != screen.route) {
                        navController.navigate(screen.route) {
                            // Define la configuración de navegación
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true // Guarda el estado de la pantalla actual
                            }
                            launchSingleTop = true // Inicia la pantalla como la única en la cima del stack
                            restoreState = true // Restaura el estado de la pantalla si está en el back stack
                        }
                    }
                },
                // Define el icono del elemento de navegación
                icon = {
                    BadgedBox(
                        badge = {} // No se muestra ningún distintivo en este caso
                    ) {
                        // Verifica si la pantalla es la pantalla de recetas y muestra un icono específico
                        if (screen == Screens.BottomBarScreens.Recipe) {
                            Icon(
                                imageVector = Icons.Outlined.MenuBook, // Icono de recetas
                                contentDescription = screen.title,
                            )
                        } else {
                            // Para otras pantallas, muestra el icono definido en la clase de pantalla
                            Icon(
                                imageVector = screen.icon!!, // Icono definido en la clase de pantalla
                                contentDescription = screen.title
                            )
                        }
                    }
                },
                // Define el texto de etiqueta del elemento de navegación
                label = {
                    Text(text = screen.title)
                }
            )
        }
    }
}