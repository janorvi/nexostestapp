package com.example.nexosapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nexosapp.viewmodel.MainViewModel

@Composable
fun AnnulledAuthorizationsScreen(
    mainViewModel: MainViewModel
){
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "Annulled authorizations")
        CustomRecyclerView(mainViewModel)
        mainViewModel.getAuthorizationsByStatus("Anulada")
    }
}