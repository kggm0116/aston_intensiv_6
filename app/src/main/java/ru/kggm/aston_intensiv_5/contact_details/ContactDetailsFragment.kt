package ru.kggm.aston_intensiv_5.contact_details

import android.os.Bundle
import android.os.ParcelUuid
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.kggm.aston_intensiv_5.ContactsViewModel
import ru.kggm.aston_intensiv_5.databinding.FragmentContactDetailsBinding

class ContactDetailsFragment : Fragment() {
    private lateinit var binding: FragmentContactDetailsBinding
    private val contactId by lazy {
        arguments?.getLong(ARG_CONTACT_ID)
            ?: throw IllegalStateException("Could not retrieve contact id in ContactDetailsFragment")
    }
    private val contact by lazy { ContactsViewModel.getById(contactId) }

    companion object {
        const val ARG_CONTACT_ID = "arg_contact_id"
        private const val STATE_ARG_NAME_TEXT = "arg_name_text"
        private const val STATE_ARG_SURNAME_TEXT = "arg_surname_text"
        private const val STATE_ARG_PHONE_TEXT = "arg_phone_text"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populate(savedInstanceState)
    }

    private fun populate(savedInstanceState: Bundle?) {
        binding.inputTextName.setText(
            savedInstanceState?.getString(STATE_ARG_NAME_TEXT) ?: contact.name
        )
        binding.inputTextSurname.setText(
            savedInstanceState?.getString(STATE_ARG_SURNAME_TEXT) ?: contact.surname
        )
        binding.inputTextPhone.setText(
            savedInstanceState?.getString(STATE_ARG_PHONE_TEXT) ?: contact.phone
        )
        binding.buttonContactSave.setOnClickListener { onSaveButtonClick() }
    }

    private fun onSaveButtonClick() {
        ContactsViewModel.update(contact.copy(
            name = binding.inputTextName.text.toString(),
            surname = binding.inputTextSurname.text.toString(),
            phone = binding.inputTextPhone.text.toString()
        ))
        parentFragmentManager.popBackStack()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putAll(bundleOf(
            STATE_ARG_NAME_TEXT to binding.inputTextName.text,
            STATE_ARG_SURNAME_TEXT to binding.inputTextSurname.text,
            STATE_ARG_PHONE_TEXT to binding.inputTextPhone.text
        ))
    }
}