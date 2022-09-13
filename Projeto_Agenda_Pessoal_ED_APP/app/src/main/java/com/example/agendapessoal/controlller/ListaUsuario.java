package com.example.agendapessoal.controlller;

import com.example.agendapessoal.model.Usuario;

public class ListaUsuario {
    private Usuario[] c;
    private int ultimo;
    public String nome, email, telefone, password, result;

    public ListaUsuario(int N) {
        c = new Usuario[N];
        ultimo = -1;
    }
    public void add(Usuario obj){

        nome = obj.getNome();
        email = obj.getEmail();
        telefone = obj.getTelefone();
        password = obj.getPassword();

        //problema colocar o obj dentro do vetor
        ultimo = ultimo + 1;
        c[ultimo] = new Usuario(nome,email,telefone,password);
    }

   public void lista(){

        for(int i=0; i<=ultimo; i++){
           result = (c[i].getNome() +" "+c[i].getEmail() + "  " + c[i].getTelefone());
            System.out.println(c[i].getNome() +" "+c[i].getEmail() + "  " + c[i].getTelefone());
        }

    }

}
