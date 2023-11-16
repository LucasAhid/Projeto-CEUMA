import { server } from "./server.js"

const form = document.querySelector("#form")
const input = document.querySelector("#url")
const content = document.querySelector("#content")
// app.js
const cursoForm = document.getElementById("cursoForm")
const alunoForm = document.getElementById("alunoForm")
const cursosContainer = document.getElementById("cursos")
const alunosContainer = document.getElementById("alunos")
//on init
carregarCursos()
carregarAlunosPorCurso(cursoId)
async function carregarCursos() {
  try {
    const response = await fetch("/api/cursos") // Substituir pelo caminho real da sua API
    const cursos = await response.json()
    cursosContainer.innerHTML = ""

    cursos.forEach((curso) => {
      const cursoDiv = document.createElement("div")
      cursoDiv.textContent = `Curso: ${curso.nome}, Código: ${curso.codigo}, Carga Horária: ${curso.cargaHoraria}`
      cursosContainer.appendChild(cursoDiv)
    })
  } catch (error) {
    console.error("Erro ao carregar cursos:", error)
  }
}

async function carregarAlunosPorCurso(cursoId) {
  try {
    const response = await fetch(`/api/alunos/por-curso/${cursoId}`) // Substituir pelo caminho real da sua API
    const alunos = await response.json()
    alunosContainer.innerHTML = ""

    alunos.forEach((aluno) => {
      const alunoDiv = document.createElement("div")
      alunoDiv.textContent = `Aluno: ${aluno.nome}, CPF: ${aluno.cpf}, E-mail: ${aluno.email}`
      alunosContainer.appendChild(alunoDiv)
    })
  } catch (error) {
    console.error("Erro ao carregar alunos por curso:", error)
  }
}

cursoForm.addEventListener("submit", async (event) => {
  event.preventDefault()

  const codigo = document.getElementById("codigo").value
  const nome = document.getElementById("nome").value
  const cargaHoraria = document.getElementById("cargaHoraria").value
  const novoCurso = {
    codigo: codigo,
    nome: nome,
    cargaHoraria: cargaHoraria,
  }

  try {
    const response = await fetch("/api/cursos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(novoCurso),
    })

    if (response.ok) {
      console.log("Curso cadastrado com sucesso!")
      // Atualize a lista de cursos na página após cadastrar
      carregarCursos()
    } else {
      console.error("Erro ao cadastrar curso:", response.statusText)
    }
  } catch (error) {
    console.error("Erro ao cadastrar curso:", error)
  }
})

alunoForm.addEventListener("submit", async (event) => {
  event.preventDefault()
  
  const codigoAluno = document.getElementById("codigoAluno").value
  const nomeAluno = document.getElementById("nomeAluno").value
  const cpf = document.getElementById("cpf").value
  const endereco = document.getElementById("endereco").value
  const cep = document.getElementById("cep").value
  const email = document.getElementById("email").value
  const telefone = document.getElementById("telefone").value
  const novoAluno = {
    codigo: codigoAluno,
    nome: nomeAluno,
    cpf: cpf,
    endereco: endereco,
    cep: cep,
    email: email,
    telefone: telefone,
  }

  try {
    const response = await fetch("/api/alunos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(novoAluno),
    })

    if (response.ok) {
      console.log("Aluno cadastrado com sucesso!")
      // Atualize a lista de alunos por curso na página após cadastrar
      carregarAlunosPorCurso(cursoId)
    } else {
      console.error("Erro ao cadastrar aluno:", response.statusText)
    }
  } catch (error) {
    console.error("Erro ao cadastrar aluno:", error)
  }
})
