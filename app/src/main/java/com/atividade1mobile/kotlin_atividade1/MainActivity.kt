package com.atividade1mobile.kotlin_atividade1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var result: Int? = 0

    fun calculate(view: View) {
        dicoverBest()
        refreshText(this!!.result!!)
    }


    fun refreshText(result: Int){
        if (result == R.string.resultAlc)
            tvResult.text = getString(R.string.resultAlc)
        else if (result == R.string.resultGas)
            tvResult.text = getString(R.string.resultGas)
        else if (result == 0) {
            tvResult.text = ""
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
        }
    }


    private fun dicoverBest() {

        try {

            var gas = tfGas.text.toString().toDouble()
            var alc = tfEtanol.text.toString().toDouble()

            when ((gas * 0.7) > alc) {
                true -> result = R.string.resultAlc
                false -> result = R.string.resultGas
            }

        } catch (e: Exception) {
            result = 0
        }

    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("result", result!!)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        result = savedInstanceState.getInt("result")

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if ((savedInstanceState != null)
                && (savedInstanceState.getInt("result") != null)){
            refreshText(savedInstanceState.getInt("result"))
        }
    }

}
