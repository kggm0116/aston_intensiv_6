package ru.kggm.aston_intensiv_5

import ru.kggm.aston_intensiv_5.entities.Contact

object ContactsViewModel {
    private val contactsList = mutableListOf<Contact>()
    val contacts get() = contactsList.toList()

    init {
        add(Contact("Karim", "Gimaletdinov", "8 (111) 222-33-44"))
        add(Contact("Kuzma", "Ivanov", "8 (123) 456-78-90"))
        add(Contact("Rinat", "Khzulin", "8 (999) 888-77-66"))
    }

    fun add(contact: Contact) {
        if (contactsList.none { it.id == contact.id })
            contactsList.add(contact)
    }

    fun update(contact: Contact) {
        contactsList
            .indexOfFirst { it.id == contact.id }
            .takeIf { it != -1 }
            ?.let { index ->
                contactsList.set(index, contact)
            }
    }

    fun getById(id: Long) = contacts.firstOrNull { it.id == id }
        ?: throw IllegalArgumentException("Invalid contact id: $id")
}