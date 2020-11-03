package com.example.main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import Clases.ListClientes;
import Clases.ListPrestamos;

public class Prestamos_act extends AppCompatActivity {

    private Spinner spin1, spin2;
    private TextView textv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos_act);
        getSupportActionBar().hide();

        spin1 = (Spinner)findViewById(R.id.spinClientes);
        spin2 = (Spinner)findViewById(R.id.spinCreditos);
        textv = (TextView)findViewById(R.id.tv2);

        //recibo el arreglo los datos de la lista                                       //recibo la referencia de Menu i.putExtra lista...
        ArrayList<ListClientes> listaClientes = (ArrayList<ListClientes>) getIntent().getSerializableExtra("listaClientes");
        ArrayList<ListPrestamos> listaCreditos = (ArrayList<ListPrestamos>) getIntent().getSerializableExtra("listaCreditos");

        //los spinner trabajan con el arrayadapter esto hace que se rellene con los datos
        ArrayList<String> listaNombres = new ArrayList<String>();
        ArrayList<String> listaPrestamos = new ArrayList<String>();

        for (ListClientes clit:listaClientes ){
            listaNombres.add(clit.getNombre());
        }

        for (ListPrestamos ppres: listaCreditos){
            listaPrestamos.add(ppres.getNombrePrestamo());
        }

        ArrayAdapter<String> adaptCli = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaNombres);
        ArrayAdapter<String> adaptPrest = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPrestamos);

        spin1.setAdapter(adaptCli);
        spin2.setAdapter(adaptPrest);
    }

    public void CalcularPrestamo(View v) {
        ArrayList<Integer> datos = calcular();
        textv.setText(datos.get(0) +"");
    }

    public void CalcularDeuda(View v) {
        ArrayList<Integer> datos = calcular();
        textv.setText(datos.get(0)/datos.get(1) +"");

    }

    //calculo los valores para los distintos clientes
    private ArrayList<Integer> calcular() {
        String cliente = spin1.getSelectedItem().toString();
        String credito = spin2.getSelectedItem().toString();

        //llamo a mi clase listClientes
        ListClientes selcte = new ListClientes ("", 0);
        ListPrestamos selpre = new ListPrestamos("", 0, 0);

        for (ListClientes clit: (ArrayList<ListClientes>) getIntent().getSerializableExtra("listaClientes")){
            if (cliente.equals(clit.getNombre())){
                selcte = clit;
            }
        }

        for (ListPrestamos prest: (ArrayList<ListPrestamos>) getIntent().getSerializableExtra("listaCreditos")){
            if (credito.equals(prest.getNombrePrestamo())){
                selpre = prest;
            }
        }

        int result = selcte.getDinero() + selpre.getMontoPrestamo();

        ArrayList<Integer>datos = new ArrayList<>();
        datos.add(result);
        datos.add(selpre.getCuotas());

        return datos;
    }



}

