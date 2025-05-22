package br.com.joanadarc.espacosaber.core.data.di

import br.com.joanadarc.espacosaber.core.data.repository.AlunoRepository
import br.com.joanadarc.espacosaber.core.data.repository.AlunoRepositoryImpl
import br.com.joanadarc.espacosaber.core.data.repository.AtividadeRepository
import br.com.joanadarc.espacosaber.core.data.repository.AtividadeRepositoryImpl
import br.com.joanadarc.espacosaber.core.data.repository.BairroRepository
import br.com.joanadarc.espacosaber.core.data.repository.BairroRepositoryImpl
import br.com.joanadarc.espacosaber.core.data.repository.CidadeRepository
import br.com.joanadarc.espacosaber.core.data.repository.CidadeRepositoryImpl
import br.com.joanadarc.espacosaber.core.data.repository.EscolaRepository
import br.com.joanadarc.espacosaber.core.data.repository.EscolaRepositoryImpl
import br.com.joanadarc.espacosaber.core.data.repository.EstadoRepository
import br.com.joanadarc.espacosaber.core.data.repository.EstadoRepositoryImpl
import br.com.joanadarc.espacosaber.core.data.repository.LogradouroRepository
import br.com.joanadarc.espacosaber.core.data.repository.LogradouroRepositoryImpl
import br.com.joanadarc.espacosaber.core.data.repository.NacionalidadeRepository
import br.com.joanadarc.espacosaber.core.data.repository.NacionalidadeRepositoryImpl
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

    @Binds
    internal abstract fun bindsLogradouroRepository(
        logradouroRepository: LogradouroRepositoryImpl,
    ): LogradouroRepository

    @Binds
    internal abstract fun bindsNacionalidadeRepository(
        nacionalidadeRepository: NacionalidadeRepositoryImpl,
    ): NacionalidadeRepository

    @Binds
    internal abstract fun bindsBairroRepository(
        bairroRepository: BairroRepositoryImpl,
    ): BairroRepository

    @Binds
    internal abstract fun bindsCidadeRepository(
        cidadeRepository: CidadeRepositoryImpl,
    ): CidadeRepository

    @Binds
    internal abstract fun bindsEstadoRepository(
        estadoRepository: EstadoRepositoryImpl,
    ): EstadoRepository

    @Binds
    internal abstract fun bindsAtividadeRepository(
        atividadeRepository: AtividadeRepositoryImpl,
    ): AtividadeRepository

    @Binds
    internal abstract fun bindsEscolaRepository(
        escolaRepository: EscolaRepositoryImpl,
    ): EscolaRepository
}
