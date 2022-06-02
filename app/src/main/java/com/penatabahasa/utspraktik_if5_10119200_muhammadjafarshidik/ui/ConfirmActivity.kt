package com.penatabahasa.utspraktik_if5_10119200_muhammadjafarshidik.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.penatabahasa.utspraktik_if5_10119200_muhammadjafarshidik.R
import com.penatabahasa.utspraktik_if5_10119200_muhammadjafarshidik.data.User
import com.penatabahasa.utspraktik_if5_10119200_muhammadjafarshidik.databinding.ActivityConfirmBinding
import kotlinx.android.synthetic.main.dialg_success.view.*

/*
2 Juni 2022
10119200
Muhammad Jafar Shidik
IF-5
*/

class ConfirmActivity : AppCompatActivity() {
    private var binding: ActivityConfirmBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setDataUser()
        iconBackPressed()
        btnOnCLick()
    }

    private fun btnOnCLick() {
        binding?.apply {
            btnSave.setOnClickListener {
                showDialogSuccess()
            }
            btnUpdate.setOnClickListener {

                val dataUser = intent.getParcelableExtra<User>("data")
                val user = User()
                user.typeTest = dataUser?.typeTest
                user.dateConfirmed = dataUser?.dateConfirmed
                user.nik = dataUser?.nik
                user.name = dataUser?.name
                user.dateOfBirth = dataUser?.dateOfBirth
                user.gender = dataUser?.gender
                user.relationship = dataUser?.relationship
                user.status = "update"


                val intent = Intent(
                    this@ConfirmActivity,
                    MainActivity::class.java
                )
                intent.putExtra("update", user)
                startActivity(intent)
            }
        }
    }

    private fun showDialogSuccess() {
//        val view = View.inflate(this@ConfirmActivity, R.layout.dialg_success, null)
//        val builder = AlertDialog.Builder(this@ConfirmActivity)
//        builder.setView(view)
//        val dialog = builder.create()
//
//        dialog.setCancelable(false)
//        dialog.show()

       //view.btnOk.setOnClickListener { finishAffinity() }

        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.dialg_success, null)
        val button = view.findViewById<Button>(R.id.btnOk)
        builder.setView(view)
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        builder.setContentView(R.layout.dialg_success)
        button.setOnClickListener {
            finishAffinity()
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()

        builder.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        builder.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder.window!!.attributes.windowAnimations = R.style.DialogAnimation
        builder.window!!.setGravity(Gravity.BOTTOM)

//        val dialog = Dialog(this)
//
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.dialg_success)
//        dialog.setCancelable(false)
//        dialog.show()
//        dialog.window!!.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
//        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

    private fun iconBackPressed() {
        binding?.apply {
            ivBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setDataUser() {
        binding?.apply {
            val dataUser = intent.getParcelableExtra<User>("data")
            tvTestTypeValue.text = dataUser!!.typeTest
            tvConfirmedDateValue.text = dataUser.dateConfirmed
            tvNIKValue.text = dataUser.nik
            tvNameValue.text = dataUser.name
            tvDateOfBirthValue.text = dataUser.dateOfBirth
            tvGenderValue.text = dataUser.gender
            tvRelationshipValue.text = dataUser.relationship
        }
    }
}