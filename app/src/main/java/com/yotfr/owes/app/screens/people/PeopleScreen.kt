@file:OptIn(ExperimentalMaterial3Api::class)

package com.yotfr.owes.app.screens.people

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yotfr.owes.app.navigation.GlobalScreenRoutes
import com.yotfr.owes.app.screens.UiPerson
import com.yotfr.owes.app.utils.spacing

@Composable
fun PeopleScreen(
    navController: NavController,
    viewModel: PeopleViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    LazyColumn {
        items(state.value.persons) { person ->
            PersonItem(
                person = person,
                onPersonClicked = {
                    navController.navigate(
                        GlobalScreenRoutes.PersonDetailScreen.passPersonId(
                            personId = person.id
                        )
                    )
                }
            )
        }
    }
}

@Composable
fun PersonItem(
    person: UiPerson,
    onPersonClicked: () -> Unit
) {
    OutlinedCard(
        onClick = { onPersonClicked() },
        modifier = Modifier.fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = person.personName,
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.medium,
                        end = MaterialTheme.spacing.medium,
                        top = MaterialTheme.spacing.medium
                    )
                )
                Text(
                    text = "Total borrowed ${person.takenDebtAmount}",
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.medium,
                        end = MaterialTheme.spacing.medium,
                        top = MaterialTheme.spacing.medium
                    ),
                    color = Color.Red
                )
                Text(
                    text = "Total given ${person.givenDebtAmount}",
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.medium,
                        end = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.medium
                    ),
                    color = Color.Blue
                )
            }
            Text(
                modifier = Modifier.padding(
                    end = MaterialTheme.spacing.medium
                ),
                text = person.totalAmount,
                color = if (person.totalAmount.toLong() < 0L) Color.Red else Color.Blue
            )
        }
    }
}
