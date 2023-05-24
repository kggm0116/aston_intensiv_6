package ru.kggm.aston_intensiv_6

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kggm.aston_intensiv_6.contact_list.recycler.ContactGenerator
import ru.kggm.aston_intensiv_6.entities.Contact
import kotlin.concurrent.thread

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

    var nameSurnameFilter = ""
        set(value) {
            if (field != value) {
                field = value
                emitContacts()
            }
        }

    private fun emitContacts() {
        contactsFlow.value = contactsList.filter {
            it.name.contains(nameSurnameFilter, true)
                    || it.surname.contains(nameSurnameFilter, true)
        }
    }

    fun add(contact: Contact) {
        if (contactsList.add(contact))
            emitContacts()
    }

    fun remove(contact: Contact) {
        if (contactsList.remove(contact))
            emitContacts()
    }

    fun update(contact: Contact) {
        contactsList
            .indexOfFirst { it.id == contact.id }
            .takeIf { it != -1 }
            ?.let { index ->
                contactsList[index] = contact
                emitContacts()
            }
    }

    fun getById(id: Long) = contactsList.firstOrNull { it.id == id }
        ?: throw IllegalArgumentException("Invalid contact id: $id")
}