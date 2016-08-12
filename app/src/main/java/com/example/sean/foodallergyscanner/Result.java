package com.example.sean.foodallergyscanner;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView result1;
    TextView result2;
    TextView title;
    ImageView headerImg;
    RelativeLayout resultsLayout;
    FoodInfo foodInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result1 = (TextView)findViewById(R.id.results1);
        result2 = (TextView)findViewById(R.id.results2);
        title = (TextView) findViewById(R.id.title);
        headerImg = (ImageView) findViewById(R.id.headerImg);
        resultsLayout = (RelativeLayout) findViewById(R.id.resultsLayout);

        result1.setAlpha(0f);
        result2.setAlpha(0f);


        getFoodInfo();
        setText();



    }
    public void getFoodInfo(){
        Intent intent = getIntent();
        foodInfo = new FoodInfo();
        foodInfo.setUpcCode(intent.getStringExtra("UPC"));
        foodInfo.getFoodInfo();

    }
    public void setText(){
        if(!foodInfo.upcNotFound()) {

            title.setText(foodInfo.getProductName());

            if(!foodInfo.containsYourAllergen().isEmpty()) {
                String text =foodInfo.getProductName()+ " contains the following items that you're" +
                        " allergic to: " + foodInfo.containsYourAllergen();
                result1.setText(text);
                result1.animate().alpha(1f).setDuration(1000).start();
                headerImg.setImageResource(R.drawable.ic_close_black_24dp);
                resultsLayout.setBackgroundColor(Color.parseColor("#FFAA0000"));
            }

            if(!foodInfo.mayContainYourAllergen().isEmpty()) {
                String text1 = foodInfo.getProductName()+" may contain the following items that you're" +
                        " allergic to: " + foodInfo.mayContainYourAllergen();
                result2.setText(text1);
                result2.animate().alpha(1f).setDuration(1000).start();
                headerImg.setImageResource(R.drawable.ic_close_black_24dp);
                resultsLayout.setBackgroundColor(Color.parseColor("#FFCA4C04"));
            }

            if(foodInfo.containsYourAllergen().isEmpty() && foodInfo.mayContainYourAllergen().isEmpty()){
                String text2 = foodInfo.getProductName()+ " may be safe";
                result1.setText(text2);
                result1.animate().alpha(1f).setDuration(1000).start();
                headerImg.setImageResource(R.drawable.ic_check_black_24dp);
                resultsLayout.setBackgroundColor(Color.parseColor("#FF007D19"));
            }
        }
        else
            title.setText("Product not found");
    }
}
