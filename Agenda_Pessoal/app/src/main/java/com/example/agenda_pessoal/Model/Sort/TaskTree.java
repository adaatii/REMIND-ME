package com.example.agenda_pessoal.Model.Sort;

import android.util.Log;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskTree {
    private TaskNode root;

    public TaskTree() {
        root = null;
    }

    public void add(int idTask, String[] dateTime, String localDate) {
        boolean check = true;
        if (localDate != null) {
            String[] splitDate;
            splitDate = dateTime[0].split("/");
            LocalDate addDate = LocalDate.of( //addDate armazena a data do nó a ser adicionado
                    Integer.parseInt(splitDate[2]),
                    Integer.parseInt(splitDate[1]),
                    Integer.parseInt(splitDate[0])
            );
            splitDate = localDate.split("/");
            LocalDate pointerDate = LocalDate.of( //pointerDate armazena a data seilecionada a ser comparada
                    Integer.parseInt(splitDate[2]),
                    Integer.parseInt(splitDate[1]),
                    Integer.parseInt(splitDate[0])
            );
            Log.d("TAG", "IF comparação data");
            if (addDate.isAfter(pointerDate) || addDate.isBefore(pointerDate)) { // Só recebe true se forem datas iguais
                check = false;
            }
        }
        Log.d("TAG2", String.valueOf(check));
        if (check) {
            if (root == null) {
                root = new TaskNode(idTask, dateTime);
            } else {
                root.add(idTask, dateTime);
            }
        }
    }

    public ArrayList<Integer> sort() {
        ArrayList<Integer> sortedList = new ArrayList<>();
        if (root != null) {
            sortedList.addAll(root.sort());
        }
        return sortedList;
    }
}
