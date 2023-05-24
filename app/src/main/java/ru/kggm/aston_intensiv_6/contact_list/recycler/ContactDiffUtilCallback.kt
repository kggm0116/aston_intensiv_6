package ru.kggm.aston_intensiv_6.contact_list.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.kggm.aston_intensiv_6.entities.Contact

class ContactDiffUtilCallback(
    private val oldList: List<Contact>,
    private val newList: List<Contact>
) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}