package br.com.joanadarc.espacosaber.feature.home

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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.joanadarc.espacosaber.ui.navigation.AlunoRoute
import br.com.joanadarc.espacosaber.ui.navigation.EditAlunoRoute
import br.com.joanadarc.espacosaber.ui.navigation.EditSessaoRoute
import br.com.joanadarc.espacosaber.ui.navigation.SessaoRoute
import java.time.format.DateTimeFormatter

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    onNavigate: (Any) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        onNavigate = onNavigate,
        modifier = modifier,
    )
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onNavigate: (Any) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        HomeUiState.Loading -> Unit
        is HomeUiState.Success -> HomeScreen(
            uiState = uiState,
            onNavigate = onNavigate,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    uiState: HomeUiState.Success,
    onNavigate: (Any) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Home") },
            )
        },
        modifier = modifier,
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Title(title = "Alunos", onClick = { onNavigate(AlunoRoute) })
            Items(items = uiState.alunos, onClick = { onNavigate(EditAlunoRoute(it.id)) }) { aluno ->
                Text(
                    text = aluno.nome ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp),
                )
            }

            Title(title = "Sessões", onClick = { onNavigate(SessaoRoute) })
            Items(items = uiState.sessoes, onClick = { onNavigate(EditSessaoRoute(it.id)) }) { sessao ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = sessao.aluno?.nome ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier,
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = sessao.data?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                                ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier,
                        )
                        Text(
                            text = sessao.status?.descricao ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(sessao.status?.cor ?: 0xFF000000),
                            modifier = Modifier,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Title(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun <T> Items(
    items: List<T>,
    onClick: (T) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(item: T) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            OutlinedCard(
                onClick = { onClick(item) },
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