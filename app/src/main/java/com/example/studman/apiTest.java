package com.example.studman;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class apiTest extends AppCompatActivity {

    Button b1;
    EditText et;
    String editText;
    String displayText;
    ProgressBar pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);

        //Declaration
        b1 = (Button) findViewById(R.id.btnAPI);
        et = (EditText) findViewById(R.id.txtName);
        pg = (ProgressBar) findViewById(R.id.progressBar) ;

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et.getText().length() != 0 && et.getText().toString() != "") {
                    //Get the text control value
                    editText = et.getText().toString();
                    //Create instance for AsyncCallWS
                    AsyncCallWS task = new AsyncCallWS();
                    //Call execute
                    task.execute();
                    //If text control is empty
                } else {
                    Toast.makeText(apiTest.this, "Please enter name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            displayText = WebService.invokeHelloWorldWS(editText,"HelloWorld");
//            displayText = WebService.invokeHelloWorldWS(editText,"HelloWorld");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            Toast.makeText(apiTest.this, ""+displayText, Toast.LENGTH_SHORT).show();
            //Make ProgressBar invisible
            pg.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            pg.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

}

