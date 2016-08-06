package com.okoshi.sample/*
* Copyright (C) 2016 Pedro Paulo de Amorim
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.okoshi.parseItem
import com.okoshi.sample.R
import com.okoshi.sample.R.layout
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Request.Builder

const val URL = "https://gist.github.com/ppamorim/d1abc53989f131efacc35046f59e3e5a"

class SampleActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_sample)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    DoUglyAsyncTask().execute()
  }

  /**
   * It's a just example, don't do this please!
   */
  class DoUglyAsyncTask(): AsyncTask<Any, Any, Any>() {
    override fun doInBackground(vararg params: Any?): Any {
      try {
        val request = Builder().url(URL).build()
        OkHttpClient().newCall(request)
            .execute()
            .parseItem(SampleModel::class.java,
                success = { return it },
                error = { return it })
      } catch (e: Exception) {
        e.printStackTrace()
      }
      return Any()
    }
  }

}