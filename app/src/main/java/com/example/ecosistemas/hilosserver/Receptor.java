package com.example.ecosistemas.hilosserver;


import android.accessibilityservice.AccessibilityService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Receptor extends Thread{
    Socket socket;

    //Paso 2
    OnMessage observer;

    public Receptor(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {

            InputStream is = socket.getInputStream();
            BufferedReader reader = new BufferedReader( new InputStreamReader(is) );

            while(true){

                String line = reader.readLine();
                System.out.println(line);

                //Paso 4: Solo funciona cuando no es nulo
                observer.onReceived(line);

        }
        } catch (IOException e) {

            e.printStackTrace();
        }
        //Siempre quiero que este en funcionamiento

    }

    //Observable
    //Paso 1
    public interface OnMessage{
        public void onReceived(String msg);
    }

    //Paso 3
    public void setObserver(OnMessage observer){
        this.observer = observer;
    }

}
