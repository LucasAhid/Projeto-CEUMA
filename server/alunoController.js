const alunoService = require('../services/alunoService');

exports.listarAlunosPorCurso = async (req, res) => {
  const cursoId = req.params.cursoId;
  try {
    const alunos = await alunoService.listarAlunosPorCurso(cursoId);
    res.json(alunos);
  } catch (error) {
    console.error('Erro ao listar alunos por curso:', error);
    res.status(500).json({ error: 'Erro interno do servidor' });
  }
};
