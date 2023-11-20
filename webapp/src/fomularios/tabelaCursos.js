import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrash, faList } from '@fortawesome/free-solid-svg-icons';
import './Form.css'


import Modal from 'react-modal';
import ReactHTMLTableToExcel from 'react-html-table-to-excel';
import ListarAlunos from '../paginas/listarAlunos';

function TabelaCursos() {
   
  //UseState
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [cursos, setCursos] = useState([])
  const [cursoSelecionadoId, setCursoSelecionadoId] = useState(null);
    
  //UseEffect
  useEffect(() => {
    fetch("http://localhost:8080/api/cursos/listarCursos")
    .then(retorno => retorno.json())
    .then(retorno_json => setCursos(retorno_json));
  }, []);

  //Modal
  const openModal = (cursoId) => {
    setCursoSelecionadoId(cursoId);
    setModalIsOpen(true);
  };

  const closeModal = () => {
    setModalIsOpen(false);
  };


  return (
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
          <td>{curso.dataCadastro}</td>
          <td>
            <button onClick={() => openModal(curso.id)} title="Listar Alunos">
              <FontAwesomeIcon icon={faList} />
            </button>
            <button onClick={() => console.log('Editar Curso clicado')} title="Editar Curso">
              <FontAwesomeIcon icon={faEdit} />
            </button>
            <button onClick={() => console.log('Excluir Curso clicado')} title="Excluir Curso">
              <FontAwesomeIcon icon={faTrash} />
            </button>
          </td>
        </tr>
      ))}

      </tbody>
      <tfoot>
        <tr>
          <ReactHTMLTableToExcel
            id="botaoExportarModal"
            className="download-table-xls-button"
            table="tabelaCursos"
            filename="tabelaCursos"
            sheet="tabelaCursos"
            buttonText="Exportar para Excel"
          />
        </tr>
      </tfoot>
        
        {/* Botão de Exportação */}

      <Modal isOpen={modalIsOpen} onRequestClose={closeModal}>
        <h2>Listar Alunos</h2>
        <ListarAlunos />

        <button onClick={closeModal}>Fechar Modal</button>
      </Modal>
    </table>
  );
}

export default TabelaCursos;
