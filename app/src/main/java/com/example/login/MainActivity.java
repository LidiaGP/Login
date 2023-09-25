package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edt_nick,edt_clave;
    CheckBox ckb_guardar;
    Button b_entrar;

    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_nick=findViewById(R.id.edt_nick);
        edt_clave=findViewById(R.id.edt_clave);
        ckb_guardar=findViewById(R.id.ckb_guardar);
        b_entrar=findViewById(R.id.b_entrar);

        prefs=getSharedPreferences("login.xml",MODE_PRIVATE);
        edt_nick.setText(prefs.getString("nick",""));
        ckb_guardar.setChecked(prefs.getBoolean("guardar",false));

        b_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });



    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = prefs.edit();

        if (ckb_guardar.isChecked()) {
            editor.putString("nick", edt_nick.getText().toString());
            editor.putBoolean("guardar", true);
        } else {
            editor.remove("nick");
            editor.putBoolean("guardar", false);
        }

        editor.commit();

    }
}