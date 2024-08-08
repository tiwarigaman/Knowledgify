package com.mobile.pdhayi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rajat.pdfviewer.PdfRendererView

class PDFViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfviewer)
// Retrieve the PDF URL from the intent
        val pdfUrl = intent.getStringExtra("PDF_URL")
        val pdfTitle = intent.getStringExtra("PDF_TITLE") ?: "PDF Document"
        val pdfView = findViewById<PdfRendererView>(R.id.pdfView)

        pdfView.initWithUrl(
            url = pdfUrl.toString(),
            lifecycleCoroutineScope = lifecycleScope,
            lifecycle = lifecycle
        )
    }
}