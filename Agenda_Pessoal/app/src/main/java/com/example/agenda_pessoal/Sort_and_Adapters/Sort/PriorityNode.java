package com.example.agenda_pessoal.Sort_and_Adapters.Sort;

import java.util.ArrayList;

public class PriorityNode {
    private final Integer idTask;
    private final int priority;
    private PriorityNode left, right;

    public PriorityNode(int idTask, int priority) {
        this.idTask = idTask;
        this.priority = priority;
        left = right = null;
    }

    public void add(int idTask, int newPriority) {
        boolean check = priority > newPriority;

        // check = True vai para direita oque significa que valor é maior
        // check = False vai para esquerda oque significa que valor é menor
        if (check) {
            if (right != null) right.add(idTask, newPriority);
            else right = new PriorityNode(idTask, newPriority);
        } else {
            if (left != null) left.add(idTask, newPriority);
            else left = new PriorityNode(idTask, newPriority);
        }
    }

    public ArrayList<Integer> sort(){
        ArrayList<Integer> sortedList = new ArrayList<>();
        if (right != null){sortedList.addAll(right.sort());}
        sortedList.add(idTask);
        if (left != null){sortedList.addAll(left.sort());}
        return sortedList;
    }

    public ArrayList<Integer> sortLowPriority(){
        ArrayList<Integer> sortedList = new ArrayList<>();
        if (left != null){sortedList.addAll(left.sort());}
        sortedList.add(idTask);
        if (right != null){sortedList.addAll(right.sort());}
        return sortedList;
    }
}
