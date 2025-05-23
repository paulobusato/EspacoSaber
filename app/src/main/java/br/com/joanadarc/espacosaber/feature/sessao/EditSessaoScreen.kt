package br.com.joanadarc.espacosaber.feature.sessao

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.joanadarc.espacosaber.core.designsystem.component.EsExposedDropdownMenu
import br.com.joanadarc.espacosaber.core.designsystem.component.EsSearchBottomSheet
import br.com.joanadarc.espacosaber.core.designsystem.component.EsTextField
import br.com.joanadarc.espacosaber.core.model.Procedimento
import br.com.joanadarc.espacosaber.core.model.Status
import java.time.format.DateTimeFormatter

@Composable
internal fun EditSessaoRoute(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: EditSessaoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    EditSessaoScreen(
        uiState = uiState,
        onBack = onBack,
        onAlterarEntidade = viewModel::alterarEntidade,
        modifier = modifier,
    )
}

@Composable
internal fun EditSessaoScreen(
    uiState: EditSessaoUiState,
    onBack: () -> Unit,
    onAlterarEntidade: (String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        EditSessaoUiState.Loading -> Unit
        is EditSessaoUiState.Success -> EditSessaoScreen(
            uiState = uiState,
            onBack = onBack,
            onAlterarEntidade = onAlterarEntidade,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditSessaoScreen(
    uiState: EditSessaoUiState.Success,
    onBack: () -> Unit,
    onAlterarEntidade: (String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Sessão") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                )
            }
        },
        modifier = modifier,
    ) { padding ->
        var openSheet by rememberSaveable { mutableStateOf(false) }
        var entidade by rememberSaveable { mutableStateOf("Atividade") }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(16.dp),
        ) {
            val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

            EsTextField(
                value = uiState.sessao.queixa ?: "",
                onValueChange = {},
                label = "Queixa",
                minLines = 5,
            )
            EsTextField(
                value = uiState.sessao.encaminhamento ?: "",
                onValueChange = {},
                label = "Encaminhamento",
            )
            EsTextField(
                value = uiState.sessao.atividade?.nome ?: "",
                onValueChange = {},
                label = "Atividade",
                readOnly = true,
                leadingIcon = {
                    IconButton(onClick = { openSheet = true }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                        )
                    }
                }
            )
            EsTextField(
                value = uiState.sessao.observacao ?: "",
                onValueChange = {},
                label = "Observação",
                minLines = 5,
            )
            EsTextField(
                value = if (uiState.sessao.data != null) dateTimeFormatter.format(uiState.sessao.data) else "",
                onValueChange = {},
                label = "Data/Hora",
            )
            EsExposedDropdownMenu(
                value = uiState.sessao.status.toString(),
                onValueChange = {},
                label = "Status",
                options = Status.entries.map { it.descricao },
            )
            EsExposedDropdownMenu(
                value = uiState.sessao.procedimento.toString(),
                onValueChange = {},
                label = "Procedimento",
                options = Procedimento.entries.map { it.descricao },
            )
        }

        EsSearchBottomSheet(
            entidade = entidade,
            items = uiState.atividades.map { Pair(it.id ?: "", it.nome) },
            openSheet = openSheet,
            onOpenSheet = { openSheet = it },
            onEdit = onAlterarEntidade,
        )
    }
}