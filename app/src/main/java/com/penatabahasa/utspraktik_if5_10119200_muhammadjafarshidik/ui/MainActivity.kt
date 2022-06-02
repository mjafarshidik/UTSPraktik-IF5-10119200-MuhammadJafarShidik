package com.penatabahasa.utspraktik_if5_10119200_muhammadjafarshidik.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.penatabahasa.utspraktik_if5_10119200_muhammadjafarshidik.R
import com.penatabahasa.utspraktik_if5_10119200_muhammadjafarshidik.data.User
import com.penatabahasa.utspraktik_if5_10119200_muhammadjafarshidik.databinding.ActivityMainBinding

/*
2 Juni 2022
10119200
Muhammad Jafar Shidik
IF-5
*/

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var testType: String
    private lateinit var dateConfirmed: String
    private lateinit var nik: String
    private lateinit var name: String
    private lateinit var dateOfBirth: String
    private lateinit var gender: String
    private lateinit var relationship: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setDataUser()
        intentToConfirmActivity()
    }

    private fun intentToConfirmActivity() {
        binding?.apply {
            btnNext.setOnClickListener {
                val selectedTestType = rgGender.checkedRadioButtonId
                val rbTestType = findViewById<RadioButton>(selectedTestType)
                val selectedGender = rgGender.checkedRadioButtonId
                val rbGender = findViewById<RadioButton>(selectedGender)
                val selectedRelationship = rgRelationship.checkedRadioButtonId
                val rbRelationship = findViewById<RadioButton>(selectedRelationship)

                testType = rbTestType.text.toString()
                dateConfirmed = edtDateConfirmed.text.toString()
                nik = edtNIK.text.toString()
                name = edtName.text.toString()
                dateOfBirth = edtDateOfBirth.text.toString()
                gender = rbGender.text.toString()
                relationship = rbRelationship.text.toString()

                if (dateConfirmed == "" || dateConfirmed.isEmpty()) {
                    edtDateConfirmed.error =
                        "Silakan masukkan tanggal terkonfirmasi terlebih dahulu!"
                    edtDateConfirmed.requestFocus()
                } else if (name == "" || name.isEmpty()) {
                    edtName.error = "Silakan masukkan nama terlebih dahulu!"
                    edtName.requestFocus()
                } else if (dateOfBirth == "" || dateOfBirth.isEmpty()) {
                    edtDateOfBirth.error = "Silakan masukkan tanggal lahir terlebih dahulu!"
                    edtDateOfBirth.requestFocus()
                } else {
                    saveData(
                        testType, dateConfirmed, nik, name, dateOfBirth, gender, relationship
                    )
                }
            }
        }
    }

    private fun saveData(
        testType: String,
        dateConfirmed: String,
        nik: String,
        name: String,
        dateOfBirth: String,
        gender: String,
        relationship: String
    ) {
        binding?.apply {
            val user = User()
            user.typeTest = testType
            user.dateConfirmed = dateConfirmed
            user.nik = nik
            user.name = name
            user.dateOfBirth = dateOfBirth
            user.gender = gender
            user.relationship = relationship

            if (cbAgree.isChecked){
                val intent = Intent(
                    this@MainActivity,
                    ConfirmActivity::class.java
                )
                intent.putExtra("data", user)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Setujui data yang telah anda isi sudah benar", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setDataUser() {
        binding?.apply {
            val dataUser = intent.getParcelableExtra<User>("update")
            if (dataUser?.status == "update") {
                edtDateConfirmed.setText(dataUser.dateConfirmed)
                edtNIK.setText(dataUser.nik)
                edtName.setText(dataUser.name)
                edtDateOfBirth.setText(dataUser.dateOfBirth)

                when (dataUser.typeTest) {
                    "Rapid Antigen" -> {
                        rgGender.check(R.id.typeRapidAntigen)
                    }
                    "PCR" -> {
                        rgGender.check(R.id.typePCR)
                    }
                }

                when (dataUser.gender) {
                    "Perempuan" -> {
                        rgGender.check(R.id.genderFemale)
                    }
                    "Laki-laki" -> {
                        rgGender.check(R.id.genderMale)
                    }
                }

                when (dataUser.relationship) {
                    "Orangtua" -> {
                        rgRelationship.check(R.id.relationshipParent)
                    }
                    "Suami/Istri" -> {
                        rgRelationship.check(R.id.relationshipCouple)
                    }
                    "Anak" -> {
                        rgRelationship.check(R.id.relationshipChild)
                    }
                    "Kerabat Lainnya" -> {
                        rgRelationship.check(R.id.relationshipKin)
                    }
                }
            }
        }
    }
}