import React, { useState, useEffect } from 'react';
import { format } from 'date-fns';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrash, faList } from '@fortawesome/free-solid-svg-icons';
import './Form.css';
import ReactDOM from 'react-dom';


import Modal from 'react-modal';
import ReactHTMLTableToExcel from 'react-html-table-to-excel';
import ListarAlunos from '../paginas/listarAlunos';
import FormularioCurso from './formularioCurso';

// ...

function TabelaCursos() {
  // UseState
  const [cursos, setCursos] = useState([]);
  const [cursoEditando, setCursoEditando] = useState(null);

  // UseEffect
  useEffect(() => {
    fetch("http://localhost:8080/api/cursos/listarCursos")
      .then(retorno => retorno.json())
      .then(retorno_json => setCursos(retorno_json));
  }, []);

  const formatarData = (data) => {
    return format(new Date(data), "dd/MM/yyyy");
  };

  const salvarEdicao = (cursoId, novoCurso) => {
    // Lógica para salvar a edição do curso
    fetch(`http://localhost:8080/api/cursos/${cursoId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(novoCurso),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Erro ao editar curso: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        console.log('Curso editado com sucesso:', data);
        // Atualize a lista de cursos
        fetch("http://localhost:8080/api/cursos/listarCursos")
          .then((retorno) => retorno.json())
          .then((retorno_json) => setCursos(retorno_json))
          .catch((error) => {
            console.error('Erro ao obter lista de cursos após edição:', error);
          });
      })
      .catch((error) => {
        console.error('Erro ao editar curso:', error.message);
      });

    // Limpe o curso editando
    setCursoEditando(null);
  };

  const excluirCurso = (cursoId) => {
    fetch(`http://localhost:8080/api/cursos/${cursoId}`, {
      method: 'DELETE'
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Erro ao excluir curso: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        console.log('Curso excluído com sucesso:', data);
        // Atualize a lista de cursos
        fetch("http://localhost:8080/api/cursos/listarCursos")
          .then((retorno) => retorno.json())
          .then((retorno_json) => setCursos(retorno_json))
          .catch((error) => {
            console.error('Erro ao obter lista de cursos após exclusão:', error);
          });
      })
      .catch((error) => {
        console.error('Erro ao excluir curso:', error.message);
      });
  };

  const editarCurso = (curso) => {
    setCursoEditando(curso);
  };

  const fecharEditar = () => {
    // Limpe o curso editando
    setCursoEditando(null);
  };

  return (
    <div>
      <table id="tabelaCursos">
        <thead>
          <tr>
            <th>#</th>
            <th>Código</th>
            <th>Nome</th>
            <th>Carga Horária</th>
            <th>Data do Cadastro</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {cursos.map(curso => (
            <tr key={curso.id}>
              <td>{curso.id}</td>
              <td>{curso.codigo}</td>
              <td>{curso.nome}</td>
              <td>{curso.cargaHoraria}</td>
              <td>{formatarData(curso.dataCadastro)}</td>
              <td>
                <div>
                  <button onClick={() => editarCurso(curso)} title="Editar Curso">
                    Editar
                  </button>
                  <button onClick={() => excluirCurso(curso.id)} title="Excluir Curso">
                    Excluir
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
        <tfoot>
          <tr>
            <td>
              <ReactHTMLTableToExcel
                id="botaoExportarModal"
                className="download-table-xls-button"
                table="tabelaCursos"
                filename="tabelaCursos"
                sheet="tabelaCursos"
                buttonText="Exportar para Excel"
              />
            </td>
          </tr>
        </tfoot>
      </table>

      {cursoEditando && (
        <div>
          <h2>Editar Curso</h2>
          <FormularioCurso cursoEditando={cursoEditando} salvarEdicao={salvarEdicao} />
          <button onClick={fecharEditar}>Fechar Edição</button>
        </div>
      )}
    </div>
  );
}

export default TabelaCursos;

