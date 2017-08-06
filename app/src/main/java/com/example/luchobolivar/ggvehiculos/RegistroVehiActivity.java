package com.example.luchobolivar.ggvehiculos;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class RegistroVehiActivity extends AppCompatActivity {


    EditText placaVehiculo;
    Spinner marca;
    Spinner tipoVehiculo;
    EditText numeroPasajeros;
    ArrayAdapter<CharSequence> adpaterMarcas;
    ArrayAdapter<CharSequence> adpaterTipoVehiculo;
    String listaVehiculos = "listaVehiculos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehi);

        placaVehiculo = (EditText) findViewById(R.id.etPlaca);
        marca = (Spinner) findViewById(R.id.cbMarca);
        tipoVehiculo = (Spinner) findViewById(R.id.cbTipoVehiculo);
        numeroPasajeros = (EditText) findViewById(R.id.etNumeroDePasajeros);

        adpaterMarcas = ArrayAdapter.createFromResource(this, R.array.marcas, android.R.layout.simple_spinner_item);
        adpaterMarcas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marca.setAdapter(adpaterMarcas);

        adpaterTipoVehiculo = ArrayAdapter.createFromResource(this, R.array.tipoVehiculo, android.R.layout.simple_spinner_item);
        adpaterTipoVehiculo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoVehiculo.setAdapter(adpaterTipoVehiculo);

    }


    public String archivoExixstente(String listaVehicu) {

        String[] archivos = fileList();

        if (existeArchivo(archivos, listaVehicu)) {
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput(listaVehicu));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();

                }

                br.close();
                archivo.close();
                return todo;


            } catch (IOException e) {
                Toast.makeText(this, "error ", Toast.LENGTH_SHORT).show();
            }


        }

        return null;


    }


    public void grabar(View view) {

        String placa = placaVehiculo.getText().toString();


        if(archivoExixstente(listaVehiculos) == null) {

            try {
                OutputStreamWriter archivo = new OutputStreamWriter
                        (openFileOutput(
                                listaVehiculos, Activity.MODE_PRIVATE));

                archivo.write(placa + " " + marca.getSelectedItem().toString() + " " + tipoVehiculo.getSelectedItem().toString() +
                        " " + numeroPasajeros.getText().toString());

                archivo.flush();

                archivo.close();

                placaVehiculo.setText("");
                marca.setSelection(0);
                tipoVehiculo.setSelection(0);
                numeroPasajeros.setText("");

                Toast.makeText(this, "Vehiculo Registrado", Toast.LENGTH_SHORT).show();


            } catch (IOException e) {

                Toast.makeText(this, "Error al intentar Registrar el vehiculo", Toast.LENGTH_SHORT).show();

            }

        }else{

            try {
                String archivoAntiguo = archivoExixstente(listaVehiculos);

                OutputStreamWriter archivo = new OutputStreamWriter
                        (openFileOutput(
                                listaVehiculos, Activity.MODE_PRIVATE));

                archivo.write(archivoAntiguo + " " + placa + " " + marca.getSelectedItem().toString() + " " + tipoVehiculo.getSelectedItem().toString() +
                        " " + numeroPasajeros.getText().toString());

                archivo.flush();

                archivo.close();

                placaVehiculo.setText("");
                marca.setSelection(0);
                tipoVehiculo.setSelection(0);
                numeroPasajeros.setText("");

                Toast.makeText(this, "Vehiculo Registrado", Toast.LENGTH_SHORT).show();


            } catch (IOException e) {

                Toast.makeText(this, "Error al intentar Registrar el vehiculo", Toast.LENGTH_SHORT).show();

            }

        }


    }

    private boolean existeArchivo(String[] archivos, String archBuscar) {

        for (int f = 0; f < archivos.length; f++) {
            if (archBuscar.equals(archivos[f])) {
                return true;
            }

        }
        return false;


    }


}
