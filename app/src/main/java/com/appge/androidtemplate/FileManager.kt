package com.appge.androidtemplate

import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object FileManager {

    @Throws(IOException::class)
    fun getTextFromFile(file: File): String {
        val inputStream = file.inputStream()
        val text = getTextFromInputStream(inputStream)
        inputStream.close()

        return text
    }

    @Throws(IOException::class)
    fun getTextFromInputStream(inputStream: InputStream): String {
        val br = inputStream.bufferedReader()
        val text = br.useLines { lines ->
            lines.fold("") { sum, line ->
                sum + if (sum.isNotBlank()) ", $line" else line
            }
        }

        br.close()
        return text
    }

    @Throws(IOException::class)
    fun writeTextToFile(file: File, text: String) {
        val outputStream = file.outputStream()
        writeTextToOutputStream(outputStream, text)
        outputStream.close()
    }

    @Throws(IOException::class)
    fun writeTextToOutputStream(outputStream: OutputStream, text: String) {
        val bw = outputStream.bufferedWriter()
        bw.write(text)
        bw.close()
    }

}