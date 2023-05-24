package ru.kggm.aston_intensiv_5.contact_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.kggm.aston_intensiv_5.ContactsViewModel
import ru.kggm.aston_intensiv_5.R
import ru.kggm.aston_intensiv_5.contact_details.ContactDetailsFragment
import ru.kggm.aston_intensiv_5.databinding.FragmentContactListBinding
import ru.kggm.aston_intensiv_5.databinding.LayoutContactBinding
import ru.kggm.aston_intensiv_5.entities.Contact

class ContactListFragment : Fragment() {
    private lateinit var binding: FragmentContactListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateContacts()
    }

    private fun populateContacts() {
        binding.layoutContacts.removeAllViews()
        ContactsViewModel.contacts.forEach { contact ->
            binding.layoutContacts.addView(
                getContactView(contact)
            )
        }
    }

    private fun getContactView(contact: Contact): View {
        val binding = LayoutContactBinding.inflate(layoutInflater)
        binding.textFullName.text = getString(
            R.string.template_full_name,
            contact.name,
            contact.surname
        )
        binding.textPhone.text = contact.phone
        binding.root.setOnClickListener { openDetails(contact) }
        return binding.root
    }

    private fun openDetails(contact: Contact) {
        val detailsFragment = ContactDetailsFragment().apply {
            arguments = bundleOf(ContactDetailsFragment.ARG_CONTACT_ID to contact.id)
        }

        parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.fragment_container_contacts, detailsFragment)
            .addToBackStack(null)
            .commit()
    }
}