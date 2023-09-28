package com.example.contactmanager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUp : AppCompatActivity() {
    lateinit var dB: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val signUpBtn=findViewById<Button>(R.id.signUp)
        val username=findViewById<TextInputEditText>(R.id.userId)
        val name=findViewById<TextInputEditText>(R.id.name)
        val email=findViewById<TextInputEditText>(R.id.email)
        val password=findViewById<TextInputEditText>(R.id.password)
        signUpBtn.setOnClickListener {
            val id=username.editableText.toString()
            val m=email.editableText.toString()
            val n=name.editableText.toString()
            val pas=password.editableText.toString()
            val dataClass= userDetails(id,n,m,pas)

            dB= FirebaseDatabase.getInstance().getReference("Name")
            dB.child(id).setValue(dataClass).addOnSuccessListener {
                Toast.makeText(applicationContext,"Registered SuccessFully",Toast.LENGTH_SHORT).show()
                val intent= Intent(applicationContext,MainScreen::class.java)
                startActivity(intent)
            }
            Toast.makeText(applicationContext,"Registered",Toast.LENGTH_SHORT).show()
         }
    }
}