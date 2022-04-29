package com.example.qrcode

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {
    lateinit var idIVQrcode: ImageView
    lateinit var idEdt: EditText
    lateinit var idBtnGenerateQR: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idIVQrcode = findViewById(R.id.idIVQrcode)
        idEdt = findViewById(R.id.idEdt)
        idBtnGenerateQR = findViewById(R.id.idBtnGenerateQR)

        idBtnGenerateQR.setOnClickListener {
            val data = idEdt.text.toString().trim()
            if (data.isEmpty()){
                idEdt.error = "Text is required"
                idEdt.requestFocus()
                return@setOnClickListener
            }else{
                val writer = QRCodeWriter()
                try{
                    val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE,512,512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)
                    for (x in 0 until width){
                        for (y in 0 until height){
                            bmp.setPixel(x,y,if (bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                        }
                    }
                    idIVQrcode.setImageBitmap(bmp)
                }catch (e:Exception){

                }
            }
        }
    }
}