package com.example.nexosapp.model

import com.google.gson.annotations.SerializedName
data class AnnulmentRequest(
    @SerializedName("receiptId") val receiptId: String,
    @SerializedName("rrn") val rrn: String,
)