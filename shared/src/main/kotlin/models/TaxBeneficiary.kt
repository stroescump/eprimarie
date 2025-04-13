package models

import kotlinx.serialization.Serializable

@Serializable
data class TaxBeneficiary(val name: String, val cnp: String, val email: String, val phone: String)
