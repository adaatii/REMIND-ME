package com.example.agenda_pessoal.Model.Sort;

import android.util.Log;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskNode {
    private final Integer idTask;
    private final String[] dateTime;
    private TaskNode left, right;

    public TaskNode(int idTask, String[] dateTime) {
        this.idTask = idTask;
        this.dateTime = dateTime;
        left = right = null;
    }

    public void add(int idTask, String[] newDateTime) {
        boolean check;
        String[] splitDate;
        splitDate = newDateTime[0].split("/");
        LocalDate addDate = LocalDate.of( //addDate armazena a data do nó a ser adicionado
                Integer.parseInt(splitDate[2]),
                Integer.parseInt(splitDate[1]),
                Integer.parseInt(splitDate[0])
        );
        splitDate = dateTime[0].split("/");
        LocalDate pointerDate = LocalDate.of( //pointerDate aponta para a data do nó atual
                Integer.parseInt(splitDate[2]),
                Integer.parseInt(splitDate[1]),
                Integer.parseInt(splitDate[0])
        );
        if (addDate.isAfter(pointerDate)) {
            check = true;
        } else if (addDate.isBefore(pointerDate)) {
            check = false;
        } else {
            String[] splitTime;
            splitTime = newDateTime[1].split(":");
            LocalTime addTime = LocalTime.of( //addTime armazena a hora do nó a ser adicionado
                    Integer.parseInt(splitTime[0]),
                    Integer.parseInt(splitTime[1]),
                    0
            );
            splitTime = dateTime[1].split(":");
            LocalTime pointerTime = LocalTime.of( //pointerTime aponta para a hora do nó atual
                    Integer.parseInt(splitTime[0]),
                    Integer.parseInt(splitTime[1]),
                    0
            );
            check = !addTime.isAfter(pointerTime);
            Log.d("TAG Data three", String.valueOf(check));
        }

        // check = True vai para direita oque significa que valor é maior
        // check = False vai para esquerda oque significa que valor é menor
        if (check) {
            if (right != null) right.add(idTask, newDateTime);
            else right = new TaskNode(idTask, newDateTime);
        } else {
            if (left != null) left.add(idTask, newDateTime);
            else left = new TaskNode(idTask, newDateTime);
        }
    }

    public ArrayList<Integer> sort(){
        ArrayList<Integer> sortedList = new ArrayList<>();
        if (right != null){sortedList.addAll(right.sort());}
        sortedList.add(idTask);
        if (left != null){sortedList.addAll(left.sort());}
        return sortedList;
    }
}
