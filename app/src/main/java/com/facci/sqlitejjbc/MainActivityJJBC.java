package com.facci.sqlitejjbc;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityJJBC extends AppCompatActivity {

    JJBCHelper jjbcSQLITE;

    EditText txtNombre, txtApellido, txtRecintoElectoral, txtAnoNacimiento, txtID;
    Button btnInsertar, btnModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_jjbc);
        jjbcSQLITE = new JJBCHelper(this);
    }

    public void insertarClick(View v){

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtRecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAnoNacimiento = (EditText) findViewById(R.id.txtAnoNacimiento);

        boolean estaInsertado = jjbcSQLITE.insertar(txtNombre.getText().toString(), txtApellido.getText().toString(), txtRecintoElectoral.getText().toString(), Integer.parseInt(txtAnoNacimiento.getText().toString()));

        if(estaInsertado)
            Toast.makeText(MainActivityJJBC.this,"Datos Ingrsados", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityJJBC.this,"Lo sentimos ocurrio un error", Toast.LENGTH_SHORT).show();

    }

    public void verTodosClick(View v){
        Cursor res = jjbcSQLITE.selectVerTodos();
        if(res.getCount() == 0){

            mostrarMensaje("Error", "No se encuentre registro");
            return;
        }
        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre  :  "+res.getString(1)+"\n");
            buffer.append("Apellido  :  "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral  :  "+res.getString(3)+"\n");
            buffer.append("Año Nacimiento  :  "+res.getString(4)+"\n");
        }
        mostrarMensaje("Registro", buffer.toString());
    }

    public void mostrarMensaje(String titulo, String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }

    public void modificarRegistroClick(View v) {
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtAnoNacimiento = (EditText) findViewById(R.id.txtAnoNacimiento);
        txtAnoNacimiento = (EditText) findViewById(R.id.txtAnoNacimiento);
        txtID = (EditText) findViewById(R.id.txtID);

        boolean estaAcutalizado = jjbcSQLITE.modificarRegistro(txtID.getText().toString(),txtNombre.getText().toString(),txtApellido.getText().toString(),txtRecintoElectoral.getText().toString(),Integer.parseInt(txtAnoNacimiento.getText().toString()));
        if (estaAcutalizado == true){
            Toast.makeText(MainActivityJJBC.this,"Registro Actualizado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityJJBC.this,"ERROR: Registro NO Actualizado",Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarRegistroClick(View v){
        txtID = (EditText) findViewById(R.id.txtID);
        Integer registrosEliminados = jjbcSQLITE.eliminarRegistro(txtID.getText().toString());

        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivityJJBC.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityJJBC.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }

    }


}
