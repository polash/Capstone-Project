package com.sksanwar.cricketbangla.fetchdata;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.sksanwar.cricketbangla.Pojo.Cricket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sksho on 14-Nov-17.
 */

public class getCricketData extends AsyncTask<Void, Void, List<Cricket>> {
    private static final String LOG_TAG = getCricketData.class.getSimpleName();
    //API KEY
    private static final String API_KEY = "7nAxSDAJ42eOMxxLGlVjjG3uciH3";

    //getiing data from json method
    private List<Cricket> getRecipeDataFromJson(String data)
            throws JSONException {

        //CONSTANTS for the JSON Objects
        final String CRICKET_UNIQUE_ID = "unique_id";
        final String CRICKET_DESCRIPTION = "description";
        final String CRICKET_TITLE = "title";

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Cricket> cricketdata = new ArrayList<>();

        try {
            // list of News objects with the corresponding data.
            JSONObject baseJSONResponse = new JSONObject(data);

            JSONArray resultsArray = baseJSONResponse.optJSONArray("data");

            for (int i = 0; i < resultsArray.length(); i++){
                JSONObject currentCricket = resultsArray.optJSONObject(i);
                String uniqueID = currentCricket.getString(CRICKET_UNIQUE_ID);
                String description = currentCricket.getString(CRICKET_DESCRIPTION);
                String title = currentCricket.getString(CRICKET_TITLE);

                // Create a new {@link news} object with the title, topic, date,url
                // and url from the JSON response.
                Cricket cricket = new Cricket(uniqueID, description, title);
                cricketdata.add(cricket);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return cricketdata;
    }

    protected List<Cricket> doInBackground(Void... voids) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String recipeJsonString = null;

        try {
            //Recipe Base Url
            final String BASE_URL = "http://cricapi.com/api/cricket/?";

            //Creating the Uri
            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter("apikey", API_KEY)
                    .build();

            //Creating URL
            URL url = new URL(builtUri.toString());
            Log.d(LOG_TAG, "URL" + url);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                recipeJsonString = readFromStream(inputStream);
            } else {
                Log.d(LOG_TAG ,"Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            // If the code didn't successfully get the Recipe data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final IOException e) {
                    Log.d(LOG_TAG, "Error closing inputStream", e);
                }
            }
        }

        try {
            return getRecipeDataFromJson(recipeJsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
