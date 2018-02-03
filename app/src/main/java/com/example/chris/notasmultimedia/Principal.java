package com.example.chris.notasmultimedia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by chris on 21/11/2017.
 */

public class Principal extends AppCompatActivity {

    Button btnAgregarNN;
    ListView lstN;
    ArrayList <Nota> notas;
    ArrayList <Multimedia> multimedia;
    DaoNota daoNota;
    DaoMultimedia daoMultimedia;
    ImageView foto_gallery;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

        btnAgregarNN = (Button) findViewById(R.id.btnAgregarNota);
        lstN= (ListView) findViewById(R.id.lstNotas);
        daoNota=new DaoNota(this);
        daoMultimedia=new DaoMultimedia(this);
        //foto_gallery = (ImageView) findViewById(R.id.ivDB);
        registerForContextMenu(lstN);
        mostrar();
    }

    public void mostrar(){
        multimedia=daoMultimedia.getAll();
        ArrayAdapter<Multimedia> adp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,multimedia);
        lstN.setAdapter(adp);

        /*imageUri = Uri.parse(multimedia.get(1).getUri());
        foto_gallery.setImageURI(imageUri);
        Toast.makeText(getApplication(),"URI: "+imageUri,Toast.LENGTH_LONG).show();*/
    }

    public void btnAbrirNotas_click(View view){
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

        menu.setHeaderTitle(lstN.getAdapter().getItem(info.position).toString());
        inflater.inflate(R.menu.menu, menu);
    }
}
