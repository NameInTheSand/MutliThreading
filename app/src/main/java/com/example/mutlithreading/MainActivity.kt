package com.example.mutlithreading

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val res = Resource()
        for (i in 0..5) {
            MyThread(res).also {
                it.name = "Thread $i"
                it.start()
            }
        }
    }

    class MyThread(
        private var res:Resource
    ) : Thread() {

        override fun run() {
            super.run()
            res.increment()
        }
    }
    class Resource(var x: Int = 0){
        @Synchronized fun increment(){
           x = 1
            for (i in 0..4) {
                Log.d("TESTTAG", "Name = {${Thread.currentThread().name}}, res = $x")
                x++
                try {
                    Thread.sleep(1000)
                } catch (_: Exception) {

                }
            }
        }

    }

}