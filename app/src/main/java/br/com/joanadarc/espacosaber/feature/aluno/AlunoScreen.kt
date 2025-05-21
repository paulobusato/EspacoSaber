package br.com.joanadarc.espacosaber.feature.aluno

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.joanadarc.espacosaber.core.designsystem.component.EsSearchInput

@Composable
internal fun AlunoRoute(
    modifier: Modifier = Modifier,
    viewModel: AlunoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AlunoScreen(
        uiState = uiState,
        modifier = modifier,
    )
}

@Composable
internal fun AlunoScreen(
    uiState: AlunoUiState,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        AlunoUiState.Loading -> Unit
        is AlunoUiState.Success -> AlunoScreen(
            uiState = uiState,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlunoScreen(
    uiState: AlunoUiState.Success,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Aluno") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Aluno") },
                icon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
                onClick = {},
            )
        },
        modifier = modifier,
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            EsSearchInput(
                value = "",
                onValueChange = {},
                modifier = Modifier.padding(16.dp)
            )
            Items(items = uiState.alunos) { aluno ->
                Text(
                    text = aluno.nome ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp),
                )
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