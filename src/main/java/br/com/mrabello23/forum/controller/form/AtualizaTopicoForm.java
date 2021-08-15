package br.com.mrabello23.forum.controller.form;

import br.com.mrabello23.forum.model.Topico;
import br.com.mrabello23.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizaTopicoForm {
    @NotNull @NotEmpty @Length(min=5) // bean validation
    private String titulo;

    @NotNull @NotEmpty @Length(min=5) // bean validation
    private String mensagem;

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

    public Topico atualizar(Long id, Topico topico) {
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }
}
