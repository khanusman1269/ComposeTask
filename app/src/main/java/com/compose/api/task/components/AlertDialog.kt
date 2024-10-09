package com.compose.api.task.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.compose.api.task.theme.Dark
import com.compose.api.task.theme.Dark13Regular
import com.compose.api.task.theme.White14Regular

@Composable
fun ShowAlert(onDismiss: () -> Unit, onLogout: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White,
                contentColor = Dark)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("It'll reset data and it'll fetched again from server", style = Dark13Regular)
                Spacer(modifier = Modifier.height(6.dp))

                CustomButton(onClick = {
                    onDismiss()
                    onLogout()
                }, buttonContent = {
                    Text("Yes", style = White14Regular)
                })

                Spacer(modifier = Modifier.width(16.dp))

                CustomButton(onClick = onDismiss, buttonContent = {
                    Text("Cancel", style = White14Regular)
                })
                Spacer(modifier = Modifier.width(4.dp))

                Spacer(Modifier.navigationBarsPadding())
            }
        }
    }
}

@Preview
@Composable
fun ShowAlertPreview() {
    ShowAlert(onDismiss = {}, onLogout = {})
}
