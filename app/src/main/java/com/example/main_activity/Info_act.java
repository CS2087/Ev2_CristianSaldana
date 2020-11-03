package com.example.main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class Info_act extends AppCompatActivity {

    private VideoView videoVi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_act);
        getSupportActionBar().hide();

        videoVi = (VideoView)findViewById(R.id.video);  //Llamo el elemento videoview por su id

        //Asignar nuestro video mp4 videoview a traves de su ruta
        String ruta = "android.resource://" + getPackageName() + "/" + R.raw.video; // Obtener la ruta
        Uri uri = Uri.parse(ruta); //parseo la ruta
        videoVi.setVideoURI(uri); //aqui lo contendre en videoview

        //Controles para el video
        MediaController media = new MediaController(this);
        videoVi.setMediaController(media);
    }

    public void maps (View v){
        Intent map = new Intent(this, Maps_act.class);
        startActivity(map);
    }

}