package com.example.luchobolivar.ggvehiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView listaVehiculos;
    String archivoVehic = "listaVehiculos.txt";
    ArrayList<String> lista;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaVehiculos = (ListView) findViewById(R.id.listaVehiculos);

        String[] archivos = fileList();

        if(existeArchivo(archivos, archivoVehic)){
            try{
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput(archivoVehic));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null){
                    todo = todo + linea + "\n";
                    linea = br.readLine();

                }

                br.close();
                archivo.close();
                lista = new ArrayList<>(Arrays.asList(todo));
                listar(lista);


            }catch (IOException e){
                Toast.makeText(this, "error ", Toast.LENGTH_SHORT).show();
            }


        }


    }




    public void listar (ArrayList<String> listaVehiculo){

        ArrayAdapter<String> adapterListaVehiculos = new ArrayAdapter<String>(this, R.layout.items, listaVehiculo);
        listaVehiculos.setAdapter(adapterListaVehiculos);



    }

    public void registroVehiculos(View view){

        Intent intent = new Intent(this, RegistroVehiActivity.class);
        startActivity(intent);


    }

    private boolean existeArchivo(String [] archivos, String archBuscar){

        for (int f=0; f< archivos.length; f++){
            if(archBuscar.equals(archivos[f])){
                return true;
            }

        }
        return false;


    }


}
