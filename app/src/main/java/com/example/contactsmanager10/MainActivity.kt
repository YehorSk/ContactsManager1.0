package com.example.contactsmanager10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsmanager10.databinding.ActivityMainBinding
import com.example.contactsmanager10.repository.ContactRepository
import com.example.contactsmanager10.room.Contact
import com.example.contactsmanager10.room.ContactDAO
import com.example.contactsmanager10.room.ContactDatabase
import com.example.contactsmanager10.view.myRecyclerViewAdapter
import com.example.contactsmanager10.viewmodel.ContactViewModel
import com.example.contactsmanager10.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = ContactDatabase.getInstance(applicationContext).contactDAO
        val repository = ContactRepository(dao)

        val factory = ViewModelFactory(repository)

        contactViewModel = ViewModelProvider(
            this, factory
        ).get(ContactViewModel::class.java)
        binding.contactViewModel = contactViewModel
        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        DisplayUsersList()
    }
    private fun DisplayUsersList(){
        contactViewModel.contacts.observe(this, Observer{
            binding.recyclerView.adapter = myRecyclerViewAdapter(
                it, { selectedItem:Contact->listItemClicked(selectedItem)}
            )
        })
    }

    private fun listItemClicked(selectedItem: Contact) {
        Toast.makeText(this,"Selected name is ${selectedItem.contact_name}",Toast.LENGTH_SHORT).show()

        contactViewModel.initUpdateAndDelete(selectedItem)

    }
}