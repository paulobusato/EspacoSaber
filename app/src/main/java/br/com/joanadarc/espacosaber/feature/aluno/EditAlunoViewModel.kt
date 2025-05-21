package br.com.joanadarc.espacosaber.feature.aluno

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.joanadarc.espacosaber.core.data.repository.AlunoRepository
import br.com.joanadarc.espacosaber.core.model.Aluno
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EditAlunoViewModel @Inject constructor(
    alunoRepository: AlunoRepository,
) : ViewModel() {
    val alunoId: String = ""

    val uiState: StateFlow<EditAlunoUiState> = alunoRepository.obterAluno(alunoId)
        .onStart { emit(Aluno()) }
        .map { aluno ->
            EditAlunoUiState.Success(aluno)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = EditAlunoUiState.Loading,
        )
}

sealed class EditAlunoUiState {
    object Loading : EditAlunoUiState()
    data class Success(val aluno: Aluno) : EditAlunoUiState()
}