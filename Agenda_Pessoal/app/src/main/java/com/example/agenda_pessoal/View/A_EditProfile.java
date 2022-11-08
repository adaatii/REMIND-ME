package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.Controller.User;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.R;

public class A_EditProfile extends AppCompatActivity implements Constants {
    Data dataInstance;
    EditText et_name_edit_profile, et_email_edit_profile, et_phone_edit_profile, et_password_edit_profile, et_confPassword_edit_profile;
    String nome, email, telefone, password, confPassword;
    String lastChar = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        dataInstance = getIntent().getExtras().getParcelable("Data");
        Log.d("OpenSerialize", dataInstance.serialize());
        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        et_name_edit_profile = findViewById(R.id.et_name_edit_profile);
        et_email_edit_profile = findViewById(R.id.et_email_edit_profile);
        et_phone_edit_profile = findViewById(R.id.et_phone_edit_profile);
        et_password_edit_profile = findViewById(R.id.et_password_edit_profile);
        et_confPassword_edit_profile = findViewById(R.id.et_confPassword_edit_profile);
        setInfoUser();
        et_phone_edit_profile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Integer lenEditPhone = et_phone_edit_profile.getText().toString().length();
                if (lenEditPhone > 1) {
                    lastChar = et_phone_edit_profile.getText().toString().substring(lenEditPhone - 1);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Integer lenEditPhone = et_phone_edit_profile.getText().toString().length();
                if (lenEditPhone == 2) {
                    if (!lastChar.equals(" ")) {
                        et_phone_edit_profile.append(" ");
                    } else {
                        et_phone_edit_profile.getText().delete(lenEditPhone - 1, lenEditPhone);
                    }
                } else if (lenEditPhone == 8) {
                    if (!lastChar.equals("-")) {
                        et_phone_edit_profile.append("-");
                    } else {
                        et_phone_edit_profile.getText().delete(lenEditPhone - 1, lenEditPhone);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void alert(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("OK", null);
        alertDialog.show();

    }

    private void setInfoUser() {
        User user = dataInstance.getDataUser().get(dataInstance.log);
        et_name_edit_profile.setText(user.name);
        et_email_edit_profile.setText(user.getEmail());
        et_phone_edit_profile.setText(user.phone);
        et_password_edit_profile.setText(user.getPassword());
        et_confPassword_edit_profile.setText(user.getPassword());
    }

    public void editUser(View view) {
        nome = et_name_edit_profile.getText().toString();
        email = et_email_edit_profile.getText().toString();
        telefone = et_phone_edit_profile.getText().toString();
        password = et_password_edit_profile.getText().toString();
        confPassword = et_confPassword_edit_profile.getText().toString();

        User user = new User();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()
                || password.isEmpty() || confPassword.isEmpty()) {
            alert("Campos Obrigatórios", "Preencha todos os campos Obrigatórios");
        } else {
            if (user.authenticateEmail(email)) {
                boolean emailUsed = false;
                for (int i = 0; i < dataInstance.getDataUser().size(); i++) {
                    if (dataInstance.getDataUser().get(i).getEmail().equals(email) && !dataInstance.getDataUser().get(dataInstance.log).getEmail().equals(email)) {
                        emailUsed = true;
                    }
                }
                if (!emailUsed) {
                    if (user.validatePassword(password, confPassword)) {
                        dataInstance.getDataUser().get(dataInstance.log).name = nome;
                        dataInstance.getDataUser().get(dataInstance.log).setEmail(email);
                        dataInstance.getDataUser().get(dataInstance.log).phone = telefone;
                        dataInstance.getDataUser().get(dataInstance.log).setPassword(password);

                        Intent it_aProfile = new Intent();
                        it_aProfile.putExtra("EditedUser", dataInstance);
                        setResult(RESULT_FIRST_USER, it_aProfile);
                        finish();
                    } else {
                        alert("Edição", "Senha e confirmação de senha não conferem");
                    }
                } else {
                    alert("Edição", "Email já possui cadastro");
                }
            } else {
                alert("Edição", "Email Inválido");
            }
        }
    }

    public void returnToProfile(View v) {
        Intent it_aProfile = new Intent();
        setResult(RESULT_DESTROY, it_aProfile);
        finish();
    }
}