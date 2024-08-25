package com.uvg.lab7.Layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.lab7.Notification
import com.uvg.lab7.NotificationType
import com.uvg.lab7.generateFakeNotifications
import com.uvg.lab7.ui.theme.Lab7Theme
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralDesign(modifier: Modifier = Modifier){
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Notificaciones") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                navigationIcon = {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Menu")
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                filter()
            }
        }

    )
}

@Composable
fun Design(modifier: Modifier = Modifier, chips: List<String>, selectedChipIndex: Int, onChipSelected: (Int) -> Unit, notifications: List<Notification>){

    Column (modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surface)) {


        Column(modifier = Modifier.padding(8.dp)) {
            Text("Tipos de Notificaciones", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                chips.forEachIndexed { index, chipText ->
                    FilterChip(
                        selected = selectedChipIndex == index,
                        onClick = { onChipSelected(index) },
                        label = { Text(chipText, color = MaterialTheme.colorScheme.primaryContainer) },
                        colors =  FilterChipDefaults.filterChipColors(containerColor = MaterialTheme.colorScheme.onSurface),
                        leadingIcon = if (selectedChipIndex == index) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        }
                    )
                }
            }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.inverseSurface, RoundedCornerShape(16.dp))
                        .padding(0.dp, 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)

                ) {
                    items(notifications.size) { index ->
                        dataNotif(notifications, index)

                    }


                }

            }

        }


}

@Composable
fun dataNotif(notifications: List<Notification>, index: Int){
    val ColorGeneral = MaterialTheme.colorScheme.primary
    val ColorPost = MaterialTheme.colorScheme.secondary
    val ColorMensaje = MaterialTheme.colorScheme.tertiary
    val ColorLike = MaterialTheme.colorScheme.onPrimary
    val dateFormat = SimpleDateFormat("dd MMM. h:mm a", Locale.getDefault())

    notification(
        colorChange = { when(notifications[index].type){
            NotificationType.GENERAL -> ColorGeneral
            NotificationType.NEW_POST -> ColorPost
            NotificationType.NEW_MESSAGE -> ColorMensaje
            NotificationType.NEW_LIKE -> ColorLike
        } },
        iconoChange = {
            when(notifications[index].type){
                NotificationType.GENERAL -> com.uvg.lab7.R.drawable.ic_notification
                NotificationType.NEW_POST -> com.uvg.lab7.R.drawable.ic_post
                NotificationType.NEW_MESSAGE -> com.uvg.lab7.R.drawable.ic_chat
                NotificationType.NEW_LIKE -> com.uvg.lab7.R.drawable.ic_like } },
        Titulo = notifications[index].title,
        Fecha = dateFormat.format(notifications[index].sendAt),
        Descripcion = notifications[index].body
    )

        }




@Composable
fun filter(){
    val notifications = generateFakeNotifications()
    val chips = listOf("General", "Post", "Mensaje", "Like")
    var selectedChipIndex by remember { mutableStateOf(-1) }

    Design(chips = chips,
        selectedChipIndex = selectedChipIndex,
        onChipSelected = {  if(it == selectedChipIndex) selectedChipIndex = -1 else selectedChipIndex = it },
        notifications = when(selectedChipIndex){
            -1 -> notifications
        0 -> notifications.filter { it.type == NotificationType.GENERAL }
        1 -> notifications.filter { it.type == NotificationType.NEW_POST }
        2 -> notifications.filter { it.type == NotificationType.NEW_MESSAGE }
        3 -> notifications.filter { it.type == NotificationType.NEW_LIKE }
        else -> notifications
    })



}

@Composable
fun notification(colorChange: () -> Color,
                 iconoChange: () -> Int,
                 Titulo: String,
                 Fecha: String,
                 Descripcion: String
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box (modifier = Modifier
            .size(64.dp)
            .padding(6.dp)
            .background(
                color = colorChange(),
                shape = CircleShape
            ),
            contentAlignment = Alignment.Center) {
            Icon(painter = painterResource(id = iconoChange()), contentDescription = "")
        }
        Spacer(modifier = Modifier.size(8.dp))
        Column (modifier = Modifier, verticalArrangement = Arrangement.SpaceBetween) {
            Row (modifier = Modifier.fillMaxWidth(0.97f),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(Titulo, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text(Fecha, style = MaterialTheme.typography.bodySmall)
            }
            Text(Descripcion, style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Preview
@Composable
fun PreviewTopAppBarDemo(){
    Lab7Theme {
        GeneralDesign()

    }
}