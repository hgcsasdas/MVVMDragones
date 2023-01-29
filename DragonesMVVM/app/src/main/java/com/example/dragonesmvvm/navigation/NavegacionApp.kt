package com.example.primeraconexionfirebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.primeraconexionfirebase.model.ViewModel
import com.example.primeraconexionfirebase.screens.*

@Composable
fun NavegacionApp(ViewModel: ViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PantallasApp.FirstScreen.route){
        composable(route= PantallasApp.FirstScreen.route){
            FirstScreen(navController)
        }
        composable(route= PantallasApp.Aniadir.route){
            Aniadir(
                navController,
                ViewModel
            )
        }
        composable(route= PantallasApp.Ver.route){
            Ver(navController)
        }
        composable(route= PantallasApp.Actualizar.route){
            Actualizar(
                navController,
                ViewModel
            )
        }
        composable(route= PantallasApp.Eliminar.route){
            Eliminar(
                navController,
                ViewModel
            )
        }
        composable(route= PantallasApp.ConsultarDragon.route){
            ConsultarDragon(
                navController,
                ViewModel
            )
        }
    }
}