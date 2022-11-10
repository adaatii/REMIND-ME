package com.example.agenda_pessoal.Model;


import com.example.agenda_pessoal.R;

public interface Constants {
    //DON'T CREATE A CONSTANT WITH THE SAME NUMBER
    //REQUESTS
    int LOGIN_ACTIVITY_REQUEST_CODE = 0;
    int REGISTER_ACTIVITY_REQUEST_CODE = 1;
    int EVENT_ACTIVITY_REQUEST_CODE = 2;
    int NEW_EVENT_ACTIVITY_REQUEST_CODE = 3;
    int PROFILE_ACTIVITY_REQUEST_CODE = 4;
    int TASK_ACTIVITY_REQUEST_CODE = 5;
    int VIEW_EVENT_ACTIVITY_REQUEST_CODE = 6;
    int EDIT_EVENT_ACTIVITY_REQUEST_CODE = 7;
    int NEW_TASK_ACTIVITY_REQUEST_CODE = 8;
    int VIEW_TASK_ACTIVITY_REQUEST_CODE = 9;
    int EDIT_TASK_ACTIVITY_REQUEST_CODE = 10;
    int EDIT_PROFILE_ACTIVITY_REQUEST_CODE = 11;

    //RESULTS
    int RESULT_DESTROY = 2;

    //PRIORITY
    int[][] PRIORITY_LIST = new int[][]{
            new int[]{R.drawable.ic_hight_priority, R.color.high_priority},
            new int[]{R.drawable.ic_medium_priority, R.color.medium_priority},
            new int[]{R.drawable.ic_low_priority, R.color.low_priority},
            new int[]{R.drawable.ic_clock, R.color.default_priority},
            new int[]{R.drawable.ic_baseline_task_alt_24, R.color.finished_task}

    };

}

