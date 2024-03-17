package com.example.salesmanapp.feature_search.presentation.search.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.salesmanapp.R

@Composable
fun SearchField(
    value: String,
    maxQueryLength: Int,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    TextField(
        value = value,
        onValueChange = {
            if (it.length <= maxQueryLength)
                onValueChanged(it)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                style = MaterialTheme.typography.titleMedium
            )
        },
        leadingIcon = {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.ic_search),
                contentDescription = null
            )
        },
        trailingIcon = {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.ic_microphone),
                contentDescription = null,
                Modifier.clickable {
                    Toast.makeText(
                        context,
                        context.getText(R.string.voice_search_not_implemented),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFFFFFFF),
            focusedContainerColor = Color(0xFFFFFFFF),
            focusedBorderColor = Color(0xFFFFFFFF),
            unfocusedBorderColor = Color(0xFFE5E5E5),
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 2.dp,
            )
    )
}

@Preview
@Composable
fun SearchFieldPreview() {
    SearchField(
        "Search",
        5,
        {},
    )
}