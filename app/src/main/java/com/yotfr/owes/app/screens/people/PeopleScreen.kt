@file:OptIn(ExperimentalMaterial3Api::class)

package com.yotfr.owes.app.screens.people

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
                    // TODO
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
        onClick = { onPersonClicked() }
    ) {
        Text(
            text = person.personName,
            modifier = Modifier.fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        )
        Text(
            text = "Total borrowed ${person.takenDebtAmount}",
            modifier = Modifier.fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        )
        Text(
            text = "Total given ${person.givenDebtAmount}",
            modifier = Modifier.fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        )
    }
}
