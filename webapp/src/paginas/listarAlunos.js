import React, { useState, useEffect } from 'react';
import FormularioAluno from '../fomularios/formularioAluno';
import TabelaAlunos from '../fomularios/tabelaAlunos';
import ReactHTMLTableToExcel from 'react-html-table-to-excel';

function ListarAlunos({ cursoSelecionadoId }) {
  const [alunosDoCurso, setAlunosDoCurso] = useState([]);
  const [formularioVisivel, setFormularioVisivel] = useState(false);

  useEffect(() => {
    // Consulta a lista de alunos com base no cursoSelecionadoId
    if (cursoSelecionadoId) {
      fetch(`http://localhost:8080/api/alunos/por-curso/${cursoSelecionadoId}`)
        .then((retorno) => retorno.json())
        .then((retorno_json) => setAlunosDoCurso(retorno_json));
    }
  }, [cursoSelecionadoId]);

  const exibirFormulario = () => {
    setFormularioVisivel(true);
  };

  return (
    <div>
      <TabelaAlunos alunos={alunosDoCurso} />

      <button onClick={exibirFormulario}>Novo Aluno</button>
      {formularioVisivel && <FormularioAluno cursoSelecionadoId={cursoSelecionadoId} />}

      <ReactHTMLTableToExcel
        id="botaoExportar"
        className="download-table-xls-button"
        table="tabelaAlunos"
        filename="tabelaAlunos"
        sheet="tabelaAlunos"
        buttonText="Exportar Excel"
      />
    </div>
  );
}

export default ListarAlunos;
