package com.example.chris.notasmultimedia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Invitado on 28/10/2017.
 */

public class Activity_Imagen extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    ImageView foto_gallery;
    Button btnAgregarI;
    DaoNota daoNota;
    DaoMultimedia daoMultimedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);
        btnAgregarI=(Button) findViewById(R.id.btnAgregarImagen);
        daoNota=new DaoNota(getApplication());
        daoMultimedia=new DaoMultimedia(getApplication());

        foto_gallery = (ImageView) findViewById(R.id.imageView);
        foto_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        btnAgregarI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nota objNota=new Nota();
                objNota.setTitulo("EY");
                objNota.setFecha("21/11/2017");
                objNota.setDescripcion("Esto es una prueba");

                Multimedia objMulti=new Multimedia();
                objMulti.setTipo(1);
                objMulti.setUri(imageUri.toString());
                objMulti.setIdNota(daoNota.recuperarId());

                Toast.makeText(getApplication(),"LONG: "+daoNota.insert(objNota),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplication(),"ID: "+daoNota.recuperarId(),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplication(),"MULTIMEDIA: "+daoMultimedia.insert(objMulti),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplication(),"URI : "+imageUri,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);

        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            foto_gallery.setImageURI(imageUri);
        }
    }

    public void btnVideo_click(View v){
        startActivity( new Intent(this,Activity_video.class));
    }
}
