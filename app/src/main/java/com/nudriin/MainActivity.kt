package com.nudriin

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
//    Deklarasikan input button dan result text yang ada pada activity__main.xml
    private lateinit var edtLenght: EditText
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCalk: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val RESULT_STATE = "result_state"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Inisiasi properties dengan id yang ada pada activity_main.xml
        edtLenght = findViewById(R.id.edt_length) // mengambil id yng ada di activity main
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        btnCalk = findViewById(R.id.btn_calc)
        tvResult = findViewById(R.id.tv_result)
        btnCalk.setOnClickListener(this)

//        cek apakah ada state yang di simpan
        if(savedInstanceState != null) {
            val resultState = savedInstanceState.getString(RESULT_STATE)
            tvResult.text = resultState // ubah tv result menjadi statenya
        }
    }

    override fun onClick(v: View?) {
        if(v?.id == btnCalk.id) {
            val length = edtLenght.text.toString().trim() // di ubah ke string dan dihapus whitecpacenya
            val height = edtHeight.text.toString().trim()
            val width = edtWidth.text.toString().trim()

            var isEmptyInputValue = false

//            Cek apakah ada nilai yang kosong, jika ada kita return error
            if(length.isEmpty()) {
                isEmptyInputValue = true
                edtLenght.error = "Panjang tidak boleh kosong"
            }

            if(height.isEmpty()) {
                isEmptyInputValue = true
                edtHeight.error = "Tinggi tidak boleh kosong"
            }

            if(width.isEmpty()) {
                isEmptyInputValue = true
                edtWidth.error = "Tinggi tidak boleh kosong"
            }

            if(!isEmptyInputValue) {
                val result = length.toDouble() * height.toDouble() * width.toDouble() // casting ke double agar bisa di kalkulasi
                // mengubah text yang ada di tv result
                tvResult.text = result.toString()
            }
        }
        }

//    Membuat save instance state, menyimpan state apabila terjadi perubahan orientasi dan lain sebagainya
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(RESULT_STATE, tvResult.text.toString()) // kita simpan statenya yaitu state result
    }

    }