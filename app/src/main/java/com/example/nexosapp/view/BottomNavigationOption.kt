package com.example.nexosapp.view

import com.example.nexosapp.R

sealed class BottomNavigationOption(
    val icon: Int,
    val tittle: String,
    val route: String
){
    object AuthorizationsScreen: BottomNavigationOption(R.drawable.outline_check_circle_24, "Aprobadas", "authorizations_screen")
    object AnnulmentsScreen: BottomNavigationOption(R.drawable.outline_cancel_24, "Anuladas", "annulments_screen")
}