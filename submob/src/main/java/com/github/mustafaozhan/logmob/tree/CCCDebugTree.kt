/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.logmob.tree

import android.content.Context
import android.util.Log
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("TooGenericExceptionCaught")
class CCCDebugTree(context: Context) : Timber.DebugTree() {

    companion object {
        private const val MAX_FILE_SIZE = (10 * 1024 * 1024).toLong() // 10 MB
        private const val DATE_FORMAT = "HH:mm:ss MM.dd.yyyy"
    }

    private val currentFilePath: String
    private val lastFilePath: String

    init {
        val directoryPath = context.cacheDir.toString() + "/logs/"
        lastFilePath = directoryPath + context.packageName + "_last_log.txt"
        currentFilePath = directoryPath + context.packageName + "_current_log.txt"

        val directory = File(directoryPath)
        if (!directory.exists() && !directory.mkdirs()) {
            Timber.e("Failed to create LOG file directory")
        }

        Timber.plant(this)
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        var realMessage = message
        if (message.isNotEmpty()) {
            realMessage = "@" + Thread.currentThread().name + ": " + message
            appendLine(priorityToLevel(priority), tag, message, t)
        }

        super.log(priority, tag, realMessage, t)
    }

    private fun appendLine(level: String, tag: String?, line: String, throwable: Throwable?) {
        rolloverIfNeeded()

        var outputStream: PrintWriter? = null
        try {
            outputStream = PrintWriter(FileOutputStream(currentFilePath, true))
            outputStream.printf(
                "%s %s/%s: %s\n",
                SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(Date()),
                level,
                tag,
                line
            )
            if (throwable != null) {
                outputStream.printf("Caused by: %s\n", Log.getStackTraceString(throwable))
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to write to LOG file")
        } finally {
            outputStream?.close()
        }
    }

    private fun rolloverIfNeeded() {
        val lastFile = File(lastFilePath)
        val currentFile = File(currentFilePath)

        if (!currentFile.exists()) {
            return
        }

        if (currentFile.length() <= MAX_FILE_SIZE) {
            return
        }

        try {
            copyFile(currentFile, lastFile, true)
        } catch (e: Exception) {
            Timber.e(e, "Failed to copy log file")
        }
    }

    @Suppress("SameParameterValue")
    @Throws(Exception::class)
    private fun copyFile(src: File, dst: File, deleteSource: Boolean) {
        val inStream = FileInputStream(src)
        val outStream = FileOutputStream(dst)
        val inChannel = inStream.channel
        val outChannel = outStream.channel
        inChannel.transferTo(0, inChannel.size(), outChannel)
        inStream.close()
        outStream.close()

        Timber.d("Data copied from $src to $dst")

        if (deleteSource) {
            if (src.delete()) {
                Timber.d("Source file deleted")
            } else {
                Timber.e("Failed to delete source file!")
            }
        }
    }

    private fun priorityToLevel(priority: Int) = when (priority) {
        Log.VERBOSE -> "V"
        Log.DEBUG -> "D"
        Log.INFO -> "I"
        Log.WARN -> "W"
        Log.ERROR -> "E"
        Log.ASSERT -> "A"
        else -> "?"
    }
}
