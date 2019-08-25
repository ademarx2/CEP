package com.rameda.cep;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rameda.cep.Classes.CEP;
import com.rameda.cep.Classes.HttpService;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Button btnMainbuscarCep;
    private TextView resposta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resposta = findViewById(R.id.etMain_resposta);
        final EditText cep = findViewById(R.id.etMain_cep);

        btnMainbuscarCep = findViewById(R.id.btnMainbuscarCep);
        btnMainbuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CEP retorno = null;
                try {
                    retorno = new HttpService(cep.getText().toString()).execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    resposta.setText(retorno.toString());
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"PREENCHA OS DADOS CORRETAMENTE",Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}
