/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

data class Kitten(val index: Int) {

    val imageUrl = "$baseUrl${300 + index}/300"

    companion object {
        const val baseUrl = "http://placekitten.com/"
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kittens = (0..100).map { Kitten(it) }

        setContent {
            MyTheme {
                MyApp(kittens)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(kittens: List<Kitten>) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items = kittens, itemContent = { kitten ->

                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(16.dp)
                ) {
                    Row(Modifier.padding(16.dp)) {
                        CoilImage(
                            data = kitten.imageUrl,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp).align(Alignment.CenterVertically)
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = "Meow ${kitten.index}",
                            style = MaterialTheme.typography.subtitle2,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
                        )
                    }
                }
            })
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(emptyList())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(emptyList())
    }
}
