package com.example.primeraconexionfirebase.screens

import android.annotation.SuppressLint
import android.view.View
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.primeraconexionfirebase.model.ViewModel
import com.example.primeraconexionfirebase.navigation.PantallasApp
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ConsultarDragon(navController: NavController, ViewModel: ViewModel) {
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
                Text(text = "Consultar Dragon", modifier = Modifier.padding(10.dp))
            }
        }
    ){
        ConsultarUnDragon(ViewModel)
    }
}

@Composable
fun ConsultarUnDragon(ViewModel: ViewModel){
    var nombre_coleccion = "dragones"
    val db = FirebaseFirestore.getInstance()

    val gradientColors = listOf(Color(0xFF413846), Color(0xFF807C7C))
    val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp)

    //DECLARAMOS LA VARIABLE QUE VA A RECOGER LOS DATOS DE LA CONSULTA CON EL ESTADO REMEMBER
    var datos by remember { mutableStateOf("") }

    val nombre_dragon by ViewModel.nombre_dragon.observeAsState(initial = "")
    val raza_dragon by ViewModel.raza_dragon.observeAsState(initial = "")
    val color_dragon by ViewModel.color_dragon.observeAsState(initial = "")
    val peso_dragon by ViewModel.peso_dragon.observeAsState(initial = "")
    val genero_dragon by ViewModel.genero_dragon.observeAsState(initial = "")

    val nombre_busqueda by ViewModel.nombre_busqueda.observeAsState(initial = "")

    Column(

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
            )

    ) {

        Text(
            text = "Búsqueda de dragones por nombre",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(20.dp))

        OutlinedTextField(
            value = nombre_busqueda,
            onValueChange = { ViewModel.onCompleteBusqueda(nombre_busqueda = it) },
            label = { Text("Introduce el Dragon a consultar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))
        /*var d2 = Dragon()
        var listaDinamica = ArrayList<Dragon>()*/

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(colors = listOf(Color(0xFFA8A8A8), Color(0xFF807C7C))),
                    shape = roundCornerShape
                )
                //.width(300.dp)
                .clip(roundCornerShape)
                .clickable {
                    // VACIAMOS VARIABLE AL DAR AL BOTON
                    datos = ""
                    ViewModel.limpiarCampos()
                    // HACEMOS LA CONSULTA A LA COLECCION CON GET
                    db.collection(nombre_coleccion)
                        .whereEqualTo("nombre",nombre_busqueda)
                        .get()

                        //SI SE CONECTA CORRECTAMENTE
                        // RECORRO TODOS LOS DATOS ENCONTRADOS EN LA COLECCIÓN Y LOS ALMACENO EN DATOS
                        .addOnSuccessListener { resultado ->
                            for (encontrado in resultado) {
                                //Para crear un HashMap con todos los datos
                                datos += "${encontrado.id}: ${encontrado.data}\n"




                                //Para crear un HashMap con todos los datos
                                ViewModel.onCompletedFields(
                                    nombre_dragon = encontrado["nombre"].toString(),
                                    raza_dragon = encontrado["raza"].toString(),
                                    color_dragon = encontrado["color"].toString(),
                                    peso_dragon = encontrado["peso"].toString(),
                                    genero_dragon = encontrado["genero"].toString()
                                )
                                /*nombre_dragon += encontrado["nombre"].toString()
                                color_dragon += encontrado["color"].toString()
                                genero_dragon += encontrado["genero"].toString()
                                peso_dragon += encontrado["peso"].toString()
                                raza_dragon += encontrado["raza"].toString()
                                //Log.i("DATOS:", datos)*/

                                /*d2.setNombreDragon(nombre_dragon)
                                listaDinamica.add(d2)
                                for( i in listaDinamica){
                                    println(i.getNombreDragon())
                                }
                                var D1 = Dragon(
                                    nombre_dragon,
                                    color_dragon,
                                    genero_dragon,
                                    peso_dragon,
                                    raza_dragon
                                );*/
                            }

                            if (datos.isEmpty()) {
                                datos = "No existen datos"
                            }
                        }
                        //SI NO CONECTA CORRECTAMENTE
                        .addOnFailureListener { resultado ->
                            datos = "La conexión a FireStore no se ha podido completar"
                        }
                }
        ) {
            Text(
                text = "Cargar Dragón",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.size(300.dp, 40.dp)
            )
        }

        Spacer(modifier = Modifier.size(10.dp))
        // PINTAMOS EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS
        //Text (text = datos)
        Text (text = "Nombre: " + nombre_dragon)
        Text (text = "Raza: " + raza_dragon)
        Text (text = "Peso: " + peso_dragon)
        Text (text = "Genero: " + genero_dragon)
        Text (text = "Color: " + color_dragon)


    }

}