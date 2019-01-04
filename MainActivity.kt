package com.tictactoe.calculatornew

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.media.MediaPlayer
import android.text.InputType
import  kotlinx.android.synthetic.main.activity_calculater.*

class MainActivity : AppCompatActivity() {

    private  enum class OPERATOR {
        PLUS,SUBTRACT,MULTIPLY,DIVIDE,EQUAL,UNDERROOT,LOG          //,UPON
    }
// music player constant variable
   // private var player:MediaPlayer=MediaPlayer.create(this, R.raw.leli )
lateinit var  player:MediaPlayer

    // instant variable
    private  var currentNumber: String? = null
    private  var currentOperator: OPERATOR? = null
    private  var stringNumberAtLeft: String? = null
    private  var stringNumberAtRight: String? = null
    private  var calculationsResult: Double = 0.00
    private var calculationString:String? = null        //  create a string to store no and display

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculater)

        player = MediaPlayer.create(this, R.raw.leli )
        player.isLooping = true
        player.start()


        edtNumber.isEnabled = false
        edtNumber.inputType = InputType.TYPE_NULL

        currentNumber = ""
        calculationString = ""               // assing it at empty string

    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    fun buttonTapped(view: View) {
        when (view.id) {

            R.id.btn0 -> numberIsTapped(0) // code to be executed
            R.id.btn1 -> numberIsTapped(1)
            R.id.btn2 -> numberIsTapped(2)
            R.id.btn3 -> numberIsTapped(3)
            R.id.btn4 -> numberIsTapped(4)
            R.id.btn5 -> numberIsTapped(5)
            R.id.btn6 -> numberIsTapped(6)
            R.id.btn7 -> numberIsTapped(7)
            R.id.btn8 -> numberIsTapped(8)
            R.id.btn9 -> numberIsTapped(9)

            R.id.btndiv -> {
                operaterIsTapped(OPERATOR.DIVIDE)
                calculationString="/"              // to display thr operation done in display box
            }
            R.id.btnadd -> {
                operaterIsTapped(OPERATOR.PLUS)
                calculationString="+"
            }
            R.id.btnsub -> {
                operaterIsTapped(OPERATOR.SUBTRACT)
                calculationString="-"
            }
            R.id.btnmul -> {
                operaterIsTapped(OPERATOR.MULTIPLY)
                calculationString="*"
            }
            R.id.btnequal -> {
                operaterIsTapped(OPERATOR.EQUAL)
                calculationString="="
            }
            R.id.btnper -> {
            implementPercent()
            calculationString="%"
        }
            R.id.btnroot -> {
                operaterIsTapped(OPERATOR.UNDERROOT)
                calculationString="√ "
            }
            R.id.btnlog -> {
                operaterIsTapped(OPERATOR.LOG)
                calculationString="lg"
            }
            R.id.btnupon -> {
                upon()
              //  operaterIsTapped(OPERATOR.UPON)
                calculationString="1/x"
            }
            R.id.btnclear -> {
                clear()
            }
        }
        txtCalculation.setText(calculationString)               // set text of string to txt calculation for display
    }

    private fun numberIsTapped(tappedNumber: Int) {
         currentNumber += tappedNumber.toString()
        edtNumber.setText(currentNumber)
    //    calculationString = currentNumber      // assign the current no to display screen no
        txtCalculation.setText(calculationString)     // give command to txtcalculation to display this string



    }
    private fun operaterIsTapped(tappedOperator: OPERATOR) {
        if (currentOperator != null){

            if (currentNumber != "") {

                stringNumberAtRight = currentNumber
                currentNumber=""

                when(currentOperator){
                    //Integer.(string)     it convert string to integer but like this "3" to 3 not the"a"
                    OPERATOR.PLUS -> calculationsResult = (stringNumberAtLeft!!.toDouble()) + (stringNumberAtRight!!.toDouble())
                    OPERATOR.SUBTRACT -> calculationsResult = (stringNumberAtLeft!!.toDouble()) - (stringNumberAtRight!!.toDouble())
                    OPERATOR.MULTIPLY -> calculationsResult = (stringNumberAtLeft!!.toDouble()) * (stringNumberAtRight!!.toDouble())
                    OPERATOR.DIVIDE -> calculationsResult = (stringNumberAtLeft!!.toDouble()) / (stringNumberAtRight!!.toDouble())
                    OPERATOR.UNDERROOT -> calculationsResult = Math.pow(stringNumberAtLeft!!.toDouble(),stringNumberAtRight!!.toDouble())
               //     OPERATOR.UPON -> calculationsResult=1.0/(stringNumberAtLeft!!.toDouble())
                    OPERATOR.LOG -> calculationsResult= (stringNumberAtLeft!!.toDouble()) + (stringNumberAtRight!!.toDouble())
                    // asseng the task for every operation

                }

                stringNumberAtLeft = calculationsResult.toString()              // this hold the answer as first input no.
                edtNumber.setText(calculationsResult.toString())               // to display the no. on onhold box
       //         calculationString = stringNumberAtLeft

            }
        } else {
            stringNumberAtLeft = currentNumber
            currentNumber = ""
        }
        currentOperator = tappedOperator
    }

    private  fun  implementPercent(){
        val percentValue = edtNumber.text.toString().toDouble()/100
        currentNumber = percentValue.toString()
        edtNumber.setText(currentNumber)

    }
    private  fun  upon(){
        val percentValue = 1/edtNumber.text.toString().toDouble()
        currentNumber = percentValue.toString()
        edtNumber.setText(currentNumber)

    }
    private fun clear(){

        // set all the value to the null aur empty to clear the data

        stringNumberAtLeft = ""
        stringNumberAtRight = ""
        calculationsResult = 0.0
        currentNumber = ""
        currentOperator = null
        edtNumber.setText("0")
        calculationString= "0"           // also clear the the display data for help edtnumber
    }
}



