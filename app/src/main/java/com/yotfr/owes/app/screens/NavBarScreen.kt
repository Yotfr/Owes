package com.yotfr.owes.app.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yotfr.owes.app.navigation.*
import com.yotfr.owes.app.screens.debtdetails.DebtDetailsScreen
import com.yotfr.owes.app.screens.givendebts.GivenDebtsScreen
import com.yotfr.owes.app.screens.people.PeopleScreen
import com.yotfr.owes.app.screens.persondetails.PersonDetailsScreen
import com.yotfr.owes.app.screens.takendebts.TakenDebtsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBarScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        },
        floatingActionButton = {
            FAB(
                onClick = {
                    navController.navigate(
                        GlobalScreenRoutes.DebtDetailsScreen.passDebtId()
                    )
                },
                navController = navController
            )
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
                PeopleScreen(navController = navController)
            }
            composable(
                route = GlobalScreenRoutes.PersonDetailScreen.route,
                arguments = listOf(
                    navArgument(PERSON_ID_ARGUMENT_KEY) {
                        type = NavType.LongType
                    }
                )
            ) {
                PersonDetailsScreen(navController = navController)
            }
            composable(
                route = GlobalScreenRoutes.DebtDetailsScreen.route,
                arguments = listOf(
                    navArgument(DEBT_ID_ARGUMENT_KEY) {
                        type = NavType.LongType
                        defaultValue = WITHOUT_DEBT_ID
                    }
                )
            ) {
                DebtDetailsScreen(navController = navController)
            }
        }
    }
}

@Composable
fun FAB(
    onClick: () -> Unit,
    navController: NavHostController
) {
    val navItems = listOf(
        NavBarScreenRoutes.TakenDebts,
        NavBarScreenRoutes.GivenDebts,
        NavBarScreenRoutes.People
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = navItems.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        FloatingActionButton(
            onClick = {
                onClick()
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Add debt"
            )
        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val navItems = listOf(
        NavBarScreenRoutes.TakenDebts,
        NavBarScreenRoutes.GivenDebts,
        NavBarScreenRoutes.People
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = navItems.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar {
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
                    icon = {
                        Icon(
                            painter = painterResource(navBarScreenRoutes.icon),
                            contentDescription = "Icon"
                        )
                    }
                )
            }
        }
    }
}
