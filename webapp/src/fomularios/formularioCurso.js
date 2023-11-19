function FormularioCurso(botao){
    return (
        <form>
            <h1>Formulario do Curso</h1>
            <input type='text' className='form-control' placeholder ='Código' id="codigoCurso"/>
            <input type='text' className='form-control' placeholder ='Nome' id="nomeCurso"/>
            <input type='time' className='form-control' placeholder='Carga Horaria' id='cargaHorarioCurso'/> 
        <div>
            {botao
             ?
             <input type='button' value='Cadastrar'/>
             :
             <div>
             <input type='button' value='Cadastrar'/>
             <input type='button' value='Limpar'/>
             <input type='button' value='Cancelar'/>
             </div> 
            }
        </div>    
        </form>
        )
}


export default FormularioCurso;