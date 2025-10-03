package com.example.practice_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileCard()
        }
    }
}

@Composable
fun ProfileCard() {
    var isFollowing by rememberSaveable { mutableStateOf(false) }
    var count by rememberSaveable { mutableStateOf(2330)}
    val color by animateColorAsState(
        if(isFollowing) Color.LightGray else MaterialTheme.colorScheme.primary
    )
    Box(modifier = Modifier
        .background(Color.LightGray)
        .padding(20.dp)
        .clip(shape = RoundedCornerShape(8.dp))
    ){
        Box(modifier = Modifier
            .background(Color.White)
            .padding(
                vertical = 10.dp, horizontal = 20.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(R.drawable.pfp), contentDescription = null, modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                    text = "Timur P.",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "SDU Student",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Computer Science",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "3rd year",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                    text = "$count followers",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Button(onClick = {
                    isFollowing = !isFollowing
                    count+= if (isFollowing) 1 else -1
                },
                    colors = ButtonDefaults.buttonColors(containerColor = color)) {
                    Text(if(isFollowing) "Unfollow" else "Follow")
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