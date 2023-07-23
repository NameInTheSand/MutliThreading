package com.example.mutlithreading

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "TESTTAG"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val store = Store()
        Thread(Producer(store)).start()
        Thread(Buyer(store)).start()
    }

    class Store : Object() {

        private var productCount: Int = 0

        @Synchronized
        fun buy() {
            while (productCount == 0) {
                try {
                    wait()
                } catch (_: Exception) {

                }
            }
            productCount--
            Log.d(TAG, "Buyer bought 1 good")
            Log.d(TAG, "Products count = $productCount")
            notify()
        }

        @Synchronized
        fun put() {
            while (productCount >= 3) {
                try {
                    wait()
                } catch (_: Exception) {

                }
            }

            productCount++
            Log.d(TAG, "Producer delivered 1 good")
            Log.d(TAG, "Products count = $productCount")
            notify()
        }

    }

    class Buyer(private val store: Store) : Object(), Runnable {

        override fun run() {
            for (i in 0..5) {
                store.buy()
            }
        }

    }

    class Producer(private val store: Store) : Object(), Runnable {

        override fun run() {
            for (i in 0..5) {
                store.put()
            }
        }

    }


}