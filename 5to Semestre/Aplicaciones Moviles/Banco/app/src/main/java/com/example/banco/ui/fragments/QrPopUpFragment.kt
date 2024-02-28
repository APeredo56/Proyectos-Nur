package com.example.banco.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.example.banco.databinding.FragmentQrPopUpBinding
import com.google.zxing.client.android.BuildConfig

import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class QrPopUpFragment(var qrBase64: String, val appContext: Context) : DialogFragment() {

    private lateinit var binding: FragmentQrPopUpBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        binding = FragmentQrPopUpBinding.inflate(inflater!!, null, false)
        builder.setView(binding.root)
        val pageData = "<img src=\"$qrBase64\" style=\"width: 100%; \" />"
        binding.qrContainer.loadData(pageData, null, null)

        setupOnClickListeners()
        return builder.create()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw()
        }
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    private fun setupOnClickListeners() {
        binding.shareButton.setOnClickListener {
            Log.e("Directorio", appContext.filesDir.toString())
            shareImage(getQRBitMap()!!)
//            val imageUri = FileProvider.getUriForFile(appContext,
//                "com.example.banco/files.provider",  //(use your app signature + ".provider" )
//                imagen
//            )
//            val intent = Intent().apply {
//                action = Intent.ACTION_SEND
//                putExtra(Intent.EXTRA_STREAM, imageUri)
//                type = "image/*"
//                putExtra(Intent.EXTRA_TITLE, "Compartir Cosas")
//            }
//            val shareIntent = Intent.createChooser(intent, null)
//            startActivity(shareIntent)
        }
    }

    private fun getQRBitMap(): Bitmap? {
        val webView = binding.qrContainer
        webView.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )

        //layout of webview
        webView.layout(0, 0, webView.measuredWidth, webView.getMeasuredHeight())

        webView.isDrawingCacheEnabled = true
        webView.buildDrawingCache()
        //create Bitmap if measured height and width >0
        val b = if (webView.measuredWidth > 0 && webView.measuredHeight > 0) Bitmap.createBitmap(
            webView.measuredWidth,
            webView.measuredHeight, Bitmap.Config.ARGB_8888
        )
        else null
        // Draw bitmap on canvas
        b?.let {
            Canvas(b).apply {
                drawBitmap(it, 0f, b.height.toFloat(), Paint())
                webView.draw(this)
            }
        }
        return b
    }

    private fun shareImage(bitmap: Bitmap) {
        // save bitmap to cache directory
        try {
            val cachePath = File(appContext.cacheDir, "images")
            cachePath.mkdirs() // don't forget to make the directory
            val stream =
                FileOutputStream("$cachePath/image.png") // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val imagePath = File(appContext.cacheDir, "images")
        val newFile = File(imagePath, "image.png")
        val contentUri =
            FileProvider.getUriForFile(appContext, "com.example.banco.provider", newFile)
        if (contentUri != null) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, appContext.contentResolver.getType(contentUri))
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
            shareIntent.type = "image/*"
            startActivity(Intent.createChooser(shareIntent, "Choose an app"))
        }
    }
}