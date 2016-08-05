![Logo Okoshi][1]

[![](https://jitpack.io/v/ppamorim/okoshi.svg)](https://jitpack.io/#ppamorim/okoshi)
[![](https://www.bitrise.io/app/90c91f86f7a45c19.svg?token=PtB6gVW9yTi3RTm2Wp227Q)](https://www.bitrise.io/app/90c91f86f7a45c19)
[![](https://img.shields.io/badge/Size-5 KB-e91e63.svg)](http://www.methodscount.com/?lib=com.github.ppamorim%3Aokoshi%3A0.1)

Tired to write every simple parser for your OkHttp response? I think you would love to use
this library, it is just a extension, written in Kotlin, that will validate, parse and return
the data for you as function (or interface if you need).

Usage
-----

It's very simple, you just need to call the method `.parseItem(Class, Unit, Unit)` to parse
the data.

```kotlin
OkHttpClient().newCall(request)
            .execute()
            .parseItem(Model::class.java,
                success = { it }, //Return item/list of items parsed.
                error = { it }) //Return int status code.
```

Import library
--------------

```groovy
repositories {
  maven {
    url "https://jitpack.io"
  }
}

dependencies {
  compile 'com.github.ppamorim:okoshi:0.1'
}
```

That's all.

License
-------

    Copyright 2016 Pedro Paulo de Amorim

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]: ./art/logo.png