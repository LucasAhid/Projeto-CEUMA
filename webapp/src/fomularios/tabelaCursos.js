import React, { useState, useEffect } from 'react';
import './Form.css'
import Modal from 'react-modal';
import ReactHTMLTableToExcel from 'react-html-table-to-excel';
import ListarAlunos from '../paginas/listarAlunos';

function TabelaCursos() {
  //UseState
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [cursos, setCursos] = useState([])
  
  //UseEffect
  useEffect(() => {
    fetch("http:localhost:8080/listarCursos")
    .then(retorno => retorno.json())
    .then(retorno_json => setCursos(retorno_json));
  }, []);

  //Modal
  const openModal = () => {
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
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>CUR001</td>
          <td>Curso de Exemplo</td>
          <td>40 horas</td>
          <td>2023-11-19</td>
          <td>
          <button onClick={openModal}>
                <FontAwesomeIcon icon={faListAlt} />
              </button>
              <button onClick={() => console.log('Editar Curso clicado')}>
                <FontAwesomeIcon icon={faEdit} />
              </button>
              <button onClick={() => console.log('Editar Excluir clicado')}>
                <FontAwesomeIcon icon={faTrash} />
              </button>
          </td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colSpan="5">
            {/* <button onClick={openModal}>Listar Alunos</button>
            <button onClick={() => console.log('Editar Curso clicado')}>Editar Curso</button> */}
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
