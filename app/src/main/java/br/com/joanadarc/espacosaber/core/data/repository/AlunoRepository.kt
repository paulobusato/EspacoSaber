package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Aluno
import br.com.joanadarc.espacosaber.core.model.Endereco
import br.com.joanadarc.espacosaber.core.model.Responsavel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import javax.inject.Inject

interface AlunoRepository {
    fun obterAluno(id: String): Flow<Aluno>
    fun obterAlunos(): Flow<List<Aluno>>
}

class AlunoRepositoryImpl @Inject constructor() : AlunoRepository {
    override fun obterAluno(id: String): Flow<Aluno> = flowOf(
        Aluno(
            nome = "Joana D'arc",
            cpf = "123.456.789-10",
            dataNascimento = "01/01/2000",
            nacionalidade = "Brasileira",
            telefone = "(11) 99999-9999",
            email = "james.francis.byrnes@example-pet-store.com",
            rg = "12.345.678-9",
            endereco = Endereco(
                logradouro = "Rua Exemplo",
                numero = "123",
                complemento = "Apto 101",
                bairro = "Centro",
                cep = "12345-678",
                cidade = "São Paulo",
                estado = "SP",
            ),
            cidadeNascimento = "São Paulo",
            responsavel = Responsavel(
                nome = "João Silva",
                cpf = "987.654.321-00",
                dataNascimento = "05/05/1980",
                nacionalidade = "Brasileira",
                telefone = "(11) 88888-8888",
                email = "james.madison@examplepetstore.com",
                rg = "98.765.432-1",
                endereco = Endereco(
                    logradouro = "Rua Exemplo",
                    numero = "456",
                    complemento = "Casa 2",
                    bairro = "Centro",
                    cep = "54321-876",
                    cidade = "São Paulo",
                    estado = "SP",
                    ),
                cidadeNascimento = "São Paulo",
            ),
            escola = "Escola Municipal",
            serie = 5,
            turno = "Matutino",
            dataCadastro = LocalDate.now(),
            dataAtualizacao = LocalDate.now(),
        ),
    )

    override fun obterAlunos(): Flow<List<Aluno>> = flowOf(
        listOf(
            Aluno(
                nome = "Joana D'arc",
                cpf = "123.456.789-10",
                dataNascimento = "01/01/2000",
                nacionalidade = "Brasileira",
                telefone = "(11) 99999-9999",
                email = "james.francis.byrnes@example-pet-store.com",
                rg = "12.345.678-9",
                endereco = Endereco(
                    logradouro = "Rua Exemplo",
                    numero = "123",
                    complemento = "Apto 101",
                    bairro = "Centro",
                    cep = "12345-678",
                    cidade = "São Paulo",
                    estado = "SP",
                ),
                cidadeNascimento = "São Paulo",
                responsavel = Responsavel(
                    nome = "João Silva",
                    cpf = "987.654.321-00",
                    dataNascimento = "05/05/1980",
                    nacionalidade = "Brasileira",
                    telefone = "(11) 88888-8888",
                    email = "james.madison@examplepetstore.com",
                    rg = "98.765.432-1",
                    endereco = Endereco(
                        logradouro = "Rua Exemplo",
                        numero = "456",
                        complemento = "Casa 2",
                        bairro = "Centro",
                        cep = "54321-876",
                        cidade = "São Paulo",
                        estado = "SP",
                    ),
                    cidadeNascimento = "São Paulo",
                ),
                escola = "Escola Municipal",
                serie = 5,
                turno = "Matutino",
                dataCadastro = LocalDate.now(),
                dataAtualizacao = LocalDate.now(),
            ),
            Aluno(
                nome = "Joana D'arc",
                cpf = "123.456.789-10",
                dataNascimento = "01/01/2000",
                nacionalidade = "Brasileira",
                telefone = "(11) 99999-9999",
                email = "james.francis.byrnes@example-pet-store.com",
                rg = "12.345.678-9",
                endereco = Endereco(
                    logradouro = "Rua Exemplo",
                    numero = "123",
                    complemento = "Apto 101",
                    bairro = "Centro",
                    cep = "12345-678",
                    cidade = "São Paulo",
                    estado = "SP",
                ),
                cidadeNascimento = "São Paulo",
                responsavel = Responsavel(
                    nome = "João Silva",
                    cpf = "987.654.321-00",
                    dataNascimento = "05/05/1980",
                    nacionalidade = "Brasileira",
                    telefone = "(11) 88888-8888",
                    email = "james.madison@examplepetstore.com",
                    rg = "98.765.432-1",
                    endereco = Endereco(
                        logradouro = "Rua Exemplo",
                        numero = "456",
                        complemento = "Casa 2",
                        bairro = "Centro",
                        cep = "54321-876",
                        cidade = "São Paulo",
                        estado = "SP",
                    ),
                    cidadeNascimento = "São Paulo",
                ),
                escola = "Escola Municipal",
                serie = 5,
                turno = "Matutino",
                dataCadastro = LocalDate.now(),
                dataAtualizacao = LocalDate.now(),
            ),
        )
    )
}