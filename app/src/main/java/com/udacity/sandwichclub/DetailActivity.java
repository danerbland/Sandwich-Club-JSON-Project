package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

//    private ImageView iv_Sandwich_Image = (ImageView) findViewById(R.id.image_iv);
    private TextView tv_Sandwich_AKA_Label;
    private TextView tv_Sandwich_AKA;
    private TextView tv_Sandwich_Origin_Label;
    private TextView tv_Sandwich_Origin;
    private TextView tv_Sandwich_Description;
    private TextView tv_Sandwich_Ingredients;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            Log.e("ERROR", "intent = null");
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            Log.e("ERROR", "position = default");
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            Log.e("ERROR", "sandwich = null");
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        tv_Sandwich_AKA = (TextView) findViewById(R.id.also_known_tv);
        tv_Sandwich_AKA_Label = (TextView) findViewById(R.id.aka_label_tv);
        tv_Sandwich_Origin_Label = (TextView) findViewById(R.id.origin_label_tv);
        tv_Sandwich_Origin = (TextView) findViewById(R.id.origin_tv);
        tv_Sandwich_Description = (TextView) findViewById(R.id.description_tv);
        tv_Sandwich_Ingredients = (TextView) findViewById(R.id.ingredients_tv);


        //DONE 2 populate the UI via this method.  The image is handled above, so we will only deal
        //with the textviews

        //Hide AKA if the field is empty
        if(sandwich.getAlsoKnownAs().size() == 0){
            tv_Sandwich_AKA_Label.setVisibility(View.GONE);
            tv_Sandwich_AKA.setVisibility(View.GONE);
        }

        for(int i = 0; i < sandwich.getAlsoKnownAs().size(); i++){
            if(i != sandwich.getAlsoKnownAs().size() - 1) {
                tv_Sandwich_AKA.append(sandwich.getAlsoKnownAs().get(i) + "\n");
            } else {
                tv_Sandwich_AKA.append(sandwich.getAlsoKnownAs().get(i));
            }
        }

        if(sandwich.getPlaceOfOrigin().isEmpty()){
            tv_Sandwich_Origin_Label.setVisibility(View.GONE);
            tv_Sandwich_Origin.setVisibility(View.GONE);
        }
        tv_Sandwich_Origin.setText(sandwich.getPlaceOfOrigin());

        tv_Sandwich_Description.setText(sandwich.getDescription());

        for(String s : sandwich.getIngredients()){
            tv_Sandwich_Ingredients.append(s + "\n");
        }
}
}
