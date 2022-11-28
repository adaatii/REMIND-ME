package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.agenda_pessoal.Controller_and_Model.Data;
import com.example.agenda_pessoal.Controller_and_Model.User;
import com.example.agenda_pessoal.Sort_and_Adapters.Constants;
import com.example.agenda_pessoal.R;

public class A_Login extends AppCompatActivity implements Constants {
    Data dataInstance;
    EditText id_login_email;
    EditText id_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataInstance = getIntent().getExtras().getParcelable("Data");
        Log.d("OpenSerialize" , dataInstance.serialize());
        // Importantes
        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        id_login_email = findViewById(R.id.et_login_email);
        id_login_password = findViewById(R.id.et_login_password);

    }

    public void alert(String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("OK", null);
        alertDialog.show();
    }

    public void openEvent(View v){
        String log_email = id_login_email.getText().toString();
        String log_password = id_login_password.getText().toString();

        User loginUser = new User();

        if (loginUser.authenticateEmail(log_email)){
            boolean emailIsUsed = false;
            int currentUser = 0;
            for (int i = 0; i < dataInstance.getDataUser().size(); i++) {
                if (dataInstance.getDataUser().get(i).getEmail().equals(log_email)) {
                    emailIsUsed = true;
                    currentUser = i;
                }
            }

            boolean currect;
            currect = emailIsUsed && loginUser.validatePassword(dataInstance.getDataUser().get(currentUser).getPassword(), log_password);
            if (currect) {
                dataInstance.log = currentUser;
                Intent it_aEvent = new Intent(this, A_Event.class);
                it_aEvent.putExtra("Data", dataInstance);
                startActivityForResult(it_aEvent, EVENT_ACTIVITY_REQUEST_CODE);

            } else {
                // Esse email ou senha incorretos
                alert("Login", "Email ou senha incorretos!");
                Log.d("OperLog" , "Email ou senha incorretos");
                Log.d("OperSerialize" , dataInstance.serialize());
            }


        }else{
            // email invalido
            alert("Login", "Email inválido!");
            Log.d("OperLog" , "Email invalido");
            Log.d("OperSerialize" , dataInstance.serialize());
        }

    }

    public void returnToHome(View v){
        Intent it_aHome = new Intent();
        it_aHome.putExtra("Data", dataInstance);
        setResult(RESULT_DESTROY, it_aHome);
        finish();
    }

    public void openRegister(View v){
        Intent it_aHome = new Intent();
        it_aHome.putExtra("Data", dataInstance);
        setResult(RESULT_OK, it_aHome);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode", Integer.toString(resultCode));
        if (requestCode == EVENT_ACTIVITY_REQUEST_CODE){
            if (resultCode == RESULT_FIRST_USER){
                Data dataSerialize = data.getExtras().getParcelable("Data");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("Data"));
            }else if(resultCode == RESULT_OK){
                Data dataSerialize = data.getExtras().getParcelable("Data");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("Data"));
                id_login_email.setText("");
                id_login_password.setText("");
            }
        }
    }
}