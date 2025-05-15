package com.example.elsysdiplomathesisfrontend.ui.feature.stationscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.elsysdiplomathesisfrontend.R
import com.example.elsysdiplomathesisfrontend.data.model.Landmark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StationScreen(
    navController: NavHostController,
    stationData: StationData,
    bottomSheetVisibility: (Boolean, Int) -> Unit
) {
    var selectedLandmark by remember { mutableStateOf<Landmark?>(null) }
    val sheetState =
        androidx.compose.material3.rememberModalBottomSheetState(skipPartiallyExpanded = false)

    val landmark = selectedLandmark

    if (stationData.isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                selectedLandmark = null
                bottomSheetVisibility(false, 0)
            },
            sheetState = sheetState,
            dragHandle = {
                BottomSheetDefaults.DragHandle()
            },
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.White,
        ) {
            if (landmark != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = landmark.name.orEmpty(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = stringResource(R.string.location, landmark.location.orEmpty()),
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Разстояние от станция: ${landmark.distanceFromStation ?: 0} метра",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = landmark.description.orEmpty(),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        stringResource(R.string.comments),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    if (stationData.comments.isEmpty()) {
                        Text(stringResource(R.string.no_comments), color = Color.Gray)
                    } else {
                        stationData.comments.forEach { comment ->
                            Column(modifier = Modifier.padding(bottom = 12.dp)) {
                                Text(text = comment.content.orEmpty())
                                Text(
                                    text = "- ${comment.User?.username.orEmpty()} • ${
                                        comment.createdAt?.substringBefore(
                                            "T"
                                        ) ?: ""
                                    }",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            } else {
                Box(modifier = Modifier.height(1.dp))
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.size(48.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (!stationData.station.name.isNullOrEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = stationData.station.name,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stationData.station.location.orEmpty(),
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        } else {
            Text(
                text = "Зареждане на данни за станцията...",
                fontSize = 18.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Забележителности:", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        stationData.landmarks.forEach { landmarkItem ->
            Button(
                onClick = {
                    selectedLandmark = landmarkItem
                    bottomSheetVisibility(true, landmarkItem.id ?: 0)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = landmarkItem.name.orEmpty(),
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
