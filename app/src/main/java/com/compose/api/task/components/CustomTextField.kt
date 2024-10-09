package com.compose.api.task.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.api.task.theme.Dark
import com.compose.api.task.theme.Dark13Regular
import com.compose.api.task.theme.Grey13Regular
import com.compose.api.task.theme.LightGrey

@Composable
fun CustomTextField(
    placeHolderText: String,
    value: String,
    visualTransformation:VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        textStyle = Dark13Regular,
        onValueChange = onValueChange,
        placeholder = {
            Text(placeHolderText, style = Grey13Regular)
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(12.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.colors().copy(
            errorTextColor = Color.Red,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = LightGrey,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = LightGrey,
            cursorColor = Dark,
        )
    )
}

@Preview
@Composable
fun CustomTextFieldPreview() {
    CustomTextField(
        placeHolderText = "Email address",
        value = "",
        leadingIcon = {
            Icon(Icons.Outlined.Email, contentDescription = null)
        },

        keyboardActions = KeyboardActions()
    ) {

    }
}