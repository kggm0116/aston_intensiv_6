package ru.kggm.aston_intensiv_6.contact_list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import ru.kggm.aston_intensiv_6.R
import ru.kggm.aston_intensiv_6.databinding.LayoutContactBinding
import ru.kggm.aston_intensiv_6.entities.Contact
import ru.kggm.aston_intensiv_6.utility.setDebouncedClickListener

class ContactListAdapter(
) : ListAdapter<Contact, ContactListAdapter.ContentViewHolder>(
    ContactDiffUtil
) {
    var onItemClicked: (Contact) -> Unit = { }
    var onItemLongClicked: (Contact) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutContactBinding.inflate(inflater, parent, false)
        return ContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.root.setDebouncedClickListener { onItemClicked(item) }
        holder.binding.root.setOnLongClickListener { onItemLongClicked(item); true }
    }

    class ContentViewHolder(val binding: LayoutContactBinding) : ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.apply {
                textFullName.text = binding.root.context.getString(
                    R.string.template_full_name,
                    contact.name,
                    contact.surname
                )
                textPhone.text = contact.phone
                image.load(contact.imageUrl) { crossfade(true) }
                image.clipToOutline = true
            }
        }
    }
}