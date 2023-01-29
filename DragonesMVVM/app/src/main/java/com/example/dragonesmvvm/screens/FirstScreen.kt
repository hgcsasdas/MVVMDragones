package com.example.primeraconexionfirebase.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
/*import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp*/
import androidx.navigation.NavController
import com.example.primeraconexionfirebase.navigation.PantallasApp
import kotlinx.coroutines.launch

@Composable
fun FirstScreen(navController: NavController){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold (
        scaffoldState = scaffoldState,
        topBar = {
            TopBarGeneral(
                onMenuButtonClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                onActionButtonClick = { /* */ }
            )
        },
        /*drawerContent = { // Contenido del drawer
            DrawerContent { // Cerrar drawer
                scope.launch { scaffoldState.drawerState.close() }
            }
        }*/

    ){
        BodyContent(navController)
    }
}

@Composable
fun TopBarGeneral(
    onMenuButtonClick: () -> Unit, // Acciones a ejecutar
    onActionButtonClick: (String) -> Unit
) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onMenuButtonClick ) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Abrir menú desplegable")
                }
            },
            title = { Text(text = "App simple Dragones") },
            /*actions = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorito")
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Más")
                }
            }*/
        )
}
/*
@Composable
fun DrawerContent(closeDrawer: () -> Unit) {
    val sections = listOf(
        "Inicio",
        "Añadir",
        "Editar",
        "Eliminar",
        "Mostrar"
    )
    Column(Modifier.padding(vertical = 8.dp)) {
        sections.forEach { section ->
            TextButton(
                onClick = closeDrawer, // Ejecución
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {

                    val textColor = MaterialTheme.colors.onSurface
                    Text(
                        text = section,
                        style = MaterialTheme.typography.body2.copy(color = textColor)
                    )
                }
            }
        }
    }
}*/
@Composable
fun BodyContent(navController: NavController){
    val gradientColors = listOf(Color(0xFF413846), Color(0xFF807C7C))
    val roundCornerShape = RoundedCornerShape(topEnd = 60.dp, bottomStart = 30.dp)
    Column(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
            )
            .size(400.dp, 800.dp)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Aplicación de gestión de dragones")

        Spacer(modifier = Modifier.padding(16.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(colors = listOf(Color(0xFFA8A8A8), Color(0xFF807C7C))),
                    shape = roundCornerShape
                )
                .clip(roundCornerShape)
                .clickable {
                    navController.navigate(route = PantallasApp.Aniadir.route)
                }
                .padding(PaddingValues(horizontal = 60.dp, vertical = 16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Añadir Dragones",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(colors = listOf(Color(0xFFA8A8A8), Color(0xFF807C7C))),
                    shape = roundCornerShape
                )
                .fillMaxWidth()
                .clip(roundCornerShape)
                .clickable {
                    navController.navigate(route = PantallasApp.Ver.route)
                }
                .padding(PaddingValues(horizontal = 60.dp, vertical = 16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Ver Dragones",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(colors = listOf(Color(0xFFA8A8A8), Color(0xFF807C7C))),
                    shape = roundCornerShape
                )
                .clip(roundCornerShape)
                .clickable {
                    navController.navigate(route = PantallasApp.ConsultarDragon.route)
                }
                .padding(PaddingValues(horizontal = 60.dp, vertical = 16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Consultar Dragones",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(colors = listOf(Color(0xFFA8A8A8), Color(0xFF807C7C))),
                    shape = roundCornerShape
                )
                .clip(roundCornerShape)
                .clickable {
                    navController.navigate(route = PantallasApp.Actualizar.route)
                }
                .padding(PaddingValues(horizontal = 60.dp, vertical = 16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Actualizar Dragones",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(colors = listOf(Color(0xFFA8A8A8), Color(0xFF807C7C))),
                    shape = roundCornerShape
                )
                .clip(roundCornerShape)
                .clickable {
                    navController.navigate(route = PantallasApp.Eliminar.route)
                }
                .padding(PaddingValues(horizontal = 60.dp, vertical = 16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Eliminar Dragones",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}