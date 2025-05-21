package br.com.joanadarc.espacosaber.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.joanadarc.espacosaber.core.data.repository.AlunoRepository
import br.com.joanadarc.espacosaber.core.data.repository.SessaoRepository
import br.com.joanadarc.espacosaber.core.model.Aluno
import br.com.joanadarc.espacosaber.core.model.Sessao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    alunoRepository: AlunoRepository,
    sessaoRepository: SessaoRepository,
) : ViewModel() {
    val uiState: StateFlow<HomeUiState> = combine(
        alunoRepository.obterAlunos(),
        sessaoRepository.obterSessoes(),
    ) { alunos, sessoes ->
        HomeUiState.Success(alunos, sessoes)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState.Loading,
    )
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val alunos: List<Aluno>,
        val sessoes: List<Sessao>,
    ) : HomeUiState()
}