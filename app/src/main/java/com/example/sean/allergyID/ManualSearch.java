package com.example.sean.allergyID;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ManualSearch extends AppCompatActivity {

    private EditText upc;

    //TODO: make demo picture (easy)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_search);

        upc = (EditText) findViewById(R.id.manualUpc);

    }

    public void search(View view){
        Intent intent = new Intent(this, Result.class);
        intent.putExtra("UPC",upc.getText().toString());
        startActivity(intent);
    }
}
