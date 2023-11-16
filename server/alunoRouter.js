const express = require("express")
const router = express.Router()
const alunoController = require("../controllers/alunoController")

router.get("/por-curso/:cursoId", alunoController.listarAlunosPorCurso)

module.exports = router
