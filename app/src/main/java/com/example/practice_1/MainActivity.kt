package com.example.practice_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Snackbar
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileCard()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard() {
    var isFollowing by remember { mutableStateOf(false) }
    var count by rememberSaveable { mutableIntStateOf(2330) }
    val color by animateColorAsState(
        if(isFollowing) Color.LightGray else MaterialTheme.colorScheme.primary
    )
    val snackbarHostState = remember { SnackbarHostState()}
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile Card") },
                navigationIcon = { Icon(Icons.Default.Menu, null) },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(modifier = Modifier
                .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ){
                Column(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painterResource(R.drawable.pfp), contentDescription = null, modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                    Text(
                        text = "Timur P.",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "SDU Student",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Computer Science",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "3rd year",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                    Text(
                        text = "$count followers",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray
                    )
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                    Button(onClick = {
                        if (isFollowing) {
                            isFollowing = false
                            count--
                            scope.launch {
                                snackbarHostState.showSnackbar("You have unfollowed Timur P.")
                            }
                        } else {
                            isFollowing = true
                            count++
                            scope.launch {
                                snackbarHostState.showSnackbar("You are now following Timur P.")
                            }
                        }
                    },
                        colors = ButtonDefaults.buttonColors(containerColor = color)
                    ) {
                        Text(if(isFollowing) "Unfollow" else "Follow", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    ProfileCard()
}