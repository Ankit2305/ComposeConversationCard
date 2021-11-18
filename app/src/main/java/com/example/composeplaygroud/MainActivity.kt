package com.example.composeplaygroud

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplaygroud.ui.theme.ComposePlaygroudTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroudTheme {
                MessageCard(Message.message1)
            }
        }
    }
}

@Composable
fun ChatCard(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message = message)
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = message.character.displayPictureId),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by remember {
            mutableStateOf(false)
        }
        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
        )
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = message.character.name,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor
            ) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.body2,
                    maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                    modifier = Modifier.animateContentSize().padding(4.dp)
                )
            }
        }
    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DefaultPreview() {
    ComposePlaygroudTheme {
        MessageCard(Message.message1)
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChatCardPreview() {
    ComposePlaygroudTheme {
        ChatCard(messages = Message.messages)
    }
}

data class Message(
    val character: Character,
    val text: String
) {
    companion object {
        val message1 = Message(
            character = Character.pikachu,
            text = "Hi everyone"
        )
        val messages = listOf(
            Message(
                character = Character.pikachu,
                text = "Hi everyone"
            ),
            Message(
                character = Character.charmander,
                text = "Bhai soja"
            ),
            Message(
                character = Character.bulbasaur,
                text = "Hi"
            ),
            Message(
                character = Character.charmander,
                text = "Bhai soja"
            ),
            Message(
                character = Character.squirtle,
                text = "Tu soja nind aa rhi h to"
            ), Message(
                character = Character.charmander,
                text = "Ok"
            ), Message(
                character = Character.pikachu,
                text = "Hahaha Bhejti krdi charmander ki haha\nlolololololololololol\n\nhahahha"
            ), Message(
                character = Character.bulbasaur,
                text = "Hahaha"
            ), Message(
                character = Character.charmander,
                text = "Tu iski copy hi krio hmesha\n\nHahaha"
            ), Message(
                character = Character.bulbasaur,
                text = "Km s km bejati to nhi krata"
            ), Message(
                character = Character.pikachu,
                text = "Hahahha"
            ), Message(
                character = Character.squirtle,
                text = "hahaha"
            ), Message(
                character = Character.charmander,
                text = "hahaha"
            )
        )
    }
}

data class Character(
    val name: String,
    val displayPictureId: Int,
    val id: Int
) {
    companion object {
        val pikachu = Character(
            name = "Pikachu",
            displayPictureId = R.drawable.pikachu,
            id = 1
        )
        val bulbasaur = Character(
            name = "Bulbasaur",
            displayPictureId = R.drawable.bulbasaur,
            id = 2
        )
        val charmander = Character(
            name = "Charmander",
            displayPictureId = R.drawable.charmander,
            id = 3
        )
        val squirtle = Character(
            name = "Squirtle",
            displayPictureId = R.drawable.squirle,
            id = 4
        )
    }
}