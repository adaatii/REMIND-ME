package com.example.agenda_pessoal.View;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.R;

public class A_Profile extends Activity {
    Data dataInstance;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        dataInstance = getIntent().getExtras().getParcelable("Data");
        Log.d("OpenSerialize", dataInstance.serialize());
    }
}
