package com.yotfr.owes.app.navigation

const val DEBT_ID_ARGUMENT_KEY = "debtId"
const val PERSON_ID_ARGUMENT_KEY = "personId"
const val WITHOUT_DEBT_ID = -1L

sealed class GlobalScreenRoutes(val route: String) {
    object DebtDetailsScreen : GlobalScreenRoutes(route = "debtDetails?debtId={debtId}") {
        fun passDebtId(debtId: Long = WITHOUT_DEBT_ID): String {
            return "debtDetails?debtId=$debtId"
        }
    }
    object PersonDetailScreen : GlobalScreenRoutes(route = "personDetails?personId={personId}") {
        fun passPersonId(personId: Long): String {
            return "personDetails?personId=$personId"
        }
    }
}
