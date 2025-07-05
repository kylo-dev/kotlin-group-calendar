package com.gc.common.logging

import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import java.io.ByteArrayInputStream

class CachingBodyServletInputStream(
  private val cachedBody: ByteArray
) : ServletInputStream() {

  private val inputStream = ByteArrayInputStream(cachedBody)

  override fun read(): Int {
    return inputStream.read()
  }

  override fun isFinished(): Boolean {
    return inputStream.available() == 0
  }

  override fun isReady(): Boolean {
    return true
  }

  override fun setReadListener(p0: ReadListener?) {

  }
}