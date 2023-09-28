package com.example.contactmanager
import ContactClass
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.contactmanager.R.*
import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback.DismissEvent
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
class MainScreen : AppCompatActivity() {
    lateinit var dB: DatabaseReference
    lateinit var dialg:Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main_screen)
        val contactName= findViewById<TextInputEditText>(id.name)
        val mailId= findViewById<TextInputEditText>(id.email)
        val contactNumber= findViewById<TextInputEditText>(id.mobileNo)
        val saveBtn= findViewById<Button>(id.save)

        dialg= Dialog(this)
        dialg.setContentView(layout.custom_dialog)
        val bt=dialg.findViewById<Button>(R.id.btn)
        bt.setOnClickListener {
            dialg.dismiss()
        }
        saveBtn.setOnClickListener {
            dB= FirebaseDatabase.getInstance().getReference("Contact")
            val n:String= contactName.editableText.toString()
            val m:String= mailId.editableText.toString()
            val mob:String= contactNumber.editableText.toString()
            val cntClass = ContactClass(n,m,mob)

            dB.child(mob).setValue(cntClass).addOnSuccessListener {
                Toast.makeText(applicationContext,"Successfully Saved",Toast.LENGTH_SHORT).show()

            }
                dialg.show()
        }
    }
}