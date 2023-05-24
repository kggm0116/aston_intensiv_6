package ru.kggm.aston_intensiv_6.contact_list.recycler

import android.graphics.Color
import android.util.Log
import com.github.javafaker.Faker
import ru.kggm.aston_intensiv_6.entities.Contact
import kotlin.random.Random
import kotlin.random.nextInt

object ContactGenerator {

    fun getRandom(): Contact {
        val (name, surname) = faker.funnyName().name().split(" ")
        val imageBg = getRandomColor()
        return Contact(
            name = name,
            surname = surname,
            phone = faker.phoneNumber().cellPhone(),
            imageUrl = "https://dummyimage.com/400x400/$imageBg/000.jpg&text=$name"
                .also { Log.i("ContactGenerator", "Generated image url: '$it'") }
        )
    }

    private val faker = Faker()

    private fun getRandomColor(): String {
        val primary = Integer.toHexString(Random.nextInt(128 until 192))
        val secondary = Integer.toHexString(Random.nextInt(0 until 192))
        val empty = "00"

        val channels = listOf(primary, secondary, empty).shuffled()
        return "${channels[0]}${channels[1]}${channels[2]}"
    }
}