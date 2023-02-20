package com.yotfr.owes.app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yotfr.owes.app.utils.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DebtItem(
    debtAmount: String,
    debtCommentary: String,
    personName: String,
    repaymentDate: String,
    onItemClicked: () -> Unit,
    onPersonClicked: () -> Unit
) {
    OutlinedCard(
        onClick = { onItemClicked() },
        modifier = Modifier.fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        shape = CutCornerShape(
            topEnd = 16.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.small,
                    vertical = MaterialTheme.spacing.small
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = debtAmount,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                modifier = Modifier.weight(2f),
                text = debtCommentary,
                textAlign = TextAlign.Center
            )
        }
        Divider()
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = repaymentDate
            )
            TextButton(onClick = { onPersonClicked() }) {
                Text(text = personName)
            }
        }
    }
}
