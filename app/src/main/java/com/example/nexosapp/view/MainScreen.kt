package com.example.nexosapp.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nexosapp.ui.theme.orange
import com.example.nexosapp.ui.theme.white
import com.example.nexosapp.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {
                viewModel.onBuyClick()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = orange,
                contentColor = white
            ),
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            shape = CircleShape
        ){
            Text(
                text = "Buy",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

    if(viewModel.isDialogShown){
        CustomDialog(mainViewModel = viewModel)
    }
}