package com.example.main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Clases.AdminSQLiteOpenHelper;

public class Clientes_act extends AppCompatActivity {

    //creo mis variables a a utilizar
    private EditText etcodigo, etnombre, etsalario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_act);
        getSupportActionBar().hide(); //oculto la action bar

        //rescato el id de los editText de mi entorno Grafico
        etcodigo = (EditText)findViewById(R.id.edit_codigo);
        etnombre = (EditText)findViewById(R.id.edit_nombre);
        etsalario = (EditText)findViewById(R.id.edit_salario);
    }



    //guardo el cliente
    public void guardarCliente(View v){
                                            //traigo el constructor de la bbdd   //mi BBDD es fichero y su version 1
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //permito sobreescribir en mi base de datos.

        //incluyo un validador
        if (!etcodigo.getText().toString().isEmpty())
        {           //creo un objeto de ontenvalues
            ContentValues registro = new ContentValues();

            registro.put("codigo", etcodigo.getText().toString());
            registro.put("nombre", etnombre.getText().toString());
            registro.put("salario", Integer.parseInt(etsalario.getText().toString()));

            //guardo dentro de la tabla Clientes, el registro que contiene los cambios
            bd.insert("clientes", null, registro);
            bd.close(); //cierro la bbdd.

            //muestro el mensaje
            Toast.makeText(this, "El Cliente ha sido guardado!", Toast.LENGTH_LONG).show();
            // añade al cliente
        }
        else
        {
            Toast.makeText(this, "Debe ingresar un codigo ", Toast.LENGTH_LONG).show();
        }

        //una vez guardado limpio los datos
        limpiar();
    }



    //metodo para mostrar cliente ingresado, consultado a traves del codigo (id)
    public void mostrarCliente(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = etcodigo.getText().toString();

        if (!codigo.isEmpty()){

            Cursor fila = bd.rawQuery("SELECT nombre, salario FROM clientes WHERE codigo =" + codigo, null);

            if (fila.moveToFirst()){
                etnombre.setText(fila.getString(0));
                etsalario.setText(fila.getString(1));
            }else{
                Toast.makeText(this, "Código Cliente no existe", Toast.LENGTH_SHORT).show();
                limpiar();
            }
        }
        else{
            Toast.makeText(this, "Favor de agregar código", Toast.LENGTH_SHORT).show();
        }

    }



    //metodo para eliminar cliente
    public void eliminarCliente(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = etcodigo.getText().toString();

        bd.delete("clientes", "codigo="+codigo, null);
        bd.close();

        Toast.makeText(this, "Cliente Eliminado", Toast.LENGTH_SHORT).show();

        limpiar(); //limpio los datos al eliminar.

    }



    //metodo para actulizar cliente
    public void actualizarCliente(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = etcodigo.getText().toString();

        if(!codigo.isEmpty()){

            ContentValues cont = new ContentValues();
            cont.put("codigo", etcodigo.getText().toString());
            cont.put("nombre", etnombre.getText().toString());
            cont.put("salario", etsalario.getText().toString());

            bd.update("clientes", cont, "codigo="+codigo, null);
            Toast.makeText(this, "Cliente Actualizado", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Cliente No existe", Toast.LENGTH_SHORT).show();
        }

        limpiar();  //limpio los datos al actualizar el cliente.
    }



    //Metodo para limpiar los campos de código nombre y salario
    public void limpiar()
    {
         etcodigo.setText("");
         etnombre.setText("");
         etsalario.setText("");
    }


}