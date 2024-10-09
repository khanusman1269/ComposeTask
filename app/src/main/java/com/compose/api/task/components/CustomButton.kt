package com.compose.api.task.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.compose.api.task.theme.Dark

@Composable
fun CustomButton(buttonContent: @Composable () -> Unit,onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Dark
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 20.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 0.dp
        )
    ) {
        buttonContent()
    }
}