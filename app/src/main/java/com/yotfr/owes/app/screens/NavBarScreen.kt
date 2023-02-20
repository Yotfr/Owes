package com.yotfr.owes.app.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yotfr.owes.app.navigation.NavBarScreenRoutes
import com.yotfr.owes.app.screens.debtdetails.DebtDetailsSceen
import com.yotfr.owes.app.screens.givendebts.GivenDebtsScreen
import com.yotfr.owes.app.screens.takendebts.TakenDebtsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBarScreen() {
    val navController = rememberNavController()
    val navItems = listOf(
        NavBarScreenRoutes.TakenDebts,
        NavBarScreenRoutes.GivenDebts,
        NavBarScreenRoutes.People
    )
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navItems.forEach { navBarScreenRoutes ->
                    NavigationBarItem(
                        label = { Text(text = stringResource(id = navBarScreenRoutes.label)) },
                        selected = currentDestination?.hierarchy?.any { it.route == navBarScreenRoutes.route } == true,
                        onClick = {
                            navController.navigate(navBarScreenRoutes.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {}
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("debtDetails")
            }) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add debt"
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = NavBarScreenRoutes.GivenDebts.route,
            Modifier.padding(innerPadding)
        ) {
            composable(NavBarScreenRoutes.GivenDebts.route) {
                GivenDebtsScreen(navController = navController)
            }
            composable(NavBarScreenRoutes.TakenDebts.route) {
                TakenDebtsScreen(navController = navController)
            }
            composable(NavBarScreenRoutes.People.route) {

            }
            composable("debtDetails") {
                DebtDetailsSceen(navController = navController)
            }
        }
    }
}
