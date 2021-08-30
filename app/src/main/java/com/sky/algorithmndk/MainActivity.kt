package com.sky.algorithmndk

import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var time = System.currentTimeMillis()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        time = System.currentTimeMillis()
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
//        sample_text.text = stringFromJNI()
        startActivity(Intent(this, BreadthFirstSearch::class.java))
//        startActivity(Intent(this, SlideMenuViewActivity::class.java))
//        var cur = System.currentTimeMillis()
//        Log.d("MyApplication","onCreate "+ (cur - time) +"  cur="+cur)
    }

    override fun onResume() {
        super.onResume()
//        var cur = System.currentTimeMillis()
//        Log.d("MyApplication","onResume "+ (cur - time) + "  cur="+cur)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        var cur = System.currentTimeMillis()
        Log.d("MyApplication","onWindowFocusChanged "+ (cur - time)+"  cur="+cur)
//        Debug.stopMethodTracing()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }




    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    external fun stringFromJNI(): String
//
//    companion object {
//        // Used to load the 'native-lib' library on application startup.
//        init {
//            System.loadLibrary("native-lib")
//        }
//    }
}
