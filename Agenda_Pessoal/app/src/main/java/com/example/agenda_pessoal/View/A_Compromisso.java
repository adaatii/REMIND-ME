package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.agenda_pessoal.R;

import java.util.ArrayList;

public class A_Compromisso extends AppCompatActivity {

    private RecyclerView recyclerView;
    private A_RecyclerAdapterCompromisso adapterCompromisso;
    private ArrayList<String> titulo = new ArrayList<>();
    private ArrayList<String> descricao = new ArrayList<>();
    private ArrayList<String> hora = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompromisso);

        recyclerView = findViewById(R.id.recyclerView_Compromisso);
        recyclerView.setLayoutManager(new LinearLayoutManager(A_Compromisso.this)); // Como o RecyclerView será mostrado na tela

        titulo.add("Oftamologista");
        titulo.add("Reunião ED");
        titulo.add("Trabalho Enari");

        descricao.add("Dr. Fulano, irá dilatar a pupila");
        descricao.add("Definir prioridades do trabalho");
        descricao.add("Realizar as alterações no trabalho");

        hora.add("17:00");
        hora.add("11:00");
        hora.add("09:30");

        adapterCompromisso = new A_RecyclerAdapterCompromisso(titulo, descricao, hora, A_Compromisso.this);
        recyclerView.setAdapter(adapterCompromisso);
    }
}