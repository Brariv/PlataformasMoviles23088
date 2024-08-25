package com.uvg.lab6.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.lab6.R

@Composable
fun Lab6Scaff(onDecrementoChange: () -> Unit = {},
             onIncrementoChange: () -> Unit = {},
             TotalIncremento: Int,
             TotalDecremento: Int,
             Total: Int,
             ValorMaximo: Int,
             ValorMinimo: Int,
             TotalCambios: Int,
             History: MutableList<Int>,
             HistoryColor: MutableList<Color>,
             onReiniciarChange: () -> Unit = {}) {
    Scaffold (
        bottomBar = {
            Button(onClick = {onReiniciarChange()}, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp), colors = ButtonDefaults.buttonColors( containerColor = Color.Blue)
            ) {
                Text("Reiniciar")
            }
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Lab6(
                    onDecrementoChange = onDecrementoChange,
                    onIncrementoChange = onIncrementoChange,
                    TotalIncremento = TotalIncremento,
                    TotalDecremento = TotalDecremento,
                    Total = Total,
                    ValorMaximo = ValorMaximo,
                    ValorMinimo = ValorMinimo,
                    TotalCambios = TotalCambios,
                    History = History,
                    HistoryColor = HistoryColor
                )
            }
        }

    )
}

@Composable
fun Lab6(onDecrementoChange: () -> Unit = {},
         onIncrementoChange: () -> Unit = {},
         TotalIncremento: Int,
         TotalDecremento: Int,
         Total: Int,
         ValorMaximo: Int,
         ValorMinimo: Int,
         TotalCambios: Int,
         History: MutableList<Int>,
         HistoryColor: MutableList<Color>
         ) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        
        , verticalArrangement = Arrangement.Top
        , horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Brandon Rivera Cabrera", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.padding(0.dp,16.dp))
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Button(
                onClick = { onDecrementoChange() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = CircleShape,
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_decremento),
                    contentDescription = "",
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.padding(8.dp,0.dp))
            Text(text = Total.toString(), style = MaterialTheme.typography.displayLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(8.dp,0.dp))

            Button(onClick = {onIncrementoChange()},
                colors = ButtonDefaults.buttonColors( containerColor = Color.Blue),
                shape = CircleShape,
                modifier = Modifier.size(64.dp)) {
                Icon(painter = painterResource(id = R.drawable.ic_incremento),
                    contentDescription = "",
                    modifier = Modifier.size(48.dp))
            }


        }
        Spacer(modifier = Modifier.padding(0.dp, 16.dp))
        HorizontalDivider(thickness = 2.dp)





    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 0.dp)
        ) {
        Spacer(modifier = Modifier.padding(0.dp, 8.dp))

        StatLine(Texto = "Total Incrementos:", Valor = TotalIncremento)
        Spacer(modifier = Modifier.padding(0.dp, 4.dp))
        StatLine(Texto = "Total Decrementos:", Valor = TotalDecremento)
        Spacer(modifier = Modifier.padding(0.dp, 4.dp))
        StatLine(Texto = "Valor Máximo:", Valor = ValorMaximo)
        Spacer(modifier = Modifier.padding(0.dp, 4.dp))
        StatLine(Texto = "Valor Mínimo:", Valor = ValorMinimo)
        Spacer(modifier = Modifier.padding(0.dp, 4.dp))
        StatLine(Texto = "Total Cambios:", Valor = TotalCambios)
        Spacer(modifier = Modifier.padding(0.dp, 4.dp))

        Text("Historial:", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(0.dp, 4.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(5),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
            items(History.size) { index ->
                HistoryBox(colorChange = { HistoryColor[index]}, Valor = History[index])
            }

        }
    }

}

@Composable
fun StatLine(Texto : String, Valor : Int){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
        Text(text = Texto, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(text = Valor.toString(), style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun Lab6Main(modifier: Modifier = Modifier){
    var TotalIncremento by remember { mutableStateOf(0) }
    var TotalDecremento by remember { mutableStateOf(0) }
    var Total by remember { mutableStateOf(0) }
    var ValorMaximo by remember { mutableStateOf(0) }
    var ValorMinimo by remember { mutableStateOf(0) }
    var TotalCambios by remember { mutableStateOf(0) }
    var History by remember { mutableStateOf(mutableListOf<Int>()) }
    var HistoryColor by remember { mutableStateOf(mutableListOf<Color>()) }
    Lab6Scaff(
        TotalIncremento = TotalIncremento,
        TotalDecremento = TotalDecremento,
        Total = Total,
        ValorMaximo = ValorMaximo,
        ValorMinimo = ValorMinimo,
        TotalCambios = TotalCambios,
        History = History,
        HistoryColor = HistoryColor,
        onDecrementoChange = { TotalDecremento += 1; Total -= 1; TotalCambios += 1; if(Total < ValorMinimo) ValorMinimo = Total; History.add(Total); HistoryColor.add(Color.Red) },
        onIncrementoChange = { TotalIncremento += 1; Total += 1; TotalCambios += 1; if(Total > ValorMaximo) ValorMaximo = Total; History.add(Total); HistoryColor.add(Color.Green) },
        onReiniciarChange = { TotalIncremento = 0; TotalDecremento = 0; Total = 0; ValorMaximo = 0; ValorMinimo = 0; TotalCambios = 0; History.clear() }
    )
}

@Composable
fun HistoryBox(colorChange: () -> Color, Valor: Int){
    Box(modifier = Modifier.background(color = colorChange(), shape = RoundedCornerShape(8.dp))) {
        Text(text = Valor.toString(), style = MaterialTheme.typography.headlineSmall, color = Color.White, modifier = Modifier.align(Alignment.Center))

    }
}

@Preview
@Composable
fun PreviewLab6(){
    Lab6Main()
}