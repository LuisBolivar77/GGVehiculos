package com.example.luchobolivar.ggvehiculos;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegistroVehiActivity extends AppCompatActivity {


    EditText placaVehiculo;
    Spinner marca;
    Spinner tipoVehiculo;
    EditText numeroPasajeros;
    ArrayAdapter<String> adapterMarcas;
    String marcas = "Marcas.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehi);

        placaVehiculo = (EditText) findViewById(R.id.etPlaca);
        marca = (Spinner) findViewById(R.id.cbMarca);
        tipoVehiculo = (Spinner) findViewById(R.id.cbTipoVehiculo);
        numeroPasajeros = (EditText) findViewById(R.id.etNumeroDePasajeros);

        String[] archivos = fileList();

        if(existeArchivo(archivos, marcas)){
            try{
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput(marcas));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null){
                    todo = todo + linea + "\n";
                    linea = br.readLine();

                }

                br.close();
                archivo.close();



            }catch (IOException e){
                Toast.makeText(this, "error ", Toast.LENGTH_SHORT).show();
            }


        }



    }

    public void grabar(View view){

        String placa = placaVehiculo.getText().toString();

        try{
            OutputStreamWriter writer = new OutputStreamWriter(
                    openFileOutput(placa, Activity.MODE_PRIVATE));

            writer.write(placa + " " + marca.getSelectedItem() + " "
                    + tipoVehiculo.getSelectedItem() + " " + numeroPasajeros.getText().toString());
            writer.flush();
            writer.close();

            placaVehiculo.setText("");
            marca.setSelection(0);
            tipoVehiculo.setSelection(0);
            numeroPasajeros.setText("");

            Toast.makeText(this, "Vehiculo Registrado", Toast.LENGTH_SHORT).show();


        }catch (IOException e){

            Toast.makeText(this, "Error al intentar Registrar el vehiculo", Toast.LENGTH_SHORT).show();

        }

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
