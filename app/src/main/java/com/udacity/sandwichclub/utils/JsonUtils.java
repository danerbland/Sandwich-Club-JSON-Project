package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    //First, let's define the Keys to use in the JSON key-value pairs
    private static final String JSON_NAME_KEY = "name";
    private static final String JSON_MAINNAME_KEY = "mainName";
    private static final String JSON_AKA_KEY = "alsoKnownAs";
    private static final String JSON_ORIGIN_KEY = "placeOfOrigin";
    private static final String JSON_DESCRIPTION_KEY = "description";
    private static final String JSON_IMAGE_KEY = "image";
    private static final String JSON_INGREDIENTS_KEY = "ingredients";


    public static Sandwich parseSandwichJson(String json){

        //create JSON object
        try {

            JSONObject jsonObject = new JSONObject(json);
            Sandwich sandwich = new Sandwich();

            /*
            Use the built in setters to set main name, aka, origin description, image, and ingredients.
            We must convert some JSONArray into List<string> for the sandwich object.  To do this
            I will iterate through the JSONArray to build a list from scratch.  Credit to Tushar on
            StackOverflow for the idea:
            https://stackoverflow.com/questions/3395729/convert-json-array-to-normal-java-array
            */

            JSONObject nameObject = jsonObject.getJSONObject(JSON_NAME_KEY);

            sandwich.setMainName(nameObject.getString(JSON_MAINNAME_KEY));

            List<String> akaList = new ArrayList<String>();
            //TODO Fix This Bug Here
            JSONArray akaArray = nameObject.getJSONArray(JSON_AKA_KEY);
            for(int i = 0; i < akaArray.length(); i++){
                akaList.add(akaArray.getString(i));
            }
            sandwich.setAlsoKnownAs(akaList);

            sandwich.setPlaceOfOrigin(jsonObject.getString(JSON_ORIGIN_KEY));

            sandwich.setImage(jsonObject.getString(JSON_IMAGE_KEY));

            List<String> ingredientsList = new ArrayList<String>();
            JSONArray ingredientArray = jsonObject.getJSONArray(JSON_INGREDIENTS_KEY);
            for (int i = 0; i < ingredientArray.length(); i++){
                ingredientsList.add(ingredientArray.getString(i));
            }
            sandwich.setIngredients(ingredientsList);

            sandwich.setDescription(jsonObject.getString(JSON_DESCRIPTION_KEY));



            return sandwich;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }
}
