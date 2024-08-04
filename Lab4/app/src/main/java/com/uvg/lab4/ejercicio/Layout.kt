package com.uvg.lab4.ejercicio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.uvg.lab4.ui.theme.Lab4Theme
import com.uvg.lab4.R


@Composable
fun LayoutEjercicio(
    modifier: Modifier = Modifier
    ) {
    Box (modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .border(8.dp, MaterialTheme.colorScheme.primary)
        .background(Color.White)
        ) {
        Column(modifier = Modifier.fillMaxSize()){
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.12f))
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .paint(
                    painterResource(id = R.drawable.uvgl),
                    contentScale = ContentScale.FillHeight,
                    alpha = (0.2f)
                )
            ){
                Contenido()
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.12f))
        }






    }

}

@Composable
fun Contenido(modifier: Modifier = Modifier){
    Box(modifier = Modifier.fillMaxSize()){
        Column (modifier = Modifier
            .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.SpaceBetween

        ){
            Box(modifier = Modifier
                .fillMaxWidth()
            ){
                Text("Universidad del Valle \n" +
                        "de Guatemala", modifier = Modifier
                    .align(Alignment.Center),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
            }
            Box(modifier = Modifier
                .fillMaxWidth()
            ){
                Text("Programacion de Plataformas\n" +
                        "Moviles, Seccion 30", modifier = Modifier
                    .align(Alignment.Center),
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
            }

            Linea(Titulo = "Integrantes", Nombres = "Pedro Avila \n" + "Javier Cifuentes\n" + "Brandon Rivera")
            Linea(Titulo = "Catedratico", Nombres = "Juan Carlos Durini")

            Box(modifier = Modifier
                .fillMaxWidth()
            ){
                Text("Brandon Werner Rivera Cabrera\n" +
                        "23088", modifier = Modifier
                    .align(Alignment.Center),
                    color = Color.Black, textAlign = TextAlign.Center
                )

            }





        }
    }
}

@Composable
fun Linea(Titulo: String, Nombres: String, modifier: Modifier = Modifier){
    Row(modifier = Modifier
        .fillMaxWidth()
        ) {
        Box(
            modifier = Modifier
                .weight(0.38f)
        ) {
            Text(
                text = Titulo, modifier = Modifier
                    .align(Alignment.CenterEnd),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .weight(0.62f)
        ) {
            Text(
                text = Nombres, modifier = Modifier
                    .align(Alignment.Center),
                color = Color.Black,
                textAlign = TextAlign.Center
            )

        }
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewLayoutEjercicio() {
    Lab4Theme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LayoutEjercicio()
        }
    }
}


