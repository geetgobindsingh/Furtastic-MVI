package com.geetgobindsingh.core_ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.geetgobindsingh.core_ui.ui.theme.Black
import com.geetgobindsingh.core_ui.ui.theme.Purple40
import com.geetgobindsingh.core_ui.ui.theme.Purple80
import com.geetgobindsingh.core_ui.ui.theme.PurpleGrey40
import com.geetgobindsingh.core_ui.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    onRemoveHeadFromQueue: () -> Unit,
    openDialogCustom: MutableState<Boolean> = mutableStateOf(false)
) {
//    AlertDialog(
//        modifier = modifier,
////        onDismissRequest = {
////            onRemoveHeadFromQueue()
////        }
////        text = {
////            if (description != null) {
////                Text(text = description)
////            }
////        }
//        //buttons = {}
//    )
}









