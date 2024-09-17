package com.example.studentregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    lateinit var txtName: EditText
    lateinit var txtSurname: EditText
    lateinit var txtEmail: EditText
    lateinit var txtPassword: EditText
    lateinit var buttonRegistration: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtName = findViewById(R.id.txtinputname)
        txtSurname = findViewById(R.id.txtinputsurname)
        txtEmail = findViewById(R.id.txtinputemail)
        txtPassword = findViewById(R.id.txtinputpassword)
        buttonRegistration = findViewById(R.id.btnregister)

        buttonRegistration.setOnClickListener ({
            addNewData()
        })
    }

    private fun addNewData() {
        val stuname = txtName.text.toString().trim()
        val stusurname = txtSurname.text.toString().trim()
        val stuemail = txtEmail.text.toString().trim()
        val stupassword = txtPassword.text.toString().trim()

        if(stuname.isEmpty()){
            txtName.error = "Please enter student name"
            return
    }

     val ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://studentregistration-e55f6-default-rtdb.firebaseio.com/")
        val sid: String?
        sid = ref.push().key

     val student = Student(sid,stuname,stusurname,stuemail,stuemail)
     val addOnCompleteListener = ref.child(sid.toString()).setValue(student)
         .addOnCompleteListener {
             Toast.makeText(applicationContext, "Successfully" , Toast.LENGTH_LONG).show()
         }




}
}