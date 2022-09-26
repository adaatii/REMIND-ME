package com.example.agenda_pessoal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.agenda_pessoal.Controller.UsuarioController;
import com.example.agenda_pessoal.Model.Usuario;

import java.util.Objects;

public class A_Register extends AppCompatActivity {

    String nome, email, telefone, password;
    EditText et_nome;
    EditText et_email;
    EditText et_telefone;
    EditText et_password;
    TextView tv_cadastro;
    Button btn_cadastro;
    Button btn_voltar;

    UsuarioController l = new UsuarioController(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Importantes
        Objects.requireNonNull(getSupportActionBar()).hide(); //esconde a action bar

        btn_cadastro=findViewById(R.id.btn_cadastrar);



        et_nome=findViewById(R.id.et_nome);
        et_email=findViewById(R.id.et_email);
        et_telefone=findViewById(R.id.et_telefone);
        et_password=findViewById(R.id.et_password);


    }

    public void cadastrar(View v) {

        Usuario obj = new Usuario(nome, email, telefone, password);
        obj.setNome(et_nome.getText().toString());
        obj.setEmail(et_email.getText().toString());
        obj.setTelefone(et_telefone.getText().toString());
        obj.setPassword(et_password.getText().toString());
        l.add(obj);
    }

    public void listar(View v){

        l.lista();
        tv_cadastro.setText(l.result);
    }

    public void abrirLogin(View v){
        Intent it_telaLogin = new Intent(this, A_Home.class);
        startActivity(it_telaLogin);
    }


}