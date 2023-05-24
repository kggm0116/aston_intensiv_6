package ru.kggm.aston_intensiv_6.contact_list.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.kggm.aston_intensiv_6.entities.Contact

object ContactDiffUtil : DiffUtil.ItemCallback<Contact>() {

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}