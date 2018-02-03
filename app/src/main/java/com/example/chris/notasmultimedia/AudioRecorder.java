package com.example.chris.notasmultimedia;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by chris on 26/11/2017.
 */

public class AudioRecorder extends Activity implements MediaPlayer.OnCompletionListener{
    MediaRecorder recorder;
    MediaPlayer player;
    File archivo;
    Button btn1,btn2,btn3,btn4;
    TextView tv2;
    DaoNota daoNota;
    DaoMultimedia daoMultimedia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio);
        tv2=findViewById(R.id.tv1);
        btn1=findViewById(R.id.btnGrabar);
        btn2=findViewById(R.id.btnDetener);
        btn3=findViewById(R.id.btnReproducir);
        btn4=findViewById(R.id.btnGuardarAudio);
        daoNota=new DaoNota(getApplication());
        daoMultimedia=new DaoMultimedia(getApplication());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grabar(view);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detener(view);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(view);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tv2.equals("Estado")){
                    Nota objNota=new Nota();
                    objNota.setTitulo("EY");
                    objNota.setFecha("21/11/2017");
                    objNota.setDescripcion("Esto es una prueba");

                    Multimedia objMulti=new Multimedia();
                    objMulti.setTipo(3);
                    objMulti.setUri(archivo.toString());
                    objMulti.setIdNota(daoNota.recuperarId());

                    Toast.makeText(getApplication(),"LONG: "+daoNota.insert(objNota),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplication(),"ID: "+daoNota.recuperarId(),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplication(),"MULTIMEDIA: "+daoMultimedia.insert(objMulti),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplication(),"URI : "+archivo,Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(getApplication(),"Introduce un audio",Toast.LENGTH_LONG).show();
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.lactivity_main, menu);
        return true;
        return super.onCreateOptionsMenu(menu);

    }*/

    public void grabar(View v) {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File path = new File(Environment.getExternalStorageDirectory()
                .getPath());
        try {
            archivo = File.createTempFile("temporal", ".3gp", path);

        } catch (IOException e) {
        }
        recorder.setOutputFile(archivo.getAbsolutePath());
        try {
            recorder.prepare();
        } catch (IOException e) {
        }
        recorder.start();
        tv2.setText("Grabando");
        btn1.setEnabled(false);
        btn2.setEnabled(true);
    }

    public void detener(View v) {
        recorder.stop();
        recorder.release();
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        try {
            player.setDataSource(archivo.getAbsolutePath());
        } catch (IOException e) {
        }
        try {
            player.prepare();
        } catch (IOException e) {
        }
        btn1.setEnabled(true);
        btn2.setEnabled(false);
        btn3.setEnabled(true);
        tv2.setText("Listo para reproducir");
    }

    public void reproducir(View v) {
        player.start();
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        tv2.setText("Reproduciendo");
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        tv2.setText("Listo");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
