package ru.alox1d.handler_and_looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidinterview.SimpleHandler
import ru.alox1d.handler_and_looper.databinding.ActivitySimpleBinding

class SimpleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySimpleBinding
    private lateinit var tv: TextView

    // private lateinit var brokenHandler: BrokenHandler
    private lateinit var simpleHandler: SimpleHandler
    private val handler = Handler(Looper.getMainLooper()) {
        // 3. Handle message
        tv.text = it.obj as String
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUI()

        simpleHandlerExample()
        myLooperHandlerExample()
//        deprecatedHandlerExample()

        with(tv) {
            text = "s"
        }

    }

    private fun simpleHandlerExample() {
        simpleHandler = SimpleHandler()
        simpleHandler.post {
            // 1. Posting a task (runnable) to a thread
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            // 2. Communicating with a main thread
            val msg = Message.obtain()
            msg.obj = "Task 1 Completed"
            handler.sendMessage(msg)
        }.post {
            task2()
        }.post {
            task3()
        }
    }

    private fun myLooperHandlerExample() {
        // Get looper object associated with this thread
        val looper = Looper.myLooper()
        looper?.let {

            // don't need to call this, because main looper in work already
            // Looper.prepare()
            // Looper.loop()

            val myLooperHandler = Handler(it) {
                tv.text = it.obj.toString()
                true
            }
            myLooperHandler.post {
                val msg = Message.obtain()
                msg.obj = "Task of Handler(Looper.myLooper()) Completed"
                myLooperHandler.sendMessage(msg)
            }
        }
    }

    private fun deprecatedHandlerExample() {
        Thread {
            Looper.prepare()
            Thread.sleep(1000)
            // It will CRASH without main thread by Handler(Looper.getMainLooper())
            // val deprecatedHandler = Handler {
            val deprecatedHandler = Handler() {
                Log.i(
                    "TAG", "Handler with non-main thread" +
                            "trying to interact with view "
                )
                tv.text = it.obj.toString()
                true
            }
            deprecatedHandler.post {
                val m = Message.obtain()
                m.obj = "Task from Deprecated Handler "
                deprecatedHandler.sendMessage(m)
            }
            Looper.loop()

        }.start()
    }

    private fun setUI() {
        binding = ActivitySimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tv = binding.textview
    }

    private fun task2() {
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val msg = Message.obtain()
        msg.obj = "Task 2 Completed"
        handler.sendMessage(msg)
    }

    private fun task3() {
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val msg = Message.obtain()
        msg.obj = "Task 3 Completed"
        handler.sendMessage(msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleHandler.quit()
    }
}