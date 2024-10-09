package com.compose.api.task.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.api.task.domain.models.Medicine
import com.compose.api.task.theme.Dark13Regular
import com.compose.api.task.theme.Dark14Medium
import com.compose.api.task.theme.Dark14SemiBold
import com.compose.api.task.theme.DarkSlateGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailScreen(medicine: Medicine, onBackPress: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "For ${medicine.condition}")
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = DarkSlateGrey,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        }) {
            Column(modifier = Modifier.fillMaxWidth().
                    padding(vertical =it.calculateTopPadding() + 16.dp, horizontal = 12.dp)) {
                Spacer(Modifier.height(24.dp))
                Text(
                    text = medicine.name,
                    style = Dark14SemiBold
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Disease: ",
                        style = Dark14Medium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = medicine.condition,
                        style = Dark13Regular,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Dose: ",
                        style = Dark14Medium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = medicine.dose,
                        style = Dark13Regular,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Strength: ",
                        style = Dark14Medium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = medicine.strength,
                        style = Dark13Regular,
                        modifier = Modifier.weight(1f)
                    )
            }
        }
    }
}
