package com.malik.noteapp.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material.icons.rounded.Note
import androidx.compose.material.icons.rounded.NoteAlt
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malik.noteapp.R
import com.malik.noteapp.components.NoteInputText
import com.malik.noteapp.components.SaveButton
import com.malik.noteapp.data.NoteDataSource
import com.malik.noteapp.model.Note
import com.malik.noteapp.ui.theme.NoteAppTheme
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote:(Note) -> Unit,
    onRemoveNote:(Note) -> Unit
) {

    var titleNote by remember {
        mutableStateOf("")
    }
    var descNote by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    Column(modifier = Modifier.padding(8.dp)) {

        //AppBar
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Image(imageVector = Icons.Rounded.NoteAlt, contentDescription = "AppIcon")

            }, backgroundColor = Color(color = 0xFFDADFE3)
        )

        //Content

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            NoteInputText(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 8.dp
                ),
                text = titleNote,
                label = "Enter note title Here",
                onTextChange = { it ->
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) {
                        titleNote = it
                    }

                })
            NoteInputText(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 8.dp
                ),
                text = descNote,
                label = "Enter note details here",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) descNote = it

                })

            SaveButton(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 8.dp
                ),
                text = "Save",
                onClick = {
                    if (titleNote.isNotEmpty() && descNote.isNotEmpty()) {
                        onAddNote(Note(title = titleNote, description = descNote))
                        titleNote = ""
                        descNote = ""
                        Toast.makeText(context,"Note Added Successfully",Toast.LENGTH_SHORT).show()
                    }
                })
        }
        Divider(modifier = Modifier.padding(8.dp))

        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(note)
                })
            }
        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxSize(),
        color = Color(0xC6149CFF)
    ) {
        Column(modifier
            .clickable {
                onNoteClicked(note)
            }
            .padding(
                horizontal = 14.dp,
                vertical = 6.dp
            ), horizontalAlignment = Alignment.Start) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.caption
            )
        }

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteAppTheme {
        NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
    }
    Surface() {

    }
}