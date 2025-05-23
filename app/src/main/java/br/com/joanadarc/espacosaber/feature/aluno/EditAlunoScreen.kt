package br.com.joanadarc.espacosaber.feature.aluno

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.joanadarc.espacosaber.core.designsystem.component.EsSearchBottomSheet
import br.com.joanadarc.espacosaber.core.designsystem.component.EsTextField

@Composable
internal fun EditAlunoRoute(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: EditAlunoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    EditAlunoScreen(
        uiState = uiState,
        onBack = onBack,
        onAlterarEntidade =  viewModel::alterarEntidade,
        modifier = modifier,
    )
}

@Composable
internal fun EditAlunoScreen(
    uiState: EditAlunoUiState,
    onBack: () -> Unit,
    onAlterarEntidade: (String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        EditAlunoUiState.Loading -> Unit
        is EditAlunoUiState.Success -> EditAlunoScreen(
            uiState = uiState,
            onBack = onBack,
            onAlterarEntidade = onAlterarEntidade,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditAlunoScreen(
    uiState: EditAlunoUiState.Success,
    onBack: () -> Unit,
    onAlterarEntidade: (String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Aluno") },
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
        var entidade by rememberSaveable { mutableStateOf("") }
        val items by rememberSaveable(entidade) {
            when (entidade) {
                "Escola" -> mutableStateOf(uiState.escolas.map { Pair(it.id ?: "", it.nome) })
                "Responsavel" -> mutableStateOf(uiState.responsaveis.map { Pair(it.id ?: "", it.nome ?: "") })
                "Logradouro" -> mutableStateOf(uiState.logradouros.map { Pair(it.id ?: "", it.nome) })
                "Bairro" -> mutableStateOf(uiState.bairros.map { Pair(it.id ?: "", it.nome) })
                "Cidade" -> mutableStateOf(uiState.cidades.map { Pair(it.id ?: "", it.nome) })
                "Estado" -> mutableStateOf(uiState.estados.map { Pair(it.id ?: "", it.nome) })
                "Nacionalidade" -> mutableStateOf(uiState.nacionalidades.map { Pair(it.id ?: "", it.nome) })
                else -> mutableStateOf(listOf())
            }
        }

        var state by remember { mutableIntStateOf(0) }
        val tabs = listOf("Pessoal", "Escolar", "Endereço")
        Column(modifier = Modifier.padding(padding)) {
            PrimaryTabRow(selectedTabIndex = state) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = state == index,
                        onClick = { state = index },
                        text = {
                            Text(
                                text = tab,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp),
            ) {
                when (state) {
                    0 -> TabPessoal(uiState) {
                        entidade = it
                        openSheet = true
                    }

                    1 -> TabEscolar(uiState) {
                        entidade = it
                        openSheet = true
                    }

                    2 -> TabEndereco(uiState) {
                        entidade = it
                        openSheet = true
                    }
                }
            }
        }

        EsSearchBottomSheet(
            entidade = entidade,
            items = items,
            openSheet = openSheet,
            onOpenSheet = { openSheet = it },
            onEdit = onAlterarEntidade,
        )
    }
}

@Composable
private fun TabPessoal(
    uiState: EditAlunoUiState.Success,
    onSearch: (String) -> Unit,
) {
    EsTextField(
        value = uiState.aluno.nome ?: "",
        onValueChange = {},
        label = "Nome",
    )
    EsTextField(
        value = uiState.aluno.responsavel?.nome ?: "",
        onValueChange = {},
        label = "Responsável",
        readOnly = true,
        leadingIcon = {
            IconButton(onClick = { onSearch("Responsavel") }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
        }
    )
    EsTextField(
        value = uiState.aluno.cpf ?: "",
        onValueChange = {},
        label = "CPF",
    )
    EsTextField(
        value = uiState.aluno.telefone ?: "",
        onValueChange = {},
        label = "Telefone",
    )
    EsTextField(
        value = uiState.aluno.email ?: "",
        onValueChange = {},
        label = "E-mail",
    )
    EsTextField(
        value = uiState.aluno.dataNascimento ?: "",
        onValueChange = {},
        label = "Data de Nascimento",
    )
    EsTextField(
        value = uiState.aluno.rg ?: "",
        onValueChange = {},
        label = "RG",
    )
}

@Composable
private fun TabEscolar(
    uiState: EditAlunoUiState.Success,
    onSearch: (String) -> Unit,
) {
    EsTextField(
        value = uiState.aluno.escola ?: "",
        onValueChange = {},
        label = "Escola",
        readOnly = true,
        leadingIcon = {
            IconButton(onClick = { onSearch("Escola") }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
        }
    )
    EsTextField(
        value = uiState.aluno.serie.toString(),
        onValueChange = {},
        label = "Série",
    )
    EsTextField(
        value = uiState.aluno.turno ?: "",
        onValueChange = {},
        label = "Turno",
        readOnly = true,
        leadingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
        }
    )
}

@Composable
private fun TabEndereco(
    uiState: EditAlunoUiState.Success,
    onSearch: (String) -> Unit,
) {
    EsTextField(
        value = uiState.aluno.endereco?.logradouro ?: "",
        onValueChange = {},
        label = "Logradouro",
        readOnly = true,
        leadingIcon = {
            IconButton(onClick = { onSearch("Logradouro") }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
        }
    )
    EsTextField(
        value = uiState.aluno.endereco?.numero ?: "",
        onValueChange = {},
        label = "Número",
    )
    EsTextField(
        value = uiState.aluno.endereco?.complemento ?: "",
        onValueChange = {},
        label = "Complemento",
    )
    EsTextField(
        value = uiState.aluno.endereco?.bairro ?: "",
        onValueChange = {},
        label = "Bairro",
        readOnly = true,
        leadingIcon = {
            IconButton(onClick = { onSearch("Bairro") }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
        }
    )
    EsTextField(
        value = uiState.aluno.endereco?.cep ?: "",
        onValueChange = {},
        label = "CEP",
    )
    EsTextField(
        value = uiState.aluno.endereco?.cidade ?: "",
        onValueChange = {},
        label = "Cidade",
        readOnly = true,
        leadingIcon = {
            IconButton(onClick = { onSearch("Cidade") }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
        }
    )
    EsTextField(
        value = uiState.aluno.endereco?.estado ?: "",
        onValueChange = {},
        label = "Estado",
        readOnly = true,
        leadingIcon = {
            IconButton(onClick = { onSearch("Estado") }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
        }
    )
}