package com.example.agenda_pessoal.Model.Sort;

import android.util.Log;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskTree {
    private TaskNode root;

    public TaskTree() {
        root = null;
    }

    public void add(int idTask, String[] dateTime, String data) {
        boolean check = true;
        if (data != null) {
            String[] splitDate;
            splitDate = dateTime[0].split("/");
            LocalDate firstDate = LocalDate.of( //FirstDate armazena a data do nó a ser adicionado
                    Integer.parseInt(splitDate[2]),
                    Integer.parseInt(splitDate[1]),
                    Integer.parseInt(splitDate[0])
            );
            splitDate = data.split("/");
            LocalDate secondDate = LocalDate.of( //FirstDate armazena a data do nó a ser adicionado
                    Integer.parseInt(splitDate[2]),
                    Integer.parseInt(splitDate[1]),
                    Integer.parseInt(splitDate[0])
            );
            Log.d("TAG", "IF comparação data");
            if (firstDate.isAfter(secondDate) || firstDate.isBefore(secondDate)) {
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
