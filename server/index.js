import express from "express"
const express = require("express")
const app = express()
const alunoRoutes = require("./routes/alunoRoutes")

app.use("/api/alunos", alunoRoutes)

const PORT = process.env.PORT || 3000
app.listen(PORT, () => {
  console.log(`Servidor rodando na porta ${PORT}`)
})
