async function carregarPacientes() {
  const res = await fetch("/pacientes");
  const pacientes = await res.json();
  preencherSelects(pacientes);
  atualizarListaPacientes(pacientes);
  atualizarHistorico(pacientes);
}

function preencherSelects(pacientes) {
  const pacienteSelect = document.getElementById("paciente");
  const historicoSelect = document.getElementById("historico-paciente");
  pacienteSelect.innerHTML = "";
  historicoSelect.innerHTML = "";
  pacientes.forEach(p => {
    const opt = new Option(p.nome, p.id);
    pacienteSelect.add(opt.cloneNode(true));
    historicoSelect.add(opt.cloneNode(true));
  });
}

function atualizarListaPacientes(pacientes) {
  const lista = document.getElementById("pacientes-list");
  lista.innerHTML = "";
  pacientes.forEach(p => {
    const li = document.createElement("li");
    li.className = "list-group-item";
    li.textContent = p.nome;
    lista.appendChild(li);
  });
}

async function atualizarHistorico() {
  const pacienteId = document.getElementById("historico-paciente").value;
  const res = await fetch(`/consultas/paciente/${pacienteId}`);
  const consultas = await res.json();
  const lista = document.getElementById("historico-list");
  lista.innerHTML = "";
  consultas.forEach(c => {
    const li = document.createElement("li");
    li.className = "list-group-item";
    li.textContent = `${c.tipo.toUpperCase()} - ${new Date(c.data).toLocaleString()}`;
    lista.appendChild(li);
  });
}

document.getElementById("consultorio-form").addEventListener("submit", (e) => {
  e.preventDefault();
  const nome = document.getElementById("consultorio").value;
  alert(`Consultório "${nome}" salvo!`); // Aqui pode futuramente chamar uma API para salvar o consultório
  e.target.reset();
});

document.getElementById("consulta-form").addEventListener("submit", async (e) => {
  e.preventDefault();
  const pacienteId = parseInt(document.getElementById("paciente").value);
  const data = document.getElementById("data").value;
  const tipo = document.getElementById("tipo").value;

  const consulta = { pacienteId, data, tipo };
  await fetch("/consultas", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(consulta)
  });

  alert("Consulta agendada com sucesso!");
  e.target.reset();
  atualizarHistorico();
});

document.getElementById("historico-paciente").addEventListener("change", atualizarHistorico);

document.getElementById("gerar-relatorio").addEventListener("click", async () => {
  const res = await fetch("/consultas");
  const consultas = await res.json();
  const total = consultas.reduce((sum, c) => sum + (c.valor || 0), 0);
  document.getElementById("resultado-relatorio").textContent = `Total a receber: R$ ${total.toFixed(2)}`;
});

carregarPacientes();