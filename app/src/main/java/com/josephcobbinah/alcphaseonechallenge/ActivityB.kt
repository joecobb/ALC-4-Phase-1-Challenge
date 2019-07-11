package com.josephcobbinah.alcphaseonechallenge

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.webkit.*
import com.josephcobbinah.alcphaseonechallenge.utils.isOnline
import kotlinx.android.synthetic.main.activity_b.*


class ActivityB : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        supportActionBar?.title = "About ALC"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        checkInternetAndLoadPage()
    }


    private fun showNoInternet() {

        progressBar.visibility = View.GONE
        showErrorMessage("No internet connection")
    }


    private fun loadPage(){
        progressBar.visibility = View.VISIBLE


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        webView.webChromeClient = WebChromeClient()

        webView.settings.allowUniversalAccessFromFileURLs = true
        webView.settings.javaScriptEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.settings.setAppCacheEnabled(false)
        webView.settings.domStorageEnabled = true

        webView.webViewClient = object:WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                Log.d("WebView Error:WebResErr", error.toString())
                progressBar.visibility = View.GONE
                showErrorMessage("An Error Occurred")
                super.onReceivedError(view, request, error)
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                Log.d("WebView Error: HTTP", errorResponse.toString())
                progressBar.visibility = View.GONE
                showErrorMessage("An Error Occurred")
                super.onReceivedHttpError(view, request, errorResponse)
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                Log.d("WebView Error: SslError", error.toString())
                handler?.proceed()
            }
        }

        webView.loadUrl("https://www.andela.com/alc")
    }

    private fun checkInternetAndLoadPage(){
        if(isOnline()){
            loadPage()
        }else{
            showNoInternet()
        }
    }

    private fun showErrorMessage(message:String){
        Snackbar.make(constraintLayout,message,Snackbar.LENGTH_INDEFINITE).setAction("Retry") {
            checkInternetAndLoadPage()
        }.show()
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}
