package com.sky.algorithmndk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)
        Log.d(TAG,"onCreate $this")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart $this")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart $this")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume $this")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG,"onRestoreInstanceState $this")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,"onSaveInstanceState $this")
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        Log.d(TAG,"onRetainCustomNonConfigurationInstance $this")
        return super.onRetainCustomNonConfigurationInstance()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG,"onAttachedToWindow $this")
    }



    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause $this")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop $this")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG,"onDetachedFromWindow $this")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy $this")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d(TAG,"onWindowFocusChanged $this")
    }


    override fun onPostResume() {
        super.onPostResume()
        Log.d(TAG,"onPostResume $this")
    }

}