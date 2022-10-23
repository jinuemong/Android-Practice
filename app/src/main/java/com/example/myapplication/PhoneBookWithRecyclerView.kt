package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_phone_book_with_recycler_view.*

class PhoneBookWithRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_book_with_recycler_view)
        val phoneBook = createPhoneBook(faskeNumber = 30)
        val phoneBookRecyclerAdapter = PhoneBookRecyclerAdapter(phonebookList=phoneBook
            , LayoutInflater.from(this@PhoneBookWithRecyclerView)
            , activity = this)
        phonebook_recycler_view.adapter = phoneBookRecyclerAdapter
        phonebook_recycler_view.layoutManager = LinearLayoutManager(this@PhoneBookWithRecyclerView)
    }

    fun createPhoneBook(faskeNumber:Int = 10, phoneBook: PhoneBook = PhoneBook()) :
            PhoneBook {
        for (i in 0 until faskeNumber) {
            phoneBook.addPerson(
                Person(name =""+i+"번째 사람", number = ""+i+"번째 사람의 전화 번호")
            )
        }
        return phoneBook
    }
}

class PhoneBookRecyclerAdapter(
    val phonebookList : PhoneBook,
    val inflater : LayoutInflater,
    val activity : Activity
): RecyclerView.Adapter<PhoneBookRecyclerAdapter.ViewHoler>(){
    inner class ViewHoler(itemView: View): RecyclerView.ViewHolder(itemView){
        val personName :TextView
        init {
            personName = itemView.findViewById(R.id.person_name)
            itemView.setOnClickListener {
                //intent에서는 activity를 보내줘야 한다
                val intent = Intent (activity,PhonebookDetailActivity::class.java)
                intent.putExtra("name",phonebookList.personList.get(bindingAdapterPosition).name)
                intent.putExtra("number",phonebookList.personList.get(bindingAdapterPosition).number)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        val view = inflater.inflate(R.layout.phonebook_item,parent,false)
        return ViewHoler(view)
    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        holder.personName.setText(phonebookList.personList.get(position).name)
    }

    override fun getItemCount(): Int {
        return phonebookList.personList.size
    }
}

class PhoneBook(){
    val personList  = ArrayList<Person>()
    fun addPerson(person:Person){
        personList.add(person)
    }
}
class Person(val name: String,var number:String){

}