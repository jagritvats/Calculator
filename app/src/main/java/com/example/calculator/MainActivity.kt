package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var inputbox : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        inputbox = findViewById(R.id.inputbox)
    }

    var dot:Boolean=false
    var newop:Boolean=true

    fun getNumbers(view: View){

        if(newop){
            inputbox.text=""
        }

        newop=false

        var getText:String = inputbox.text.toString()

        val selectedBtn = view as Button

        when(selectedBtn.id){
            R.id.bt0->{
                getText+="0"
            }
            R.id.bt1->{
                getText+="1"
            }
            R.id.bt2->{
                getText+="2"
            }
            R.id.bt3->{
                getText+="3"
            }
            R.id.bt4->{
                getText+="4"
            }
            R.id.bt5->{
                getText+="5"
            }
            R.id.bt6->{
                getText+="6"
            }
            R.id.bt7->{
                getText+="7"
            }
            R.id.bt8->{
                getText+="8"
            }
            R.id.bt9->{
                getText+="9"
            }
            R.id.btdot->{
                if(!dot || !getText.contains('.')){
                    getText+="."
                    dot=true
                }
            }
        }

        inputbox.text = getText
    }

    var operator: String = ""
    var oldNum: String = ""
    var newNum: String = ""

    fun getOperator(view: View){

        if(oldNum!=""){
            newNum = inputbox.text.toString()
            if(newNum==""){
                return;
            }
        }else{
            oldNum = inputbox.text.toString()
            if(oldNum==""){
                return;
            }
        }

        if (oldNum!="" && newNum!=""){
            // if oldNum and newNum are both some values i.e. they haven't been operated
            equaloperator(View(this)); // A workaround to pass a dummy parameter
            oldNum = inputbox.text.toString()
        }
        val selectedOp = view as Button;
        when(selectedOp.id){
            R.id.btadd->{
                operator="+"
            }
            R.id.btsub->{
                operator="-"
            }
            R.id.btdivide->{
                operator="/"
            }
            R.id.btmultiply->{
                operator="x"
            }
            R.id.btpercent->{
                operator="%"
            }
        }

        dot = false
        newop=true
    }

    fun equaloperator(v: View){
        if(oldNum==""){
            return;
        }
        newNum = inputbox.text.toString();
        if(newNum==""){
            return;
        }

        var result:Double = 0.0;

        when(operator){
            "x"->{
                result = oldNum.toDouble() * newNum.toDouble();
            }
            "+"->{
                result = oldNum.toDouble() + newNum.toDouble();
            }
            "-"->{
                result = oldNum.toDouble() - newNum.toDouble();
            }
            "/"->{
                result = oldNum.toDouble() / newNum.toDouble();
            }
            "%"->{
                result = oldNum.toDouble()/100 * newNum.toDouble();
            }
        }
        if(result-result.toInt() == 0.0){
            inputbox.text = result.toInt().toString()
        }else{
            inputbox.text = result.toString()
        }

        // for consecutive operations
        oldNum = ""
        newNum = ""
        operator=""
    }

    fun signchange(view: View){
        var txt:String = inputbox.text.toString();
        if(txt=="" || txt.toDouble() ==0.0){
            return;
        }

        if(txt[0] !='-'){
            txt = "-$txt";

        }else{
            txt = txt.substring(1,)
        }

        inputbox.text = txt;
    }

    fun cleanInput(view:View){
        inputbox.text=""
        dot = false
        newop = true
        oldNum=""
        newNum=""
        operator=""
    }
}