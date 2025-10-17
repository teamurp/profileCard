package com.example.practice_1

import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
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

data class Follower(
    val id: Int,
    val name: String,
    val pfp: Int,
    var isFollowing: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard() {
    var isFollowing by remember { mutableStateOf(false) }
    var count by rememberSaveable { mutableIntStateOf(2330) }
    val color by animateColorAsState(
        if(isFollowing) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
    )
    val snackbarHostState = remember { SnackbarHostState()}
    val scope = rememberCoroutineScope()
    var followers by remember {
        mutableStateOf(
            mutableListOf(
                Follower(0, "Diane", R.drawable.default_pfp),
                Follower(1, "John", R.drawable.default_pfp),
                Follower(2, "Finn", R.drawable.default_pfp),
                Follower(3, "Paul", R.drawable.default_pfp)
            )
        )
    }
    val state = rememberLazyListState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profile Card") },
                navigationIcon = { Icon(Icons.Default.Menu, null) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ){ innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(followers, key = { it.id }) { followerStory ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painterResource(followerStory.pfp), null, modifier = Modifier
                                    .height(70.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )
                            Text(
                                text = followerStory.name,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painterResource(R.drawable.pfp),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                                .clip(CircleShape)
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
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
                            modifier = Modifier.height(10.dp)
                        )
                        Text(
                            text = "$count followers",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Button(
                            onClick = {
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
                            Text(
                                if (isFollowing) "Unfollow" else "Follow",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                Text(
                    text = "Followers",
                    style = MaterialTheme.typography.titleLarge
                )
                LazyColumn(
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    state = state
                ) {
                    items(followers, key = { it.id }) { follower ->
                        FollowerItem(
                            follower = follower,
                            onDelete = {
                                val removedFollower = follower
                                followers = followers.toMutableList().also {it.remove(removedFollower)}
                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
                                        message = "${removedFollower.name} removed from followers",
                                        actionLabel = "Undo"
                                    )
                                    if(result == SnackbarResult.ActionPerformed){
                                        followers = followers.toMutableList().also {it.add(removedFollower)}
                                    }
                                }
                            }
                        )
                    }
                }
            }
    }
}

@Composable
fun FollowerItem(
    follower: Follower,
    onDelete: () -> Unit
) {
    var logo by remember { mutableStateOf(Icons.Default.Delete) }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(follower.pfp), null, modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = follower.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable {}
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
//                IconButton(onClick = {
//                    logo = Icons.Default.Add
//                    onDelete()
//                }) {
//                    Icon(
//                        logo, null
//                    )
//                }
                Button(
                    onClick = {
                        onDelete()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary)
                ) { Text("Unfollow", style = MaterialTheme.typography.bodyLarge) }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    ProfileCard()
}