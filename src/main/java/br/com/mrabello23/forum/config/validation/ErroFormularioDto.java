package br.com.mrabello23.forum.config.validation;

public class ErroFormularioDto {
    private final String campo;
    private final String erro;

    public ErroFormularioDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
