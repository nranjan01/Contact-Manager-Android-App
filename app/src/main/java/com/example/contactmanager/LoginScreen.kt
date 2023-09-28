package com.example.contactmanager

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class LoginScreen : AppCompatActivity() {
    lateinit var dB: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        val signInB=findViewById<Button>(R.id.signIn)
        val reg=findViewById<Button>(R.id.register)
        val id=findViewById<TextInputEditText>(R.id.userId)
        signInB.setOnClickListener {
            val alrtDial = AlertDialog.Builder(this)
            alrtDial.setTitle("Are you Sure?")
            alrtDial.setMessage("Have You checked that it is your User id?")
            alrtDial.setIcon(R.drawable.icn)
            alrtDial.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(applicationContext,"SigningInnn Successfully",Toast.LENGTH_SHORT).show()

            dB = FirebaseDatabase.getInstance().getReference("Name")
            dB.child(id.editableText.toString()).get().addOnSuccessListener {
                if (it.exists()) {
                    val intent = Intent(applicationContext, MainScreen::class.java)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(applicationContext,"User Does'nt exist! please register.",Toast.LENGTH_SHORT).show()
                }
            }


            } )
                alrtDial.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i -> BaseTransientBottomBar.BaseCallback.DismissEvent() })
                alrtDial.show()

            }

        reg.setOnClickListener {
            Toast.makeText(applicationContext,"Fill Your details",Toast.LENGTH_SHORT).show()
            val intent= Intent(applicationContext,SignUp::class.java)
            startActivity(intent)
        }
    }
}