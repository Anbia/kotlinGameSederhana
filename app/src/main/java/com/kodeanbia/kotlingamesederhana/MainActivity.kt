package com.kodeanbia.kotlingamesederhana

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var score : Int = 0
    var imageArray = ArrayList<ImageView>()
    var handler : Handler = Handler()
    var runnable: Runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        score = 0
        imageArray = arrayListOf(imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView6,imageView7,imageView8,imageView9)
        hideImages()
       object : CountDownTimer(10000,1000){
           override fun onFinish() {
               time_countdown.text = "Time's Off"
               //untuk gambar berhenti bersamaan dengan waktu hitung mundur
               handler.removeCallbacks(runnable)
               for (image in imageArray){
                   image.visibility = View.INVISIBLE
               }
               Toast.makeText(applicationContext, "Time's Off",Toast.LENGTH_LONG).show()

           }
           //fungsi untuk waktu hitung mundur
           override fun onTick(p0: Long) {
               time_countdown.text = "Time : " + p0 / 1000
           }

       }.start()

    }

    //fungsi untuk gambar bergerak random
    fun hideImages(){
        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val index = random.nextInt(8-0)
                imageArray[index].visibility = View.VISIBLE
                handler.postDelayed(runnable, 500)
            }

        }
        handler.post (runnable) 

    }

    fun icreaseScore(view: View){
        score++
        scoreText.text = "Score : " + score
    }

}
