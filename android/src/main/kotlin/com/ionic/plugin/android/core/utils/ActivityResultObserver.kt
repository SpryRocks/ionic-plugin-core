package com.ionic.plugin.android.core.utils

import android.content.Intent

interface IActivityResult {
  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean
}

interface IActivityResultObserver {
  fun add(receiver: IActivityResult)
  fun remove(receiver: IActivityResult)
}

class ActivityResultObserver : IActivityResultObserver, IActivityResult {
  private val receivers = mutableListOf<IActivityResult>()

  override fun add(receiver: IActivityResult) {
    receivers.add(receiver)
  }

  override fun remove(receiver: IActivityResult) {
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
