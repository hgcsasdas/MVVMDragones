package com.example.primeraconexionfirebase.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.primeraconexionfirebase.model.Dragones

import com.example.primeraconexionfirebase.navigation.PantallasApp
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("UnrememberedMutableState")
@Composable
fun Ver(navController: NavController){

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
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {
                /* Bottom app bar contenido */
                Text(text = "Ver Dragones", modifier = Modifier.padding(10.dp))
            }
        }
    ){
        val context = LocalContext.current

        val db = FirebaseFirestore.getInstance()
        var listaDinamica = mutableStateListOf<Dragones?>()
        // HACEMOS LA CONSULTA A LA COLECCION CON GET
        db.collection("dragones")
            .get()
            //SI SE CONECTA CORRECTAMENTE
            // RECORRO TODOS LOS DATOS ENCONTRADOS EN LA COLECCIÓN Y LOS ALMACENO EN DATOS
            .addOnSuccessListener {
                    queryDocumentSnapshots ->
                /*Después de coger los datos
                * si son accedidos correctamente
                * y despés se comprueba si está vacío o no*/
                if (!queryDocumentSnapshots.isEmpty) {
                    /*Si los datos no están vacíos
                    * añadimos los datos a una lista*/
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        /*Después le pasamos la lista a los objetos y convertimos los datos a objeto*/
                        val c: Dragones? = d.toObject(Dragones::class.java)
                        //println(c.toString())
                        /*le pasamos el objeto a la lista y  lo añadimos*/
                        listaDinamica.add(c)
                    }
                } else {
                }
            }
            /*desplegamos un mensaje de que no hay datos disponibles*/
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "No hay datos disponibles",
                    Toast.LENGTH_SHORT
                ).show()
            }
        firebaseUI(context,  listaDinamica)
    }
//MostrarDragones()
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun firebaseUI(context: Context, dragonList: SnapshotStateList<Dragones?>) {
    val gradientColors = listOf(Color(0xFF413846), Color(0xFF807C7C))
    val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp)
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Llamo a la lazyColumn
        LazyColumn(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(colors = gradientColors),
                shape = roundCornerShape
            )
        ) {
            // de cada dato creo una carta
            itemsIndexed(dragonList) { index, item ->
                Card(
                    onClick = {
                        Toast.makeText(
                            context,
                            dragonList[index]?.nombre + " selected..",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .background(
                            brush = Brush.horizontalGradient(colors = gradientColors),
                            shape = roundCornerShape
                        ),
                    elevation = 6.dp
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.width(5.dp))
                        //hago un display del nombre
                        dragonList[index]?.nombre?.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(4.dp),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 20.sp, fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        dragonList[index]?.raza?.let {
                            Text(
                                text = "Raza = $it",

                                modifier = Modifier.padding(4.dp),

                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 15.sp
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        dragonList[index]?.color?.let {
                            Text(
                                text = "Color = $it",

                                modifier = Modifier.padding(4.dp),

                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 15.sp)
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        dragonList[index]?.peso?.let {
                            Text(
                                text = "Peso = $it kg",

                                modifier = Modifier.padding(4.dp),

                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 15.sp)
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))

                        dragonList[index]?.genero?.let {
                            Text(

                                text = "Genero = $it",

                                modifier = Modifier.padding(4.dp),

                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 15.sp)
                            )
                        }
                    }
                }
            }

        }
    }
}