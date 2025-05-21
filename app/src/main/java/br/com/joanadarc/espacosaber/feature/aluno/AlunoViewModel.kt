package br.com.joanadarc.espacosaber.feature.aluno

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.joanadarc.espacosaber.core.data.repository.AlunoRepository
import br.com.joanadarc.espacosaber.core.model.Aluno
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AlunoViewModel @Inject constructor(
    alunoRepository: AlunoRepository,
) : ViewModel() {
    val uiState: StateFlow<AlunoUiState> = alunoRepository.obterAlunos()
        .map { alunos -> AlunoUiState.Success(alunos) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AlunoUiState.Loading,
        )
}

sealed class AlunoUiState {
    object Loading : AlunoUiState()
    data class Success(val alunos: List<Aluno>) : AlunoUiState()
}