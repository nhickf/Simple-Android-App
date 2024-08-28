package com.grpcx.androidtask.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import coil.ImageLoader
import coil.decode.DecodeResult
import coil.decode.Decoder
import coil.fetch.SourceResult
import coil.request.Options
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class PdfPageDecoder(
    private val context: Context,
    private val data: PdfPage
) : Decoder {

    companion object {
        const val NUMBER_PDF_PAGES = "numberPages"
        const val SPACE_BETWEEN_PAGES = "spaceBetweenPages"
    }


    override suspend fun decode(): DecodeResult {
        val bitmap = withContext(Dispatchers.IO) {
            renderPagesToBitmap(
                data.file,
                context.resources.displayMetrics.density,
                data.numberPages,
                data.spaceBetweenPages
            )
        }
        val drawable = BitmapDrawable(context.resources, bitmap)
        return DecodeResult(drawable = drawable, isSampled = false)
    }

    class Factory : Decoder.Factory {
        override fun create(
            result: SourceResult,
            options: Options,
            imageLoader: ImageLoader
        ): Decoder? {
            return if (isApplicable(result)) {
                val numberPages = (options.parameters.values()[NUMBER_PDF_PAGES] as? Int) ?: Int.MAX_VALUE
                val spaceBetweenPages =
                    (options.parameters.values()[SPACE_BETWEEN_PAGES] as? Int) ?: 0
                PdfPageDecoder(
                    context = options.context,
                    data = PdfPage(
                        file = result.source.file().toFile(),
                        numberPages = numberPages,
                        spaceBetweenPages = spaceBetweenPages
                    )
                )
            } else {
                null
            }
        }

        private fun isApplicable(result: SourceResult): Boolean {
            return result.mimeType?.contains("pdf") == true
        }
    }

    private fun renderPagesToBitmap(
        file: File,
        density: Float,
        pagesNumber: Int,
        spaceBetweenPages: Int
    ): Bitmap {
        val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        val pdfRenderer = PdfRenderer(fileDescriptor)

        val spaceBetweenPagesPx = (spaceBetweenPages * density).toInt()
        val pagesSize = pagesNumber.coerceIn(1, pdfRenderer.pageCount)
        val (maxWidth, totalHeight) = calculateTotalHeightAndWidth(
            pdfRenderer,
            density,
            pagesSize,
            spaceBetweenPagesPx
        )

        // Create a bitmap with the total height and max width
        val combinedBitmap = Bitmap.createBitmap(maxWidth, totalHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(combinedBitmap)

        var currentY = 0
        for (i in 0 until pdfRenderer.pageCount) {
            pdfRenderer.openPage(i).use { page ->
                val pageBitmap = createPageBitmap(page, density)
                page.render(pageBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

                // Draw the page bitmap on the combined bitmap
                canvas.drawBitmap(pageBitmap, 0f, currentY.toFloat(), null)
                currentY += pageBitmap.height + spaceBetweenPagesPx

                pageBitmap.recycle() // Recycle the page bitmap to free memory
            }
        }

        pdfRenderer.close()
        fileDescriptor.close()

        return combinedBitmap
    }

    private fun calculateTotalHeightAndWidth(
        pdfRenderer: PdfRenderer,
        density: Float,
        pagesSize: Int,
        spaceBetweenPagesPx: Int
    ): Pair<Int, Int> {
        var totalHeight = 0
        var maxWidth = 0
        for (i in 0 until pagesSize) {
            pdfRenderer.openPage(i).use { page ->
                totalHeight += (page.height * density).toInt() + spaceBetweenPagesPx
                maxWidth = maxOf(maxWidth, (page.width * density).toInt())
            }
        }
        totalHeight -= spaceBetweenPagesPx
        return Pair(maxWidth, totalHeight)
    }

    private fun createPageBitmap(page: PdfRenderer.Page, density: Float): Bitmap {
        val pageWidth = (page.width * density).toInt()
        val pageHeight = (page.height * density).toInt()
        return Bitmap.createBitmap(pageWidth, pageHeight, Bitmap.Config.ARGB_8888)

    }

    data class PdfPage(val file: File, val numberPages: Int, val spaceBetweenPages: Int)
}