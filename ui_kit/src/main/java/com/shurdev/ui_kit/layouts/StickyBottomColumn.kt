import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StickyBottomColumn(
    modifier: Modifier = Modifier,
    stickyBottom: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(modifier) {
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            content()
        }

        stickyBottom()
    }
}