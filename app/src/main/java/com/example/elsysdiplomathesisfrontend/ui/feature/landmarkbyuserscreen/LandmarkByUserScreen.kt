package com.example.elsysdiplomathesisfrontend.ui.feature.landmarkbyuserscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elsysdiplomathesisfrontend.R

@Composable
fun LandmarkByUserScreen(
    stateValue: LandmarkByUserData,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    userAddLandmark: () -> Unit,
    onDropdownVisibility: (Boolean) -> Unit,
    onLandmarkClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.new_landmark_user),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(value = stateValue.name,
            onValueChange = onNameChange,
            singleLine = true,
            label = { Text(stringResource(R.string.name)) })

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = stateValue.description,
            onValueChange = onDescriptionChange,
            label = { Text(stringResource(R.string.description)) },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            TextField(value = stateValue.stationId.toString(),
                onValueChange = onDescriptionChange,
                label = { Text(stringResource(R.string.station_id)) },
                enabled = false,
                modifier = Modifier.clickable {
                    onDropdownVisibility(true)
                })

            DropdownMenu(
                expanded = stateValue.isDropdownVisible,
                onDismissRequest = {
                    onDropdownVisibility(false)
                },
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
            ) {
                getStationList().forEach {
                    Text(it.first, color = Color.Black, modifier = Modifier.clickable {
                        onDropdownVisibility(false)
                        onLandmarkClick(it.second)
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { userAddLandmark() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
        ) {
            Text(stringResource(R.string.add), color = Color.White)
        }
    }
}
