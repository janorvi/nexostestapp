package com.example.nexosapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nexosapp.ui.theme.NexosappTheme
import com.example.nexosapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NexosappTheme {
                MainScreenOne(mainViewModel)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenOne(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val navigationItem = listOf(
        BottomNavigationOption.AuthorizationsScreen,
        BottomNavigationOption.AnnulmentsScreen
    )
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(navController, navigationItem)
        },
        floatingActionButton = {
            Fab(mainViewModel)
        },
        isFloatingActionButtonDocked = true
    ){
        NavigationHost(navController, mainViewModel)
    }

    if(mainViewModel.isDialogShown){
        CustomDialog(mainViewModel)
    }
}

@Composable
fun Fab(mainViewModel: MainViewModel){
    FloatingActionButton(
        onClick = {
            mainViewModel.onBuyClick()
        },
        backgroundColor = androidx.compose.material.MaterialTheme.colors.primaryVariant
    ){
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Recompensas"
        )
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}

@Composable
fun BottomNavigation(
    navController: NavHostController,
    bottomNavigationOptions: List<BottomNavigationOption>
){
    BottomAppBar(
        cutoutShape = androidx.compose.material.MaterialTheme.shapes.small.copy(
            CornerSize(percent = 45)
        )
    ){
        BottomNavigation{
            val currentRoute = currentRoute(navController = navController)
            bottomNavigationOptions.forEach{ bottomNavigationItem ->
                BottomNavigationItem(
                    selected = currentRoute == bottomNavigationItem.route,
                    onClick = {navController.navigate(bottomNavigationItem.route)},
                    icon = {
                        Icon(
                            painter = painterResource(id = bottomNavigationItem.icon),
                            contentDescription = bottomNavigationItem.tittle
                        )
                    },
                    label = {
                        Text(bottomNavigationItem.tittle)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NexosappTheme {
        //Greeting("Android")
    }
}