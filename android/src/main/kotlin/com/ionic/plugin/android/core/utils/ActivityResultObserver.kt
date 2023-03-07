package com.ionic.plugin.android.core.utils

import android.content.Intent

interface IActivityResult {
  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean
}

interface WithActivityResult : IActivityResult

interface IActivityResultObserver {
  fun add(receiver: WithActivityResult)
}

class ActivityResultObserver : IActivityResultObserver, IActivityResult {
  private val receivers = mutableListOf<WithActivityResult>()

  override fun add(receiver: WithActivityResult) {
    receivers.add(receiver)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
    for (it in receivers) {
      if (it.onActivityResult(requestCode, resultCode, data)) {
        return true
      }
    }
    return false
  }
}
