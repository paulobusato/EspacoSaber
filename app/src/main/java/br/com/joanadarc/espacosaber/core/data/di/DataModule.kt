package br.com.joanadarc.espacosaber.core.data.di

import br.com.joanadarc.espacosaber.core.data.repository.AlunoRepository
import br.com.joanadarc.espacosaber.core.data.repository.AlunoRepositoryImpl
import br.com.joanadarc.espacosaber.core.data.repository.ResponsavelRepository
import br.com.joanadarc.espacosaber.core.data.repository.ResponsavelRepositoryImpl
import br.com.joanadarc.espacosaber.core.data.repository.SessaoRepository
import br.com.joanadarc.espacosaber.core.data.repository.SessaoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsAlunoRepository(
        alunoRepository: AlunoRepositoryImpl,
    ): AlunoRepository

    @Binds
    internal abstract fun bindsSessaoRepository(
        sessaoRepository: SessaoRepositoryImpl,
    ): SessaoRepository

    @Binds
    internal abstract fun bindsResponsavelRepository(
        responsavelRepository: ResponsavelRepositoryImpl,
    ): ResponsavelRepository
}
