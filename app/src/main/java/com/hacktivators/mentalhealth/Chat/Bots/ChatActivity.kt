package com.hacktivators.mentalhealth.Chat.Bots

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.java.ChatFutures
import com.google.ai.client.generativeai.java.GenerativeModelFutures
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content
import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.Futures
import com.hacktivators.mentalhealth.Adapter.MessageAdapter
import com.hacktivators.mentalhealth.BackEnd.Service
import com.hacktivators.mentalhealth.Constants
import com.hacktivators.mentalhealth.Model.Message
import com.hacktivators.mentalhealth.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ChatActivity : Activity() {
    var recyclerView: RecyclerView? = null
    var chat= null
    var welcomeTextView: TextView? = null
    var messageEditText: EditText? = null
    var sendButton: ImageView? = null
    var messageList: MutableList<Message>? = null
    var messageAdapter: MessageAdapter? = null
    var jsonBody = JSONObject()
    var messagesArray = JSONArray()
    var userMessage = JSONObject()
    var systemMessage = JSONObject()
    var service: Service? = null
    var constants: Constants? = null
    private val mainThreadScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        messageList = ArrayList()
        constants = Constants()
        recyclerView = findViewById(R.id.recycler_view)
        welcomeTextView = findViewById(R.id.welcome_text)
        messageEditText = findViewById(R.id.message_edit_text)
        sendButton = findViewById(R.id.send_btn)

        //setup recycler view
        messageAdapter = MessageAdapter(messageList)
        recyclerView?.setAdapter(messageAdapter)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        recyclerView?.setLayoutManager(layoutManager)




        sendButton?.setOnClickListener(View.OnClickListener { v: View? ->
            val question = messageEditText?.getText().toString().trim { it <= ' ' }
            addToChat(question, Message.SENT_BY_USER)
            messageEditText?.setText("")
            mainThreadScope.launch {
                runGemini(question)
            }            //callAPI(question);

            welcomeTextView?.setVisibility(View.GONE)
        })
    }

    suspend fun runGemini(question: String){
        val generativeModel = GenerativeModel(
            // The Gemini 1.5 models are versatile and work with most use cases
            modelName = "gemini-1.5-flash",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = "AIzaSyDFJKIK3lLXQkX5KVSrkYu-vS3_ejHpYv8",
                    systemInstruction = content { text("This model will be used as a chatbot called Halcyon, for a mental-healthcare app.\n" +
                            "\n" +
                            "Respond like a therapist without sounding like one.\n" +
                            "\n" +
                            "Whenever questions like \"What do you do?\", \"How can you assist me?\" or something similar is asked, respond with - \"Hi! I’m Halcyon, your mental health companion from this app. I’m here to provide 24/7 support and help you prioritize your well-being. Whether you’re feeling stressed, anxious, or down, I can offer resources and information to help you cope. Remember, I’m here to listen non-judgmentally, so don’t hesitate to reach out if you ever need someone to talk to.\"\n" +
                            "\n" +
                            "Guide the user to these features present inside the app whenever they seem helpful - meditation, breathing exercises and yoga guides. Sleep tracker, calming nature sounds and nature walk feature that allows the user to locate the nearest park, garden etc. Crisis helpline and contacts of professional therapists who can be contacted to book a session whenever it seems necessary.\n" +
                            "\n" +
                            "Make it clear to users that the chatbot is not a replacement for professional therapy. Direct users towards human therapists when needed.") }

            )
        val chat = generativeModel.startChat(
            history = listOf(
                content(role = "user") { text("How many hours of sleep do you usually get?") },
                content(role = "model") { text("7") },
                content(role = "user") { text("i do not know the reason. do you think I should take some anxiety pills?") },
                content(role = "model") { text("It's important to figure out what might be causing these feelings of fear before considering any medication. Self-treating can be risky, and it's best to talk to a medical professional about what you're experiencing.\n" +
                        "\n" +
                        "Would you like to explore some ways to manage these feelings of fear right now? We could try a breathing exercise or perhaps listen to some calming nature sounds that are available in our app together.") },
                content(role = "user") { text("I'm constantly worried about failing my exams.") },
                content(role = "model") { text("Exam anxiety is common. What kinds of thoughts do you typically have when you feel worried about failing?\"\n" +
                        "Let's see if we can break down your worries and find ways to feel more in control. Sometimes, just talking things through can help. And hey, have you checked out the meditation or breathing exercises on the app? They can be really helpful for calming those pre-exam jitters.") }
            )
        )
        val response = chat.sendMessage(question)
        println(response.text)
        addToChat(response.text, Message.SENT_BY_BOT)
    }

    fun addToChat(message: String?, sentBy: String?) {
        runOnUiThread {
            messageList!!.add(Message(message, sentBy))
            messageAdapter!!.notifyDataSetChanged()
            recyclerView!!.smoothScrollToPosition(messageAdapter!!.itemCount)
        }
    }
}