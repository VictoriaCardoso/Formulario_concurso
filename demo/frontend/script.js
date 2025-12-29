const API_URL = "http://localhost:8080/api/aprovados";

document.getElementById("formAprovado").addEventListener("submit", cadastrarAprovado);

async function cadastrarAprovado(event) {
    event.preventDefault();

    const aprovado = {
        nome: document.getElementById("nome").value,
        email: document.getElementById("email").value,
        telefone: document.getElementById("telefone").value,
        concursos: document.getElementById("concursos").value
    };

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(aprovado)
        });

        if (!response.ok) {
            const erro = await response.text();
            throw new Error(erro);
        }

        alert("Aprovado cadastrado com sucesso!");

        document.getElementById("formAprovado").reset();
        carregarAprovados();

    } catch (error) {
        alert("Erro: " + error.message);
    }
}

async function carregarAprovados() {
    const listaDiv = document.getElementById("lista");
    listaDiv.innerHTML = "Carregando...";

    try {
        const response = await fetch(API_URL);
        const aprovados = await response.json();

        listaDiv.innerHTML = "";

        aprovados.forEach(aprovado => {
            const card = document.createElement("div");
            card.className = "card";

            card.innerHTML = `
                <strong>Nome:</strong> ${aprovado.nome}<br>
                <strong>Email:</strong> ${aprovado.email}<br>
                <strong>Telefone:</strong> ${aprovado.telefone ?? "-"}<br>
                <strong>Concursos:</strong> ${aprovado.concursos ?? "-"}
            `;

            listaDiv.appendChild(card);
        });

    } catch (error) {
        listaDiv.innerHTML = "Erro ao carregar dados";
    }
}
