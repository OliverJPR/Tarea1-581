package com.oliverjpr.tarea1_581;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener{


    private TextView fecha;
    private EditText nombre;
    private EditText apellido;
    private EditText email;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fecha = findViewById(R.id.txtFecha);Spinner spinner = (Spinner) findViewById(R.id.spnEdad);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        email = findViewById(R.id.txtEmail);
        Integer[] ageList = getAnios();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ageList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        findViewById(R.id.btnFecha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

    }



    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        String fullNameInput = nombre.getText().toString().trim() + ' ' + apellido.getText().toString().trim();
        String emailInput = email.getText().toString().trim();
        intent.putExtra(EXTRA_MESSAGE, fullNameInput);
        String fullInput = "El cliente " + fullNameInput + " se ha registrado con el e-mail: " + emailInput;
        if (validateEmail() == false){
            return;
        }else{
            Toast.makeText(this, fullInput, Toast.LENGTH_LONG).show();
        }

        startActivity(intent);
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String date = "dia/mes/anno: " + dayOfMonth + '/' + (month+1) + '/' + year;
        fecha.setText(date);
    }

    public Integer[] getAnios(){
        Integer [] anios = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        return anios;
    }
    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("Email no puede ser vacio");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            email.setError("Insgrese una dirección de correo válida");
            return false;
        }
        else {
            email.setError(null);
            return true;
        }
    }



}
