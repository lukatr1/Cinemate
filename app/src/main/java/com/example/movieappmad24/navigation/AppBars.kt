import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopMovieAppBar(
    title: String,
    onBackPressed: () -> Unit,
    onActionClicked: () -> Unit = {},
    actionIcon: ImageVector? = null,
    btnIsVisible: Boolean = false
) {
    //val backgroundColor = Color.Black

    if (btnIsVisible) {
        TopAppBar(
            title = { Text(title) },
            //backgroundColor = backgroundColor,
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go Back",
                        tint = Color.White
                    )
                }
            },
            actions = {
                actionIcon?.let {
                    IconButton(onClick = onActionClicked) {
                        Icon(
                            imageVector = it,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
        )
    } else {
        TopAppBar(
            title = { Text(title) },
            //backgroundColor = backgroundColor,
            actions = {
                actionIcon?.let {
                    IconButton(onClick = onActionClicked) {
                        Icon(
                            imageVector = it,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
        )
    }
}


