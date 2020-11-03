package com.example.main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etnom, etpass;
    private ProgressBar pbar;
    private Button btnIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //oculto la action bar

        etnom = (EditText)findViewById(R.id.etNom);
        etpass = (EditText)findViewById(R.id.etPass);
        btnIn=(Button)findViewById(R.id.bt1);
        pbar=(ProgressBar)findViewById(R.id.pb);
        pbar.setVisibility(View.INVISIBLE); // se hace invisible mi ProgressBar


        //llamo a mi listener - boton iniciar sesion
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etnom.getText().toString().equalsIgnoreCase("android") && etpass.getText().toString().equals("123")){
                    Toast.makeText(MainActivity.this, "Ingreso Correctamente", Toast.LENGTH_SHORT).show();
                    new Task().execute();
                }else {
                    Toast.makeText(MainActivity.this, "Nombre y Password incorrectas!", Toast.LENGTH_SHORT).show();
                    etnom.setText("");
                    etpass.setText("");
                }
            }
        });

    }

    //Implemento el AsyncTask.
    class Task extends AsyncTask<String, Void, String> {

        @Override //Creo el proceso inicial del AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            pbar.setVisibility(View.VISIBLE);
        }

        @Override // aplico mi tarea pesada de carga
        protected String doInBackground(String... strings) {
            //Implemento un tiempo para que la progressbar cargue 300 ms.
            for (int i = 1; i<=10; i++)
            {
                try { Thread.sleep(200); }
                catch (Exception e)
                { e.printStackTrace(); }
            }
            return null;
        }

        @Override //Final del AsyncTask
        protected void onPostExecute(String s) {
            pbar.setVisibility(View.INVISIBLE);

            Intent i = new Intent(getBaseContext(), Home_act.class);
            startActivity(i);
        }
    }

}