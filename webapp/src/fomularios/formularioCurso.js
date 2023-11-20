import React, { useState } from 'react';

function FormularioCurso() {
  const [curso, setCurso] = useState({
    codigo: '',
    nome: '',
    cargaHoraria: '',
  });

  const aoDigitar = (e) => {
    const { id, value } = e.target;
    setCurso((prevCurso) => ({
      ...prevCurso,
      [id]: value,
    }));
  };

  const cadastrarCurso = () => {
    fetch('http:localhost:8080/cadastrarCurso', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(curso),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log('Cadastro realizado com sucesso:', data);
      })
      .catch((error) => {
        console.error('Erro ao cadastrar curso:', error);
      });
  };

  return (
    <form>
      <h1>Formulário do Curso</h1>
      <div>
        <input
          type='text'
          onChange={aoDigitar}
          className='form-control'
          placeholder='Código'
          id='codigo'
        />
        <input
          type='text'
          onChange={aoDigitar}
          className='form-control'
          placeholder='Nome'
          id='nome'
        />
      </div>
      <div id='time'>
        <input
          type='time'
          onChange={aoDigitar}
          className='form-control'
          placeholder='Carga Horária'
          id='cargaHoraria'
        />
      </div>
      <div>
        <input type='button' value='Cadastrar' onClick={cadastrarCurso} />
        <input type='button' value='Limpar' />
        <input type='button' value='Cancelar' />
      </div>
    </form>
  );
}

export default FormularioCurso;
