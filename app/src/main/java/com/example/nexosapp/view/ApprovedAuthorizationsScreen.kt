package com.example.nexosapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nexosapp.R
import com.example.nexosapp.ui.theme.orange
import com.example.nexosapp.ui.theme.white
import com.example.nexosapp.viewmodel.MainViewModel

@Composable
fun ApprovedAuthorizationsScreen(
    mainViewModel: MainViewModel
){
    val authorizationId: String by mainViewModel.authorizationId.observeAsState(initial = "")

    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            searchAuthorizationTextField(authorizationId = authorizationId){
                mainViewModel.onAuthorizationIDChanged(it)
            }
            Button(
                onClick = {
                    mainViewModel.getAuthorizationsByNumber()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = orange,
                    contentColor = white
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = CircleShape
            ){
                Image(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "klarna",
                    modifier = Modifier.fillMaxWidth(0.2f).clip(RoundedCornerShape(15.dp))
                )
            }
        }
        CustomRecyclerView(mainViewModel)
        mainViewModel.getAuthorizationsByStatus("Aprobada")
    }
}

@Composable
fun searchAuthorizationTextField(authorizationId: String, onAuthorizationIdChanged:(String) -> Unit){
    OutlinedTextField(
        value = authorizationId,
        onValueChange = {
            onAuthorizationIdChanged(it)
        },
        label = {
            Text(
                "Authorization ID"
            )
        }
    )
}