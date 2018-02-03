package com.example.chris.notasmultimedia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by Invitado on 28/10/2017.
 */

public class Activity_video extends AppCompatActivity {

    private static final int PICK_VIDEO = 100;
    Uri videoUri;
    Button btnGale,btnGuardar;
    VideoView video_gallery;
    DaoNota daoNota;
    DaoMultimedia daoMultimedia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        video_gallery=(VideoView)findViewById(R.id.videoView);
        btnGale=(Button)findViewById(R.id.btnGaleria);
        btnGuardar=(Button) findViewById(R.id.btnGuardarVideo);
        daoNota=new DaoNota(getApplication());
        daoMultimedia=new DaoMultimedia(getApplication());
        btnGale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!video_gallery.isPlaying()){
                    Toast.makeText(getApplication(),"Selecciona un video",Toast.LENGTH_LONG).show();
                }else{
                    Nota objNota=new Nota();
                    objNota.setTitulo("EYYYY");
                    objNota.setFecha("26/11/2017");
                    objNota.setDescripcion("Esto es una prueba");

                    Multimedia objMulti=new Multimedia();
                    objMulti.setTipo(2);
                    objMulti.setUri(videoUri.toString());
                    objMulti.setIdNota(daoNota.recuperarId());

                    Toast.makeText(getApplication(),"LONG: "+daoNota.insert(objNota),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplication(),"ID: "+daoNota.recuperarId(),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplication(),"MULTIMEDIA: "+daoMultimedia.insert(objMulti),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplication(),"URI : "+videoUri,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
        gallery.setType("video/*");
        startActivityForResult(gallery, PICK_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == PICK_VIDEO){
            videoUri = data.getData();
            video_gallery.setMediaController(new MediaController(this));
            video_gallery.setVideoURI(videoUri);
            video_gallery.requestFocus();
            video_gallery.start();
        }
    }
}
