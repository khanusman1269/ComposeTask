package com.compose.api.task.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.api.task.R
import com.compose.api.task.common.LoadingShimmer
import com.compose.api.task.common.UiState
import com.compose.api.task.components.ErrorScreen
import com.compose.api.task.components.ShowAlert
import com.compose.api.task.domain.models.Medicine
import com.compose.api.task.theme.DarkSlateGrey
import com.compose.api.task.theme.White16Medium
import com.compose.api.task.theme.White18SemiBold
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(userName: String, onCardClick: (Medicine) -> Unit, onLogout: () -> Unit) {
    val medicineViewModel = hiltViewModel<HomeViewModel>()
    val medicineState = medicineViewModel.medicineState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog){
        ShowAlert(onDismiss = { showDialog = false }, onLogout = {
            medicineViewModel.clearLocalDb()
            onLogout()
        })
    }
    Scaffold(containerColor = DarkSlateGrey,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                            Text(text = getGreetingMessage(), style = White18SemiBold, overflow = TextOverflow.Ellipsis)
                            Image(
                                modifier = Modifier.padding(start = 8.dp).size(24.dp),
                                painter = painterResource(R.drawable.ic_hand),
                                contentDescription = null
                            )
                        }
                        Text(
                            text = userName,
                            style = White16Medium,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = DarkSlateGrey,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        showDialog = true
                    }) {
                        Icon(painterResource(R.drawable.ic_logout), contentDescription = null)
                    }
                }
            )
        }) {
        Column(
            Modifier.padding(
                top = it.calculateTopPadding(),
                start = 12.dp,
                end = 12.dp
            )
        ) {
            HorizontalDivider()
            Spacer(Modifier.height(12.dp))

            when (val state = medicineState.value) {
                UiState.Loading -> {
                    ShimmerList()
                }

                is UiState.Success -> {
                    MedicineListScreen(medicines = state.data, onCardClick)
                }

                UiState.Empty -> {
                    EmptyScreen()
                }

                is UiState.Error -> {
                    ErrorScreen(state.message, onRetry = {
                        medicineViewModel.getMedicines()
                    })
                    Log.e("TAG", "HomeScreen: ${state.message}")
                }

                else -> Unit

            }
        }
    }
}

@Composable
fun MedicineListScreen(medicines: List<Medicine>, onItemClick: (Medicine) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(medicines) { medicine ->
            MedicineItem(medicine = medicine, onItemClick = onItemClick)
        }
    }
}

@Composable
fun ShimmerList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(3) {
            LoadingShimmer()
        }
    }
}

@Composable
fun EmptyScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No medicines found", color = Color.White)
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen("Test User", onCardClick = {}, {})
}

private fun getGreetingMessage(): String {
    val c = Calendar.getInstance()

    return when (c.get(Calendar.HOUR_OF_DAY)) {
        in 5..11 -> "Good Morning"
        in 12..15 -> "Good Afternoon"
        in 16..20 -> "Good Evening"
        in 21..23, in 0..4 -> "Good Night"
        else -> "Hello"
    }
}