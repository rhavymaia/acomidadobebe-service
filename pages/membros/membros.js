class Membro{

    constructor(){
        // Definindo os valores da linas da tabela
        this.id = 1;
        this.arrayMembros = [];
        this.editId = null
    }

    salvar(){
        let membro = this.lerDados()

        if(this.validaCampos(membro)){
            if(this.editId == null){
                this.adicionar(membro);
            } else {
                this.atualizar(this.editId), membro;
            }
        }

        this.listaTabela();
        this.cancelar();
    }

    listaTabela(){
        let tbody = document.getElementById('tbody');
        tbody.innerText = '';

        for(let i = 0; i < this.arrayMembros.length; i++){
            let tr = tbody.insertRow();

            let td_id = tr.insertCell();
            let td_membro = tr.insertCell();
            let td_valor = tr.insertCell();
            let td_descricao = tr.insertCell();
            let td_acoes = tr.insertCell();

            td_id.innerText = this.arrayMembros[i].id;
            td_membro.innerText = this.arrayMembros[i].nomeMembro;
            td_valor.innerText = this.arrayMembros[i].idade;
            td_descricao.innerText = this.arrayMembros[i].descricao;

            td_id.classList.add('center')

            let imgEdit = document.createElement('img');
            imgEdit.src = 'img/edit.png';
            imgEdit.setAttribute("onclick", "membro.preparaEdicao( "+ JSON.stringify(this.arrayMembros[i]) +")")


            let imgDelete = document.createElement('img')
            imgDelete.src = 'img/delete.png';
            imgDelete.setAttribute("onclick", "membro.deletar( "+ this.arrayMembros[i].id +")")

            td_acoes.appendChild(imgEdit);
            td_acoes.appendChild(imgDelete);
        }
    }

    adicionar(membro){ //Adicionando o valores dentro do array
        this.arrayMembros.push(membro);
        this.id++;
    }

    atualizar(id, membro){
        for(let i = 0; i < this.arrayMembros.length; i++){
            if(this.arrayMembros[i].id == id){
                this.arrayMembros[i].membro = membro.nomeMembro;
                this.arrayMembros[i].idade = membro.idade;
                this.arrayMembros[i].descricao = membro.descricao;
            }
        }
    }

    preparaEdicao(dados){
        this.editId = dados.id

        document.getElementById("membro").value = dados.nomeMembro;
        document.getElementById("idade").value = dados.idade;
        document.getElementById("descricao").value = dados.descricao;

        document.getElementById("btnEdit").innerHTML = "Atualizar";
    }

    lerDados(){
        // Recebendo os valores dos campos
        let membro = {}

        membro.id = this.id;
        membro.nomeMembro = document.getElementById('membro').value;
        membro.idade = document.getElementById('idade').value;
        membro.descricao = document.getElementById('descricao').value;

        return membro
    }

    validaCampos(membro){
        let msg = '';

        if(membro.nomeMembro == ''){
            msg += '- Informe o Nome do Membro \n';
        }

        if(membro.idade == ''){
            msg += '- Informe o Valor do Membro \n';
        }

        if(msg != ''){
            alert(msg);
            return false;
        }

        return true;
    }

    cancelar(){
        document.getElementById('membro').value = '';
        document.getElementById('idade').value = '';
        document.getElementById('descricao').value = '';
    }

    deletar(id){
        if(confirm('Deseja realmente deletar o membro com o ID: ' + id + " ?")){
            let tbody = document.getElementById('tbody');
    
            for(let i = 0; i < this.arrayMembros.length; i++){
                if(this.arrayMembros[i].id == id){
                    this.arrayMembros.splice(i, 1);
                    tbody.deleteRow(i);
                }
            }
        }
    }
}

var membro = new Membro();