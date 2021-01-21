package com.pri.testapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup

class PopUp_model : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_up_model)

        val radioModel: RadioGroup = findViewById(R.id.radioGroupModel)
        val btnSelectModel: Button = findViewById(R.id.btn_modelSelect)

        btnSelectModel.setOnClickListener {
            val returnIntent = Intent()
            var returnRadioText = String()
            when(radioModel.checkedRadioButtonId){
                R.id.radioModel1 -> {returnRadioText = "Model1"}
                R.id.radioModel2 -> {returnRadioText = "Model2"}
                R.id.radioModel3 -> {returnRadioText = "Model3"}
            }
            returnIntent.putExtra("returnValue", returnRadioText)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}