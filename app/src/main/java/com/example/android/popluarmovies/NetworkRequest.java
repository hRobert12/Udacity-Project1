package com.example.android.popluarmovies;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Robert on 10/25/2016.
 */

public class NetworkRequest {
    private static final String LOG_TAG = "NetworkRequest";

    private NetworkRequest() {throw new AssertionError("No NetworkRequest instances for you!");}

    public static String makeHTTPRequest(String urlToRequest) {
        URL url;
        try {
            url = new URL(urlToRequest);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }

        String jsonResponse = "";
        try {
            if (url == null) {
                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();

                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
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
                    jsonResponse = output.toString();
                } else {
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem retrieving the Movie JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        return jsonResponse;
    }
}
