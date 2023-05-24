package ru.kggm.aston_intensiv_5

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.kggm.aston_intensiv_5.databinding.ActivityContactsBinding

class ContactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}