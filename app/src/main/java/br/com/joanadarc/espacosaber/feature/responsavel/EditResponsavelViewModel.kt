package br.com.joanadarc.espacosaber.feature.responsavel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.joanadarc.espacosaber.core.data.repository.ResponsavelRepository
import br.com.joanadarc.espacosaber.core.model.Responsavel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EditResponsavelViewModel @Inject constructor(
    responsavelRepository: ResponsavelRepository,
) : ViewModel() {
    val responsavelId: String = ""

    val uiState: StateFlow<EditResponsavelUiState> = responsavelRepository.obterResponsavel(responsavelId)
        .onStart { emit(Responsavel()) }
        .map { responsavel ->
            EditResponsavelUiState.Success(responsavel)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = EditResponsavelUiState.Loading,
        )
}

sealed class EditResponsavelUiState {
    object Loading : EditResponsavelUiState()
    data class Success(val responsavel: Responsavel) : EditResponsavelUiState()
}