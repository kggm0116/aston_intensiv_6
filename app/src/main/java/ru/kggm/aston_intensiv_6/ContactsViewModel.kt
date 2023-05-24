package ru.kggm.aston_intensiv_6

import android.graphics.Color
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kggm.aston_intensiv_6.contact_list.recycler.ContactGenerator
import ru.kggm.aston_intensiv_6.entities.Contact
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

object ContactsViewModel {
    private val contactsList = mutableListOf<Contact>()
    private val contactsFlow = MutableStateFlow(listOf<Contact>())
    val contacts = contactsFlow.asStateFlow()

    init {
        thread {
            for (i in 0 until 100) {
                add(ContactGenerator.getRandom())
            }
        }
    }



    fun add(contact: Contact) {
        if (contactsList.add(contact))
            contactsFlow.value = contactsList.toList()
    }

    fun remove(contact: Contact) {
        if (contactsList.remove(contact))
            contactsFlow.value = contactsList.toList()
    }

    fun update(contact: Contact) {
        contactsList
            .indexOfFirst { it.id == contact.id }
            .takeIf { it != -1 }
            ?.let { index ->
                contactsList[index] = contact
                contactsFlow.value = contactsList
            }
    }

    fun getById(id: Long) = contactsList.firstOrNull { it.id == id }
        ?: throw IllegalArgumentException("Invalid contact id: $id")
}