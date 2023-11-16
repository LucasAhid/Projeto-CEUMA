// Supondo que você está usando algum ORM ou driver de banco de dados, como Sequelize ou Mongoose
const Aluno = require("../models/aluno")

exports.findByCurso = async (cursoId) => {
  // Lógica para buscar alunos por curso no banco de dados
  return Aluno.find({ curso: cursoId }).exec()
}
