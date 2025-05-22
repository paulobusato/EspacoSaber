package br.com.joanadarc.espacosaber.feature.sessao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.joanadarc.espacosaber.core.data.repository.AtividadeRepository
import br.com.joanadarc.espacosaber.core.data.repository.SessaoRepository
import br.com.joanadarc.espacosaber.core.model.Atividade
import br.com.joanadarc.espacosaber.core.model.Sessao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EditSessaoViewModel @Inject constructor(
    sessaoRepository: SessaoRepository,
    atividadeRepository: AtividadeRepository,
) : ViewModel() {
    val sessaoId: String = ""

    val uiState: StateFlow<EditSessaoUiState> = combine(
        sessaoRepository.obterSessao(sessaoId).onStart { emit(Sessao()) },
        atividadeRepository.obterAtividades(),
    ) { sessao, atividades ->
        EditSessaoUiState.Success(sessao, atividades)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = EditSessaoUiState.Loading,
    )
}

sealed class EditSessaoUiState {
    object Loading : EditSessaoUiState()
    data class Success(
        val sessao: Sessao,
        val atividades: List<Atividade>,
    ) : EditSessaoUiState()
}