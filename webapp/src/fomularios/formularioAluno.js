function FormularioAluno({botao}){
    return (
        <form>
            <h1>Formulario do Aluno</h1>
            <input type='text' className='form-control' placeholder ='Nome' id="nomeAluno" />
            <input type='text' className='form-control' placeholder ='Código' id="codigoAluno"/>
            <input type='text' className='form-control' placeholder ='CPF' id="cpfAluno" pattern="[0-9]*" 
            OnKeyPress="formatar('###-###-###-##',this)"/>
            <input type='text' className='form-control' placeholder ='Endereço' id="enderecoAluno"/>
            <input type='text' className='form-control' placeholder ='CEP' id="cepAluno" pattern="[0-9]*" 
            OnKeyPress="formatar('#####-###',this)"/>
            <input type='text' className='form-control' placeholder ='Email' id="emailAluno"/>
        <div> {botao
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

export default FormularioAluno;