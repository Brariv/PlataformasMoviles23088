package com.uvg.lab5.layout

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat.Style
import androidx.core.graphics.toColorInt
import com.uvg.lab5.R
import com.uvg.lab5.ui.theme.Lab5Theme


@Composable
fun LayoutEjercicio(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top

    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .background(MaterialTheme.colorScheme.tertiary),
            contentAlignment = Alignment.Center

        ){
            Actualizacion()
            
        }
        Spacer(modifier = Modifier.padding(0.dp, 8.dp))
        Box(modifier = Modifier,
            contentAlignment = Alignment.Center){
            Fecha()
            
        }

        Spacer(modifier = Modifier.padding(0.dp, 8.dp))
        Box(modifier = Modifier
            .padding(16.dp, 0.dp)
        ){
            Direccion()
            
        }
        Spacer(modifier = Modifier)

        
    }
}

@Composable
fun Actualizacion(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.8f),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .aspectRatio(1f)
            .align(Alignment.CenterVertically)
            .background(MaterialTheme.colorScheme.onPrimary, shape = CircleShape), contentAlignment = Alignment.Center
        ){
            Icon(painter = painterResource(id = R.drawable.ic_update), contentDescription = "", tint = Color.White)
        }
        Text("Actualización Disponible", color = MaterialTheme.colorScheme.onTertiary)
        Spacer(modifier = Modifier)
        TextButton(onClick = {uriHandler.openUri("https://play.google.com/store/apps/details?id=com.plarium.raidlegends&pcampaignid=web_share")}) {
                Text("Descargar", color = MaterialTheme.colorScheme.onPrimary)


        }

        
    }
}

@Composable
fun Fecha(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ){

        Column(modifier = modifier) {
            Text("Lunes",
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 48.sp)
            Text("20 de mayo", style = MaterialTheme.typography.bodyLarge, fontSize = 20.sp)
        }
        OutlinedButton(onClick = {Toast.makeText(
            context,
            "Terminar Jornada",
            Toast.LENGTH_LONG
        ).show()},
            shape = RoundedCornerShape(4.dp),
        ) {
            Text("Terminar Jornada", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        }
    }

}

@Composable
fun Direccion(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    ElevatedCard (modifier = Modifier
        .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        )
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
        ){
            Informacion()

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp),
            ){
                Button(onClick = {
                    Toast.makeText(
                    context,
                    "Iniciar",
                    Toast.LENGTH_LONG
                ).show()
                                 },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    modifier = Modifier
                        .weight(0.4f)
                    ) {
                    Text("Brandon Werner Rivera Cabrera", color = Color.White)
                }
                Spacer(modifier = Modifier.weight(0.1f))
                OutlinedButton(onClick = { Toast.makeText(
                    context,
                    "Comida Americana\n" +
                            "QQQ",
                    Toast.LENGTH_LONG
                ).show() },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(0.4f)

                    ) {
                    Text("Detalles", color = MaterialTheme.colorScheme.secondary)
                    
                }
            }
            Spacer(modifier = Modifier.padding(0.dp, 8.dp))

        }


    }

}

@Composable
fun Informacion(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column(modifier = Modifier
            .weight(0.7f)
        ){
            Text("TGI Fridays",
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium)
            Text("CA Salvador, Condado Concepcion")
            Text("12:00Pm - 11:55Pm")
        }
        TextButton(onClick = {uriHandler.openUri("https://maps.app.goo.gl/aKHuaFoCJFScMg6w5")}) {
            Icon(painter = painterResource(id = R.drawable.ic_directions), contentDescription = "")
        }


    }
}

@Preview
@Composable
private fun PreviewLayout() {
    Lab5Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LayoutEjercicio(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}