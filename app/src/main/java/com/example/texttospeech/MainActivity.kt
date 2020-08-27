package com.example.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var textToSpeech: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn : Button = findViewById(R.id.btn)
        var edittext : EditText = findViewById(R.id.edittext)
        textToSpeech = TextToSpeech(this,this)
        btn.setOnClickListener {
            var ch : String = edittext.text.toString()
            textToSpeech.speak(ch,TextToSpeech.QUEUE_FLUSH,null)
        }
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val res : Int = textToSpeech.setLanguage(Locale.US)
            if(res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this,"Language Not Supported",Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(this,"Failed to initialize",Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(textToSpeech != null){
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
    }

}