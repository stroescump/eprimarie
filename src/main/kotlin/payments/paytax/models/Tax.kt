package com.digitalisierung.payments.paytax.models

import kotlinx.serialization.Serializable
import models.TaxBeneficiary
import models.TaxType

@Serializable
data class Tax(
    val taxType: TaxType,
    val amount: Float,
    val mentions: String,
    val taxBeneficiary: TaxBeneficiary
)
