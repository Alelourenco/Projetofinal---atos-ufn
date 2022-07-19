const requestOrigin = 'http://127.0.0.1:8080/chamado/chamado';

function clearForm() {
    document.getElementById("inputChamado").value = null;
    document.getElementById("inputTitulo").value = null;
    document.getElementById("inputStatus").value = null;
    document.getElementById("inputComentario").value = null;
    
}

function insert() {
    fetch(requestOrigin, {
        method: 'POST',
        body: JSON.stringify(getInputValues())
    })
    .then(response => response.json())
    .then(data => {
        // Informar se houve um erro ou atualizar a lista caso ocorra tudo certo
        if(data['success'] == true) {
            updateList();
        }
        else {
            console.log(data['error']);
        }
    })
}

function update() {
    fetch(requestOrigin, {
        method: 'PUT',
        body: JSON.stringify(getInputValues())
    })
    .then(response => response.json())
    .then(data => {
        // Informar erro ou atualizar a lista
        if(data['success'] == true) {
            updateList();
        }
        else {
            console.log(data['error']);
        }
    })
}

function remove() {
    fetch(requestOrigin, {
        method: 'DELETE',
        body: JSON.stringify(getInputValues())
    })
    .then(response => response.json())
    .then(data => {
        // Informar se houve um erro ou atualiza
        if(data['success'] == true) {
            updateList();
        }
        else {
            console.log(data['error']);
        }
    })
}

function updateList() {
    fetch(requestOrigin, {
        method: 'GET'
    })
    .then(res => res.json())
    .then(data => {
        let htmlCode = '';

        for(p of data) {
            htmlCode += '<tr>';
            htmlCode += '<td>' + p["chamado"] + '</td>';
            htmlCode += '<td>' + p["titulo"] + '</td>';
            htmlCode += '<td>' + p["status"] + '</td>';
            htmlCode += '<td>' + p["comentario"] + '</td>';
            htmlCode += '</tr>';
        }

        document.getElementById("tableBody").innerHTML = htmlCode;
    })
}

function confirm() {
    switch (checkFormType()) {
        case 'insert':
            insert();
            break;
        case 'update':
            update();
            break;
        case 'remove':
            remove();
    }
}

function checkFormType() {
    radioInputs = document.querySelectorAll('input[name="radioInput"');

    for (const r of radioInputs) {
        if (r.checked) {
            return r.value;
        }
    }

    return '';
}

function getInputValues() {
    return obj = {
        chamado: document.getElementById("inputChamado").value,
        titulo: document.getElementById("inputTitulo").value,
        status: document.getElementById("inputStatus").value,
        comentario: document.getElementById("inputComentario").value,
    }
}

function init() {

}

init();