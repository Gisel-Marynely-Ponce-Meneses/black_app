package com.example.black_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.black_app.ui.theme.Black_appTheme

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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Black_appTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var counter by rememberSaveable { mutableStateOf(0) }
    var selectedTab by rememberSaveable { mutableStateOf(2) }

    val tecnologias = listOf("Java", "Kotlin", "Compose", "XML", "Android", "Git")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("black_app") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menú"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar"
                        )
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
            FloatingActionButton(onClick = { counter++ }) {
                Icon(
                    imageVector = Icons.Default.Add,
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
                            imageVector = Icons.Default.Home,
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
                            imageVector = Icons.Default.Favorite,
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
                            imageVector = Icons.Default.Person,
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
                .background(Color(0xFF303030))
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF424242))
            ) {
                if (selectedTab == 2) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
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
                            text = "Gisel Ponce Meneses",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            textAlign = TextAlign.Start
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Image(
                                painter = painterResource(id = R.drawable.icono),
                                contentDescription = "Logo",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(280.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Greeting(
                                name = "Android",
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 12.dp)
                                    .clickable { counter++ }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_favorite),
                                    contentDescription = "Like",
                                    modifier = Modifier.size(72.dp)
                                )
                                Text(
                                    text = counter.toString(),
                                    color = Color.White,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(start = 12.dp)
                                )
                            }

                            Text(
                                text = "Gisel Ponce Meneses",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = "TIID 04_01",
                                color = Color.White,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp),
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = "Aplicaciones móviles",
                                color = Color.White,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 2.dp),
                                textAlign = TextAlign.Center
                            )

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp, vertical = 16.dp)
                            ) {
                                Text(text = "Stack:", color = Color.White, fontWeight = FontWeight.Bold)
                                Text(text = "Java", color = Color.White)
                                Text(text = "KOTLIN", color = Color.White)
                            }

                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            ) {
                                items(tecnologias) { tecnologia ->
                                    Text(
                                        text = tecnologia,
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}