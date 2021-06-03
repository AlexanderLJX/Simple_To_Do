package com.id20048076.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvTasks;
    ArrayList<String> alTasks = new ArrayList<String>();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvTasks = findViewById(R.id.ListView);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTasks.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                alTasks.add(task);
                adapter.notifyDataSetChanged();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTasks.isEmpty()){
                    Toast toast = Toast.makeText(MainActivity.this,"You don't have any task to remove", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                int index = Integer.parseInt(etTask.getText().toString());
                if(index<alTasks.size()) {
                    alTasks.remove(index);
                    adapter.notifyDataSetChanged();
                }else{
                    Toast toast = Toast.makeText(MainActivity.this,"Wrong index number", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                adapter.notifyDataSetChanged();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        etTask.setText("");
                        etTask.setHint("Type in a new task here");
                        etTask.setInputType(InputType.TYPE_CLASS_TEXT);
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        etTask.setText("");
                        etTask.setHint("Type in the index of the task to be removed");
                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}