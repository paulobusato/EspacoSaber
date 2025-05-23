package br.com.joanadarc.espacosaber.feature.responsavel

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
internal fun EditResponsavelRoute(
    modifier: Modifier = Modifier,
    viewModel: EditResponsavelViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    EditResponsavelScreen(
        uiState = uiState,
        onAlterarEntidade =  viewModel::alterarEntidade,
        modifier = modifier,
    )
}

@Composable
internal fun EditResponsavelScreen(
    uiState: EditResponsavelUiState,
    onAlterarEntidade: (String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        EditResponsavelUiState.Loading -> Unit
        is EditResponsavelUiState.Success -> EditResponsavelScreen(
            uiState = uiState,
            onAlterarEntidade = onAlterarEntidade,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditResponsavelScreen(
    uiState: EditResponsavelUiState.Success,
    onAlterarEntidade: (String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Responsável") },
                navigationIcon = {
                    IconButton(onClick = {}) {
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
                "Logradouro" -> mutableStateOf(uiState.logradouros.map { Pair(it.id ?: "", it.nome) })
                "Bairro" -> mutableStateOf(uiState.bairros.map { Pair(it.id ?: "", it.nome) })
                "Cidade" -> mutableStateOf(uiState.cidades.map { Pair(it.id ?: "", it.nome) })
                "Estado" -> mutableStateOf(uiState.estados.map { Pair(it.id ?: "", it.nome) })
                "Nacionalidade" -> mutableStateOf(uiState.nacionalidades.map { Pair(it.id ?: "", it.nome) })
                else -> mutableStateOf(listOf())
            }
        }

        var state by remember { mutableIntStateOf(0) }
        val tabs = listOf("Pessoal", "Endereço")
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

                    1 -> TabEndereco(uiState) {
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
    uiState: EditResponsavelUiState.Success,
    onSearch: (String) -> Unit,
) {
    EsTextField(
        value = uiState.responsavel.nome ?: "",
        onValueChange = {},
        label = "Nome",
    )
    EsTextField(
        value = uiState.responsavel.dataNascimento ?: "",
        onValueChange = {},
        label = "CPF",
    )
    EsTextField(
        value = uiState.responsavel.nacionalidade ?: "",
        onValueChange = {},
        label = "Nacionalidade",
        readOnly = true,
        leadingIcon = {
            IconButton(onClick = { onSearch("Nacionalidade") }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
        }
    )
    EsTextField(
        value = uiState.responsavel.cidadeNascimento ?: "",
        onValueChange = {},
        label = "Cidade de Nascimento",
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
        value = uiState.responsavel.rg ?: "",
        onValueChange = {},
        label = "RG",
    )
    EsTextField(
        value = uiState.responsavel.cpf ?: "",
        onValueChange = {},
        label = "CPF",
    )
    EsTextField(
        value = uiState.responsavel.telefone ?: "",
        onValueChange = {},
        label = "Telefone",
    )
    EsTextField(
        value = uiState.responsavel.email ?: "",
        onValueChange = {},
        label = "E-mail",
    )
}

@Composable
private fun TabEndereco(
    uiState: EditResponsavelUiState.Success,
    onSearch: (String) -> Unit,
) {
    EsTextField(
        value = uiState.responsavel.endereco?.logradouro ?: "",
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
        value = uiState.responsavel.endereco?.numero ?: "",
        onValueChange = {},
        label = "Número",
    )
    EsTextField(
        value = uiState.responsavel.endereco?.complemento ?: "",
        onValueChange = {},
        label = "Complemento",
    )
    EsTextField(
        value = uiState.responsavel.endereco?.bairro ?: "",
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
        value = uiState.responsavel.endereco?.cep ?: "",
        onValueChange = {},
        label = "CEP",
    )
    EsTextField(
        value = uiState.responsavel.endereco?.cidade ?: "",
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
        value = uiState.responsavel.endereco?.estado ?: "",
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