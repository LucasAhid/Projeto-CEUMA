package br.com.api.cursos.interfaces;

import org.springframework.stereotype.Component;

@Component
public class IResposta {
    
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
