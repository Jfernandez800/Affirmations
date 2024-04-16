/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme
import com.example.affirmations.data.Datasource

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationsApp() {
    AffirmationList(
        affirmationList = Datasource().loadAffirmations(),
    )
}

//Create a new method called AffirmationCard(), and annotate it with the @Composable annotation.
@Composable
//Edit the method signature to take an Affirmation object as a parameter. The Affirmation object comes from the model package.
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    //call the Card composable and Pass in the modifier parameter.
    Card(modifier = modifier) {
        Column{
            Image(
                //The painterResource method will load either vector drawables or rasterized asset formats like PNGs.
                painter = painterResource(affirmation.imageResourceId),
                //Also, pass a stringResource for the contentDescription parameter.
                contentDescription = stringResource(affirmation.stringResourceId),
                // pass a modifier and a contentScale. A contentScale determines how the image should be scaled and displayed.
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
}

//Create a function called AffirmationList(), annotate it with the @Composable annotation, and declare
// a List of Affirmation objects as a parameter in the method signature.
@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    //Declare a LazyColumn composable inside of the AffirmationList() function. Pass the modifier object as an argument to the LazyColumn.
    LazyColumn(modifier = modifier) {
        //The items() method is how you add items to the LazyColumn.
        items(affirmationList) {affirmation ->
            //call the AffirmationCard() composable. Pass it the affirmation and a Modifier object with the padding attribute set to 8.dp.
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

/*
In Compose, a Card is a surface that displays content and actions in a single container.

Items within a Column composable arrange themselves vertically in the UI. This allows you to place
an image above the associated text. Conversely, a Row composable arranges its contained items horizontally.

Recall that an Image composable always requires a resource to display, and a contentDescription

The difference between a LazyColumn and a Column is that a Column should be used when you have a small
number of items to display, as Compose loads them all at once. A Column can only hold a predefined, or
fixed, number of composables. A LazyColumn can add content on demand, which makes it good for long lists
and particularly when the length of the list is unknown. A LazyColumn also provides scrolling by default,
without additional code.



 */