package com.example.agenda_pessoal.Model.Sort;

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
        LocalDate firstDate = LocalDate.of( //FirstDate armazena a data do nó a ser adicionado
                Integer.parseInt(splitDate[2]),
                Integer.parseInt(splitDate[1]),
                Integer.parseInt(splitDate[0])
        );
        splitDate = dateTime[0].split("/");
        LocalDate secondDate = LocalDate.of( //SecondDate armazena a data do nó atual
                Integer.parseInt(splitDate[2]),
                Integer.parseInt(splitDate[1]),
                Integer.parseInt(splitDate[0])
        );
        if (firstDate.isAfter(secondDate)) {
            check = true;
        } else if (firstDate.isBefore(secondDate)) {
            check = false;
        } else {
            String[] splitTime;
            splitTime = newDateTime[1].split(":");
            LocalTime firstTime = LocalTime.of( //FirstTime armazena a hora do nó a ser adicionado
                    Integer.parseInt(splitTime[0]),
                    Integer.parseInt(splitTime[1]),
                    0
            );
            splitTime = dateTime[1].split(":");
            LocalTime secondTime = LocalTime.of( //SecondTime armazena a hora do nó atual
                    Integer.parseInt(splitTime[0]),
                    Integer.parseInt(splitTime[1]),
                    0

            );
            check = firstTime.isAfter(secondTime);
        }

        // check = True vai para direita oque significa que valor é maior
        // check = False vai para esquerda oque significa que valor é menor
        if (check) {
            if (right != null) right.add(idTask, dateTime);
            else right = new TaskNode(idTask, dateTime);
        } else {
            if (left != null) left.add(idTask, dateTime);
            else left = new TaskNode(idTask, dateTime);
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
