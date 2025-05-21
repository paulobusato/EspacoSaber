package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Aluno
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface AlunoRepository {
    fun obterAlunos(): Flow<List<Aluno>>
}

class AlunoRepositoryImpl @Inject constructor() : AlunoRepository {
    override fun obterAlunos(): Flow<List<Aluno>> = flowOf(
        listOf(
            Aluno(nome = "Joana D'arc"),
            Aluno(nome = "Gabriela Angelo"),
        )
    )
}