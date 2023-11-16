const alunoRepository = require("../repositories/alunoRepository")

exports.listarAlunosPorCurso = async (cursoId) => {
  // Lógica para obter alunos por curso usando alunoRepository
  return alunoRepository.findByCurso(cursoId)
}
