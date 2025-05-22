package br.com.joanadarc.espacosaber.feature.sessao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.joanadarc.espacosaber.core.data.repository.SessaoRepository
import br.com.joanadarc.espacosaber.core.model.Sessao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SessaoViewModel @Inject constructor(
    sessaoRepository: SessaoRepository,
) : ViewModel() {
    val uiState: StateFlow<SessaoUiState> = sessaoRepository.obterSessoes()
        .map { sessoes -> SessaoUiState.Success(sessoes) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SessaoUiState.Loading,
        )
}

sealed class SessaoUiState {
    object Loading : SessaoUiState()
    data class Success(val sessoes: List<Sessao>) : SessaoUiState()
}