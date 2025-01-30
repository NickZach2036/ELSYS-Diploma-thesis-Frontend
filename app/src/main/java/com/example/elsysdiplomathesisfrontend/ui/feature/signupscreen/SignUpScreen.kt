package com.example.elsysdiplomathesisfrontend.ui.feature.signupscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elsysdiplomathesisfrontend.R

@Composable
fun SignUpScreen(
    stateValue: SignUpData,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onVisibilityChange: (Boolean) -> Unit,
    onSignUpClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Регистрирай се!", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(32.dp))

        TextField(value = stateValue.username,
            onValueChange = onUsernameChange,
            label = { Text(stringResource(R.string.username)) })

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = stateValue.password,
            onValueChange = onPasswordChange,
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = if (stateValue.visibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(imageVector = if (stateValue.visibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = "visibility",
                    modifier = Modifier.clickable { onVisibilityChange(!stateValue.visibility) })
            })

        Spacer(modifier = Modifier.height(32.dp))

        TextButton(onClick = { onSignUpClicked() }) {
            Text(
                stringResource(R.string.sign_up), fontSize = 28.sp
            )
        }
    }
}
