package com.example.clase06.ViewModels;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContadorViewModel extends ViewModel {

    private MutableLiveData<Integer> contador = new MutableLiveData<>(1);

    private MutableLiveData<Pair<Boolean,Integer>> contador2 = new MutableLiveData<>(new Pair<Boolean, Integer>(true,1));
    private MutableLiveData<String> nombre = new MutableLiveData<>("Juan");

    private Thread thread = null;

    public void iniciarContador() {

        if (thread == null) {

            thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    int contadorLocal = contador.getValue();

                    for (; contadorLocal <= 20; contadorLocal++) {
                        Log.d("contadorApp", String.valueOf(contadorLocal));

                        contador.postValue(contadorLocal);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    thread = null;
                    if(contadorLocal == 21) {
                        contador.postValue(contadorLocal);
                    }
                }
            });

            thread.start();
        }

    }

    public void iniciarContador2() {

        if (thread == null) {

            thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    Pair<Boolean,Integer> valorContador = contador2.getValue();
                    int contadorLocal = valorContador.second;

                    for (; contadorLocal <= 20; contadorLocal++) {
                        Log.d("contadorApp", String.valueOf(contadorLocal));

                        contador2.postValue(new Pair<Boolean, Integer>(true,contadorLocal));

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    thread = null;
                    if(contadorLocal == 21) {
                        contador2.postValue(new Pair<Boolean, Integer>(false,1));
                    }
                }
            });

            thread.start();
        }

    }

    public void detenerContador(){
        if(thread != null){
            thread.interrupt();
        }
    }

    public MutableLiveData<Integer> getContador() {
        return contador;
    }

    public void setContador(MutableLiveData<Integer> contador) {
        this.contador = contador;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public MutableLiveData<String> getNombre() {
        return nombre;
    }

    public void setNombre(MutableLiveData<String> nombre) {
        this.nombre = nombre;
    }

    public MutableLiveData<Pair<Boolean, Integer>> getContador2() {
        return contador2;
    }

    public void setContador2(MutableLiveData<Pair<Boolean, Integer>> contador2) {
        this.contador2 = contador2;
    }
}
