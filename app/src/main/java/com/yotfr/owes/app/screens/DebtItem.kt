package com.yotfr.owes.app.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DebtItem(
    debtAmount: String,
    debtCommentary: String,
    personName: String,
    onItemClicked: () -> Unit,
    onPersonClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { onItemClicked() }
    ) {
        Text(text = debtAmount)
        Text(text = debtCommentary)
        TextButton(onClick = { onPersonClicked() }) {
            Text(text = personName)
        }
    }
}
