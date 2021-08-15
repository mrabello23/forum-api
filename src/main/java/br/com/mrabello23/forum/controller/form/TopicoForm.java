package br.com.mrabello23.forum.controller.form;

import br.com.mrabello23.forum.model.Topico;
import br.com.mrabello23.forum.repository.CursoRepository;

public class TopicoForm {
    private String titulo;
    private String mensagem;
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        return new Topico(titulo, mensagem, cursoRepository.findByNome(nomeCurso));
    }
}
