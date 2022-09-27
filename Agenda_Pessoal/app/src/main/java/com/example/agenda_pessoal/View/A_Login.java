package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.agenda_pessoal.R;

import java.util.Objects;

public class A_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alogin);

        // Importantes
        getWindow().setStatusBarColor(Color.rgb(46, 113, 212));
        Objects.requireNonNull(getSupportActionBar()).hide(); //esconde a action bar
    }

    public void abrirTela(View v){
        Intent it_tela = new Intent(this, A_Compromisso.class);
        startActivity(it_tela);
    }
}