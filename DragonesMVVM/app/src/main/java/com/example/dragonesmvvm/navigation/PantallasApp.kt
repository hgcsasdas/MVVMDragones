package com.example.primeraconexionfirebase.navigation

sealed class PantallasApp (val route: String) {
    object FirstScreen: PantallasApp(route = "FirstScreen")
    object Aniadir: PantallasApp(route = "Aniadir")
    object Ver: PantallasApp(route = "Ver")
    object Actualizar: PantallasApp(route = "Actualizar")
    object Eliminar: PantallasApp(route = "Eliminar")
    object ConsultarDragon: PantallasApp(route = "ConsultarDragon")
}