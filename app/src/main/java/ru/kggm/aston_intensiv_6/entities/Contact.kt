package ru.kggm.aston_intensiv_6.entities

import android.os.ParcelUuid
import java.util.UUID
import kotlin.random.Random

data class Contact(
    val name: String,
    val surname: String,
    val phone: String,
    val imageUrl: String,
    val id: Long = Random.nextLong(),
)
