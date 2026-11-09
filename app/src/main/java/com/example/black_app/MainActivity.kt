package com.example.black_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.black_app.ui.theme.Black_appTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Black_appTheme {
                App()
            }
        }
    }
}

@Composable
fun MyGoogleMaps() {
    val marker = LatLng(19.98007, -98.6853)
    val markerState = rememberMarkerState(position = marker)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(marker, 15f)
    }

    val properties = remember { MapProperties(mapType = MapType.HYBRID) }
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = false) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = markerState,
            title = "UPP"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var selectedTab by rememberSaveable { mutableStateOf(2) }
    var mostrarDialogo by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                title = { Text("black_app") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF212121),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    mostrarDialogo = true
                },
                containerColor = Color(0xFF5E35B1),
                contentColor = Color.White
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Agregar"
                )
            }
        },

        floatingActionButtonPosition = FabPosition.End,

        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFECEFF1)
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favorites"
                        )
                    },
                    label = { Text("Favorites") }
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile"
                        )
                    },
                    label = { Text("Profile") }
                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> MyGoogleMaps()
                else -> ProfileScreen()
            }
        }
    }

    // Cuadro de diálogo del botón flotante
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogo = false
            },
            title = {
                Text(text = "Mensaje")
            },
            text = {
                Text(text = "Hola")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarDialogo = false
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF424242))
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.icono),
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(240.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Gisel Marynely Ponce Meneses",
            fontSize = 26.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
    }
}

private val Morado = Color(0xFF5E35B1)
private val MoradoClaro = Color(0xFFEDE7F6)

data class Lenguaje(
    val nombre: String,
    val abreviatura: String,
    val color: Color,
    val colorTexto: Color = Color.White,
    val logo: Int? = null
)

private val lenguajes = listOf(
    Lenguaje("Python", "Py", Color(0xFF3776AB)),
    Lenguaje("C++", "C++", Color(0xFF00599C)),
    Lenguaje("Java", "Jv", Color(0xFFE76F00)),
    Lenguaje("SQL", "SQL", Color(0xFF336791)),
    Lenguaje("MySQL", "My", Color(0xFF00758F)),
    Lenguaje("Kotlin", "Kt", Color(0xFF7F52FF)),
    Lenguaje("C#", "C#", Color(0xFF68217A)),
    Lenguaje("JavaScript", "JS", Color(0xFFF7DF1E), Color.Black),
    Lenguaje("HTML", "H5", Color(0xFFE34F26)),
    Lenguaje("CSS", "C3", Color(0xFF1572B6))
)

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.fondo_perfil),
                contentDescription = "Portada",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Surface(
                color = Color(0xCC212121),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text("UPP", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text("TIID", color = Color.White, fontSize = 18.sp)
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = 24.dp, y = 55.dp)
                    .size(110.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.foto_perfil),
                    contentDescription = "Foto de perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .border(4.dp, Color.White, CircleShape)
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Morado)
                        .border(2.dp, Color.White, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.PhotoCamera,
                        contentDescription = "Cambiar foto",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(65.dp))

        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = "Gisel Marynely Ponce Meneses",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1A1A2E)
            )

            Text(
                text = "gisma@micorreo.upp.edu.mx",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                text = "Ingeniero en Tecnologías de la Información e Innovación Digital | Desarrollador Mobile & Web",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Morado,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = "Apasionada por el desarrollo de aplicaciones móviles, la arquitectura de software " +
                        "y la innovación digital en la UPP.",
                fontSize = 15.sp,
                color = Color(0xFF555555),
                lineHeight = 22.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "</> LENGUAJES DE PROGRAMACIÓN",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A2E)
            )
            Text(text = "Desliza →", fontSize = 12.sp, color = Color.Gray)
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            items(lenguajes) { lenguaje ->
                EtiquetaLenguaje(lenguaje)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(280.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            MyGoogleMaps()
        }

        Spacer(modifier = Modifier.height(90.dp))
    }
}

@Composable
private fun EtiquetaLenguaje(lenguaje: Lenguaje) {
    Surface(
        color = MoradoClaro,
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, Morado.copy(alpha = 0.3f))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        ) {
            if (lenguaje.logo != null) {
                Image(
                    painter = painterResource(id = lenguaje.logo),
                    contentDescription = lenguaje.nombre,
                    modifier = Modifier.size(26.dp)
                )
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .background(lenguaje.color)
                ) {
                    Text(
                        text = lenguaje.abreviatura,
                        color = lenguaje.colorTexto,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = lenguaje.nombre,
                color = Morado,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }
    }
}