package com.example.agenda_pessoal.Model.Sort;

import android.util.Log;

import java.time.LocalDate;
import java.util.ArrayList;

public class PriorityTree {
    private PriorityNode root;

    public PriorityTree() {
        root = null;
    }

    public void add(int idTask, int newPriority) {
            if (root == null) {
                root = new PriorityNode(idTask, newPriority);
            } else {
                root.add(idTask, newPriority);
            }
    }

    public ArrayList<Integer> sort() {
        ArrayList<Integer> sortedList = new ArrayList<>();
        if (root != null) {
            sortedList.addAll(root.sort());
        }
        return sortedList;
    }

    public ArrayList<Integer> sortLowPriority(){
        ArrayList<Integer> sortedList = new ArrayList<>();
        if (root != null) {
            sortedList.addAll(root.sortLowPriority());
        }
        return sortedList;
    }
}
