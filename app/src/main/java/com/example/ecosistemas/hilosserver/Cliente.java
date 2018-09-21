package com.example.ecosistemas.hilosserver;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends Thread {

    String mensaje;

    Socket s;
    Receptor r;

    MainActivity activity;

    public Cliente(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public void run() {
        try {
            s = new Socket("10.0.2.2", 6000);
            r = new Receptor(s);
            r.setObserver(activity);
            r.start();
            Log.e("DEBUG","Conexion exitorsa");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviar(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //Este codigo se ejecuta en paralelo
                try {

                    OutputStream os = s.getOutputStream();
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(os));
                    out.println("mensaje");
                    out.flush();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
