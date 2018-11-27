package com.example.android.ejemplobasededatos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_dni, et_numero, et_nombre, et_ciudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_dni = (EditText)findViewById(R.id.etDni);
        et_nombre = (EditText)findViewById(R.id.etNombre);
        et_ciudad = (EditText)findViewById(R.id.etCiudad);
        et_numero = (EditText)findViewById(R.id.etTelefono);


    }

    public void crear(View view){
        //Dar de alta usuario
        AndroidHelper admin = new AndroidHelper(this, "administracion", null, 1 );
        SQLiteDatabase db = admin.getWritableDatabase();
        //Guardar los valores que leemos de los EditText en un registro
        //Para ello creamos el registro
        ContentValues registro = new ContentValues();
        registro.put("dni", et_dni.getText().toString());
        registro.put("nombre", et_nombre.getText().toString());
        registro.put("ciudad", et_ciudad.getText().toString());
        registro.put("numero", et_numero.getText().toString());

        //Insertar en la base de datos
        db.insert("usuario", null, registro);

        //Cerrar la base de datos
        db.close();

        //Poner los campos a vacio para insertar el siguiente usuario
        et_dni.setText("");
        et_nombre.setText("");
        et_ciudad.setText("");
        et_numero.setText("");

        Toast.makeText(this,"usuario insertado en la base de datos", Toast.LENGTH_LONG).show();
    }

    public void borrar(View view) {
        AndroidHelper admin = new AndroidHelper(this, "administracion", null, 1 );
        SQLiteDatabase db = admin.getWritableDatabase();

        String dni = et_dni.getText().toString();
        int cantidad = db.delete("usuario", "dni="+"'"+dni+"'",null);
        db.close();

        //Comprobacion filas afectadas
        if (cantidad == 1){
            Toast.makeText(this,"Se ha eliminado el usuario", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "No existe el usuario", Toast.LENGTH_LONG).show();
        }
        et_dni.setText("");
        et_nombre.setText("");
        et_ciudad.setText("");
        et_numero.setText("");

    }

    public void mostrar(View view){

    }
}
