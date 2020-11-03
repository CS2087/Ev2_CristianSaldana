package Clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    //Constructor de mi BBDD
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    //Metodos implementados,
    //onCreate me permite definir la config. inicial de mi BBDD
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {        //creo mi tabla clientes (codigo nombre salario).
        sqLiteDatabase.execSQL("CREATE TABLE clientes(codigo int primary key, nombre text, salario float)");
    }



    //onUpgrade Aqui realizó cambios esquematicos en mi modelo
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
