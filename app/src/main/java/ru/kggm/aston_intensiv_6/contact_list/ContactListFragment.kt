package ru.kggm.aston_intensiv_6.contact_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.kggm.aston_intensiv_6.ContactsViewModel
import ru.kggm.aston_intensiv_6.R
import ru.kggm.aston_intensiv_6.contact_details.ContactDetailsFragment
import ru.kggm.aston_intensiv_6.contact_list.recycler.ContactListAdapter
import ru.kggm.aston_intensiv_6.contact_list.recycler.ContactRecyclerDivider
import ru.kggm.aston_intensiv_6.databinding.FragmentContactListBinding
import ru.kggm.aston_intensiv_6.entities.Contact

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
        initializeRecycler()
    }

    private val contactsAdapter by lazy { ContactListAdapter() }
    private fun initializeRecycler() {
        binding.recyclerContacts.adapter = contactsAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            ContactsViewModel.contacts.collect {
                contactsAdapter.submitList(it)
            }
        }
        binding.recyclerContacts.addItemDecoration(
            ContactRecyclerDivider(requireContext())
        )
        contactsAdapter.onItemClicked = { openDetails(it) }
        contactsAdapter.onItemLongClicked = { remove(it) }
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

    private fun remove(contact: Contact) {
        ContactsViewModel.remove(contact)
    }
}