package br.com.joanadarc.espacosaber.feature.aluno

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.joanadarc.espacosaber.core.data.repository.AlunoRepository
import br.com.joanadarc.espacosaber.core.data.repository.BairroRepository
import br.com.joanadarc.espacosaber.core.data.repository.CidadeRepository
import br.com.joanadarc.espacosaber.core.data.repository.EscolaRepository
import br.com.joanadarc.espacosaber.core.data.repository.EstadoRepository
import br.com.joanadarc.espacosaber.core.data.repository.LogradouroRepository
import br.com.joanadarc.espacosaber.core.data.repository.NacionalidadeRepository
import br.com.joanadarc.espacosaber.core.data.repository.ResponsavelRepository
import br.com.joanadarc.espacosaber.core.model.Aluno
import br.com.joanadarc.espacosaber.core.model.Bairro
import br.com.joanadarc.espacosaber.core.model.Cidade
import br.com.joanadarc.espacosaber.core.model.Escola
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
class EditAlunoViewModel @Inject constructor(
    alunoRepository: AlunoRepository,
    responsavelRepository: ResponsavelRepository,
    escolaRepository: EscolaRepository,
    bairroRepository: BairroRepository,
    cidadeRepository: CidadeRepository,
    estadoRepository: EstadoRepository,
    logradouroRepository: LogradouroRepository,
    nacionalidadeRepository: NacionalidadeRepository,
) : ViewModel() {
    val alunoId: String = ""

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

    val uiState: StateFlow<EditAlunoUiState> = combine(
        alunoRepository.obterAluno(alunoId).onStart { emit(Aluno()) },
        responsavelRepository.obterResponsaveis(),
        escolaRepository.obterEscolas(),
        localizacaoFlow,
    ) { aluno, responsaveis, escolas, localizacao ->
        EditAlunoUiState.Success(
            aluno = aluno,
            responsaveis = responsaveis,
            escolas = escolas,
            logradouros = localizacao["Logradouro"] as List<Logradouro>,
            bairros = localizacao["Bairro"] as List<Bairro>,
            cidades = localizacao["Cidade"] as List<Cidade>,
            estados = localizacao["Estado"] as List<Estado>,
            nacionalidades = localizacao["Nacionalidade"] as List<Nacionalidade>,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = EditAlunoUiState.Loading,
    )

    fun alterarEntidade(
        entidadeNome: String,
        entidadeId: String,
        entidadeDescricao: String,
    ) {

    }
}

sealed class EditAlunoUiState {
    object Loading : EditAlunoUiState()
    data class Success(
        val aluno: Aluno,
        val responsaveis: List<Responsavel>,
        val escolas: List<Escola>,
        val logradouros: List<Logradouro>,
        val bairros: List<Bairro>,
        val cidades: List<Cidade>,
        val estados: List<Estado>,
        val nacionalidades: List<Nacionalidade>,
    ) : EditAlunoUiState()
}