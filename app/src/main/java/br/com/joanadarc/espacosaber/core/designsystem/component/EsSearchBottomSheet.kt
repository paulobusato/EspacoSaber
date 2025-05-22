package br.com.joanadarc.espacosaber.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EsSearchBottomSheet(
    entidade: String,
    items: List<String>,
    openSheet: Boolean,
    onOpenSheet: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    var openInnerSheet by rememberSaveable { mutableStateOf(false) }

    if (openSheet) {
        ModalBottomSheet(
            onDismissRequest = { onOpenSheet(false) },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false),
            modifier = modifier,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = entidade,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
                EsSearchInput(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
                OutlinedCard(
                    onClick = {
                        onOpenSheet(false)
                        openInnerSheet = true
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Adicionar $entidade",
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
                Items(items = items) { item ->
                    Text(
                        text = item,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }
    }

    if (openInnerSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                openInnerSheet = false
                onOpenSheet(true)
            },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                EsTextField(
                    value = "",
                    onValueChange = {},
                    label = entidade,
                )
                Spacer(Modifier.height(16.dp))
                Button(onClick = {
                    openInnerSheet = false
                    onOpenSheet(true)
                }) {
                    Text(text = "Salvar")
                }
            }
        }
    }
}

@Composable
private fun <T> Items(
    items: List<T>,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(item: T) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            OutlinedCard(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp,
                    ),
                content = { content(item) },
            )
        }
    }
}