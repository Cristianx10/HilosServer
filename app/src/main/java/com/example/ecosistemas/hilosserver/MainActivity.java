package com.example.ecosistemas.hilosserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements Receptor.OnMessage {

    Button b;
    public Cliente c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.btn_enviar);

        c = new Cliente(this);
        c.start();


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.enviar();
            }
        });
    }

    @Override
    public void onReceived(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
