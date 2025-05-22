package br.com.joanadarc.espacosaber.feature.responsavel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.joanadarc.espacosaber.core.data.repository.BairroRepository
import br.com.joanadarc.espacosaber.core.data.repository.CidadeRepository
import br.com.joanadarc.espacosaber.core.data.repository.EstadoRepository
import br.com.joanadarc.espacosaber.core.data.repository.LogradouroRepository
import br.com.joanadarc.espacosaber.core.data.repository.NacionalidadeRepository
import br.com.joanadarc.espacosaber.core.data.repository.ResponsavelRepository
import br.com.joanadarc.espacosaber.core.model.Bairro
import br.com.joanadarc.espacosaber.core.model.Cidade
import br.com.joanadarc.espacosaber.core.model.Estado
import br.com.joanadarc.espacosaber.core.model.Logradouro
import br.com.joanadarc.espacosaber.core.model.Nacionalidade
import br.com.joanadarc.espacosaber.core.model.Responsavel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EditResponsavelViewModel @Inject constructor(
    responsavelRepository: ResponsavelRepository,
    bairroRepository: BairroRepository,
    cidadeRepository: CidadeRepository,
    estadoRepository: EstadoRepository,
    logradouroRepository: LogradouroRepository,
    nacionalidadeRepository: NacionalidadeRepository,
) : ViewModel() {
    val responsavelId: String = ""

    // O método combine possui overload até 5 flows. Foi necessário separar em duas variáveis.
    val localizacaoFlow: Flow<Map<String, Any>> = combine(
        logradouroRepository.obterLogradouros(),
        bairroRepository.obterBairros(),
        cidadeRepository.obterCidades(),
        estadoRepository.obterEstados(),
        nacionalidadeRepository.obterNacionalidades(),
    ) { logradouro, bairro, cidade, estado, nacionalidade ->
        mapOf(
            "Logradouro" to logradouro,
            "Bairro" to bairro,
            "Cidade" to cidade,
            "Estado" to estado,
            "Nacionalidade" to nacionalidade,
        )
    }

    val uiState: StateFlow<EditResponsavelUiState> = combine(
        responsavelRepository.obterResponsavel(responsavelId).onStart { emit(Responsavel()) },
        localizacaoFlow,
    ) { responsavel, localizacao ->
        EditResponsavelUiState.Success(
            responsavel = responsavel,
            logradouros = localizacao["Logradouro"] as List<Logradouro>,
            bairros = localizacao["Bairro"] as List<Bairro>,
            cidades = localizacao["Cidade"] as List<Cidade>,
            estados = localizacao["Estado"] as List<Estado>,
            nacionalidades = localizacao["Nacionalidade"] as List<Nacionalidade>,
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = EditResponsavelUiState.Loading,
        )
}

sealed class EditResponsavelUiState {
    object Loading : EditResponsavelUiState()
    data class Success(
        val responsavel: Responsavel,
        val logradouros: List<Logradouro>,
        val bairros: List<Bairro>,
        val cidades: List<Cidade>,
        val estados: List<Estado>,
        val nacionalidades: List<Nacionalidade>,
    ) : EditResponsavelUiState()
}