package br.com.api.cursos.interfaces;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "curso")
public class ICurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private LocalDate dataCadastro;
    private int cargaHoraria;
   
    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }
    public String getCodigo() {
      return codigo;
    }
    public void setCodigo(String codigo) {
      this.codigo = codigo;
    }
    public String getNome() {
      return nome;
    }
    public void setNome(String nome) {
      this.nome = nome;
    }
    public LocalDate getDataCadastro() {
      return dataCadastro;
    }
    public void setDataCadastro(LocalDate dataCadastro) {
      this.dataCadastro = dataCadastro;
    }
    public Integer getCargaHoraria() {
      return cargaHoraria;
    }
    public void setCargaHoraria(int cargaHoraria) {
      this.cargaHoraria = cargaHoraria;
    }
}

