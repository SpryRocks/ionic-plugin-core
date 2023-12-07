package com.ionic.plugin.android.core.utils

import android.content.Intent

interface WithActivityResult {
  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean
}

interface IActivityResultObserver {
  fun add(receiver: WithActivityResult)
  fun remove(receiver: WithActivityResult)
}

class ActivityResultObserver : IActivityResultObserver, WithActivityResult {
  private val receivers = mutableListOf<WithActivityResult>()

  override fun add(receiver: WithActivityResult) {
    receivers.add(receiver)
  }

  override fun remove(receiver: WithActivityResult) {
    receivers.remove(receiver)
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
