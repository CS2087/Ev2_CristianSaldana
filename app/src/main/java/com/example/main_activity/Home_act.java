package com.example.main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import Clases.ListPrestamos;
import Clases.ListClientes;

public class Home_act extends AppCompatActivity {

    private ViewFlipper vf; // para mi slider
    private int [] images = {R.drawable.a, R.drawable.b, R.drawable.c}; //cargo imagenes del drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_act);
        getSupportActionBar().hide(); //oculto la action bar

        vf = (ViewFlipper)findViewById(R.id.slider);

        //Ahora itero la funcion del slider
        for(int i=0; i< images.length; i++)
        {
            flip_image(images[i]);
        }
    }

    //configuracion del slider
    public void flip_image(int img)
    {
        ImageView  view = new ImageView(this);
        view.setBackgroundResource(img);

        vf.addView(view);           //añado las imagenes del viewflipper
        vf.setFlipInterval(2300);   //su desplazamiento sera de 2300 mms
        vf.setAutoStart(true);      //inicar de forma auto...

        //sentido al slider
        vf.setInAnimation(this, android.R.anim.slide_in_left);
        vf.setOutAnimation(this, android.R.anim.slide_out_right);
    } //fin de slider



    //intent Cliente
    public void cClientes(View v)
    {
        Intent i = new Intent(this, Clientes_act.class);
        startActivity(i);
    }



    //Metodo Créditos/prestamos
    public void cCreditos(View v){
        //declaro un arreglo para tener varios datos //envio un arreglo de tipo string con 2 clientes
        ArrayList<ListClientes> clientes = new ArrayList<ListClientes>();
        ArrayList<ListPrestamos> prestamos = new ArrayList<ListPrestamos>();

        //agrego los clientes con el dinero
        clientes.add(new ListClientes("Axel", 750000));
        clientes.add(new ListClientes("Roxana", 900000));
        //Aqui agegue los 2 clientes adicionales.
        clientes.add(new ListClientes("Betzabe",280000));
        clientes.add(new ListClientes("Matias",350000));


        //aquí los 2 tipos de créditos, automotriz e hipotecario
        prestamos.add(new ListPrestamos("Credito Hipotecario", 1000000, 12));
        prestamos.add(new ListPrestamos("Credito Automotriz", 500000, 8));

        Intent i = new Intent(this, Prestamos_act.class);
        i.putExtra("listaClientes", clientes); //preparo el dato para ser enviado
        i.putExtra("listaCreditos", prestamos); //preparo el dato para ser enviado
        startActivity(i);
    }



    //Intent Seguridad
    public void cSeguridad(View v)
    {
        Intent i = new Intent(this, Seguridad_act.class);
        startActivity(i);
    }


    //Intent Info
    public void cInformacion(View v)
    {
        Intent i = new Intent(this, Info_act.class);
        startActivity(i);
    }




}