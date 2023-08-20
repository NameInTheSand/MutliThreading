package com.example.mutlithreading

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mutlithreading.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val task = MyAsyncTask()
        binding.tvTest.setOnClickListener {
            task.execute()
        }
        binding.btnCancel.setOnClickListener {
            task.cancel(false)
        }
    }

    inner class MyAsyncTask : AsyncTask<Void, Void, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("TESTTAG", "Started")
            binding.tvTest.text = "Started"
        }

        override fun doInBackground(vararg params: Void?): Void? {
            for (i in 0..5){
                if (isCancelled) return null
                TimeUnit.SECONDS.sleep(1)
                Log.d("TESTTAG", "isCanceled = $isCancelled")
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            Log.d("TESTTAG", "onPostExecute")
            binding.tvTest.text = "Ended"
        }

    }

}