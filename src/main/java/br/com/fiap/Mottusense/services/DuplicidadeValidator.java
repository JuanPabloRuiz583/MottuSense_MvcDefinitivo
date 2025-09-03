package br.com.fiap.Mottusense.services;

public interface DuplicidadeValidator <T> {
    String validarDuplicidadeCadastro(T entidade);
    String validarDuplicidadeEdicao(T entidade, Long idAtual);
}
