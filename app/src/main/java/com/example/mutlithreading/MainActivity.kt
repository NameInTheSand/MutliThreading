package com.example.mutlithreading

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val thread1 = MyThread()
        val thread2 = MyThread()
        val thread3 = MyThread()
        thread1.start()
        thread2.start()
        thread3.start()
    }

    class MyRunnable:Runnable{

        override fun run() {
            Log.d("TESTTAG", "Hello from ${Thread.currentThread().name}")
        }

    }

    class MyThread : Thread() {

        override fun run() {
            super.run()
            for (i in 0..4){
                sleep(500)
                Log.d("TESTTAG", "I = $i")
            }
        }
    }
}