package com.example.nexosapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nexosapp.model.AnnulmentRequest
import com.example.nexosapp.model.Authorization
import com.example.nexosapp.ui.theme.orange
import com.example.nexosapp.ui.theme.white
import com.example.nexosapp.viewmodel.MainViewModel

@Composable
fun CustomRecyclerView(mainViewModel: MainViewModel){
    val authorizationsList: List<Authorization> by mainViewModel.authorizationList.observeAsState(initial = arrayListOf())
    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp)
    ){
        itemsIndexed(items = authorizationsList){ index, item ->
            itemRecyclerView(item, mainViewModel)
        }
    }
}

@Composable
fun itemRecyclerView(authorization: Authorization, mainViewModel: MainViewModel){
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
    ){
        Column(
            modifier = Modifier.padding(24.dp).fillMaxWidth(),
        ){
            Row(

            ){
                Column(
                    modifier = Modifier.weight(1f)
                ){
                    Text( text = "Authorization")
                    Text(
                        text = "ID: " + authorization.id,
                        style = MaterialTheme.typography.h5.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "CARD No: " + authorization.card,
                        style = MaterialTheme.typography.h5.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                if(authorization.authorizationStatus == "Aprobada") {
                    Button(
                        onClick = {
                            mainViewModel.cancelAuthorization(
                                AnnulmentRequest(
                                    authorization.receiptId,
                                    authorization.rrn
                                )
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = orange,
                            contentColor = white
                        ),
                        modifier = Modifier.width(20.dp).weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Anular",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}