package com.compose.api.task.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.api.task.domain.models.Medicine
import com.compose.api.task.theme.Dark13Regular
import com.compose.api.task.theme.Dark14Medium
import com.compose.api.task.theme.Dark14SemiBold

@Composable
fun MedicineItem(medicine: Medicine, onItemClick: (Medicine) -> Unit){
    Card(
        onClick = {
            onItemClick(medicine)
        },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = medicine.name,
                style = Dark14SemiBold
            )
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

@Preview
@Composable
fun MedicineItemPreview(){
    MedicineItem(medicine = Medicine("Paracetamol", "1 Tablet", "500mg", "Pain Relief"),
        onItemClick = {})
}