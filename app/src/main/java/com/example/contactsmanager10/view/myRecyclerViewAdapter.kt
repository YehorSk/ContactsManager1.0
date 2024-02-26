package com.example.contactsmanager10.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsmanager10.R
import com.example.contactsmanager10.databinding.CardItemBinding
import com.example.contactsmanager10.room.Contact

class myRecyclerViewAdapter(private val contacts: List<Contact>,
    private val clickListener: (Contact)->Unit):RecyclerView.Adapter<myRecyclerViewAdapter.MyViewHolder>() {
        inner class MyViewHolder(val binding:CardItemBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(contact:Contact,clickListener: (Contact)->Unit){
                binding.nameTextView.text = contact.contact_name
                binding.emailTextView.text = contact.contact_email

                binding.listItemLayout.setOnClickListener{
                    clickListener(contact)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:CardItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contacts[position],clickListener)
    }
}