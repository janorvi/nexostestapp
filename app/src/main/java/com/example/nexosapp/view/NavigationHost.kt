package com.example.nexosapp.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nexosapp.viewmodel.MainViewModel

@Composable
fun NavigationHost(navHostController: NavHostController, mainViewModel: MainViewModel){
    NavHost(
        navController = navHostController,
        startDestination = BottomNavigationOption.AuthorizationsScreen.route
    ){
        composable(BottomNavigationOption.AuthorizationsScreen.route){
            ApprovedAuthorizationsScreen(mainViewModel = mainViewModel)
        }
        composable(BottomNavigationOption.AnnulmentsScreen.route){
            AnnulledAuthorizationsScreen(mainViewModel = mainViewModel)
        }
    }
}
