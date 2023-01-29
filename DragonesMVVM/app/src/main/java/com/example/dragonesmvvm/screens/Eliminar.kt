package com.example.primeraconexionfirebase.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.primeraconexionfirebase.model.ViewModel
import com.example.primeraconexionfirebase.navigation.PantallasApp
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Eliminar(navController: NavController, ViewModel: ViewModel){
    ViewModel.limpiarCampos()
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = PantallasApp.FirstScreen.route)
            },
                backgroundColor = Color.Gray
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Atrás")
            } },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(
                // Defaults to null, that is, No cutout
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {
                /* Bottom app bar content */
                Text(text = "Eliminar Dragon", modifier = Modifier.padding(10.dp))
            }
        }

        )   {
        EliminarDragon(ViewModel)
    }
}

@Composable
fun EliminarDragon(ViewModel: ViewModel){
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    var nombre_coleccion = "dragones"

    val nombre_dragon by ViewModel.nombre_dragon.observeAsState(initial = "")

    val gradientColors = listOf(Color(0xFF413846), Color(0xFF807C7C))
    val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
            )

    ) {
        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "Eliminar Dragon",
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.size(20.dp))
        OutlinedTextField(
            value = nombre_dragon,
            onValueChange ={
                ViewModel.onCompleteNombre(
                    nombre_dragon = it
                )
            },
            label = { Text("Introduce el Dragón a borrar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        Spacer(modifier = Modifier.size(20.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFA8A8A8),
                            Color(0xFF807C7C)
                        )
                    ),
                    shape = roundCornerShape
                )
                .fillMaxWidth()
                .clip(roundCornerShape)
                .clickable {
                    if (nombre_dragon.isNotBlank()) {
                        db
                            .collection(nombre_coleccion)
                            .document(nombre_dragon)
                            .delete()
                            .addOnSuccessListener {
                                ViewModel.limpiarCampos()
                                Toast
                                    .makeText(context, "Borrado correctamente", Toast.LENGTH_LONG)
                                    .show()
                            }
                            .addOnFailureListener {
                                Toast
                                    .makeText(context, "No se ha podido borrar", Toast.LENGTH_LONG)
                                    .show()
                            }

                    }
                }
        ) {
            Text(
                text = "Borrar Dragón",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.size(300.dp, 40.dp)
            )
        }

    }
}

