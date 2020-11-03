package com.example.main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Seguridad_act extends AppCompatActivity {

    private EditText edit;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad_act);
        getSupportActionBar().hide(); //oculto la action bar
        edit = (EditText)findViewById(R.id.et);
        tv = (TextView) findViewById(R.id.tv);
    }

    //METODO - vamosa generar una key o una llave
    //el parametro que recibe es el password
    private SecretKeySpec generateKey(String password)throws Exception{
        //obtnego la instancia
        MessageDigest sha = MessageDigest.getInstance("SHA-256"); //firma hmac para verficiar la integridad de los datos
        //genero una cadena de byte
        byte[] key = password.getBytes("UTF-8"); //el estandar va a ser bajo utf-8
        key = sha.digest(key); // pasamnos la firma hmac a nuestra cadena de byte
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES"); //y genero un objeto pa pasar a key AES

        return secretKey;
    }

    // MeTODO - vamos a encriptar bajo el algoritmo AES
    public String Encriptar(String datos, String passwword) throws Exception{ //recibo el string dato, recibe el password,
        if(!edit.getText().toString().isEmpty())
        {
            //hago el encriptado de datos //secretkey permite generar contraseñas
            SecretKeySpec secretKey = generateKey(passwword);
            Cipher cipher = Cipher.getInstance("AES"); //obtengo el algoritmo AES
            cipher.init(Cipher.ENCRYPT_MODE, secretKey); //Hago el encriptado bajo CIPHER.

            byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());
            String datosEncriptadosString = Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
            return datosEncriptadosString;
        }

        else
        {
            Toast.makeText(this, "Debe ingresar una clave", Toast.LENGTH_LONG).show();
            return null;
        }

    }

    //funcion Encriptar.

    public void Encriptar(View v)
    {
        try {
            String mensaje = Encriptar(edit.getText().toString(), tv.getText().toString());
            tv.setText(mensaje);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}