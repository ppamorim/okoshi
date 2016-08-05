/*
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

package com.okoshi

import com.squareup.moshi.Moshi
import okhttp3.Response

/**
 * Extension that implements a interface instead parameters as function.
 * This method, internally, will call for the original method and use the
 * instance of the interface to call the implemented methods.
 *
 * @param clazz Class used to parse the response.
 * @param okoshiCallback instance of the interface that is used to
 * return the result of the parse.
 */
fun <T> Response.parseItem(clazz: Class<T>, okoshiCallback: OkoshiCallback<T>) =
    parseItem(clazz, { okoshiCallback.onSuccess(it) }, { okoshiCallback.onError(it) })

/**
 * Extension that is a simple wrapper of the main {@ parseItem()}
 * method. Used to reduce the boilerplate of the code or simplify
 * the use of the method.
 *
 * @param moshi Instance of Moshi.
 * @param clazz Class used to parse the response.
 * @param okoshiCallback instance of the interface that is used to
 * return the result of the parse.
 */
fun <T> Response.parseItem(moshi: Moshi, clazz: Class<T>, okoshiCallback: OkoshiCallback<T>) =
    parseItem(moshi, clazz, { okoshiCallback.onSuccess(it) }, { okoshiCallback.onError(it) })

/**
 * Extension that is a simple wrapper of the main {@ parseItem()}
 * method. Used to reduce the boilerplate of the code or simplify
 * the use of the method.
 *
 * @param clazz Class used to parse the response.
 * @param success function that returns the parsed item.
 * @param error function that returns the error code of
 *  the request.
 */
inline fun <T> Response.parseItem(clazz: Class<T>,
    success: (t: T) -> Unit, error: (code: Int) -> Unit) =
    parseItem(Moshi.Builder().build(), clazz, success, error)

/**
 * Extension that will read the BufferedSource response
 * of the request and will parse for the type informed.
 *
 * @param moshi Instance of Moshi.
 * @param clazz Class used to parse the response.
 * @param success function that returns the parsed item.
 * @param error function that returns the error code of
 *  the request.
 */
inline fun <T> Response.parseItem(
    moshi: Moshi,
    clazz: Class<T>,
    success: (t: T) -> Unit,
    error: (code: Int) -> Unit) {
  if(isSuccessful) {
    success(moshi.adapter(clazz).fromJson(body().source()))
  } else {
    error(code())
  }
}