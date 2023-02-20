package com.yotfr.owes.app.navigation

const val DEBT_ID_ARGUMENT_KEY = "debtId"
const val WITHOUT_DEBT_ID = -1L

sealed class TopLevelScreenRoutes(val route: String) {
    object DebtDetailsScreen : TopLevelScreenRoutes(route = "debtDetails?debtId={debtId}") {
        fun passDebtId(debtId: Long = WITHOUT_DEBT_ID): String {
            return "debtDetails?debtId=$debtId"
        }
    }
}
