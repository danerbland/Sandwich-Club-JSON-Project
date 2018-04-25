package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class JsonUtils {

    //First, let's define the Keys to use in the JSON key-value pairs
    private static final String JSON_NAME_KEY = "name";
    private static final String JSON_AKA_KEY = "alsoKnownAs";
    private static final String JSON_ORIGIN_KEY = "placeOfOrigin";
    private static final String JSON_DESCRIPTION_KEY = "description";
    private static final String JSON_IMAGE_KEY = "image";
    private static final String JSON_INGREDIENTS_KEY = "ingredients";


    public static Sandwich parseSandwichJson(String json) throws JSONException {




            JSONObject jsonObject = new JSONObject(json);




        //TODO 1 Implement this function

        return null;
    }
}
