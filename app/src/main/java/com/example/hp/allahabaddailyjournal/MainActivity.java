package com.example.hp.allahabaddailyjournal;

//import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//import static com.androidcss.jsonexample.MainActivity.READ_TIMEOUT;

public class MainActivity extends AppCompatActivity {

    RecyclerView judgementRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        judgementRecyclerView=findViewById(R.id.judgementRecycler);

        new AsyncLogin().execute();
    }
    private class AsyncLogin extends AsyncTask<String, String, String> {
      //  ProgressDialog pdLoading = new ProgressDialog(com.androidcss.jsonexample.MainActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
//            pdLoading.setMessage("\tLoading...");
//            pdLoading.setCancelable(false);
//            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://adjonline.com/mojito/newdetails.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
               // conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

           // pdLoading.dismiss();
            List<Judgements> data=new ArrayList<>();

            //pdLoading.dismiss();
            try {

                JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    Judgements JudgementData = new Judgements();
//                    fishData.fishImage= json_data.getString("fish_img");
//                    fishData.fishName= json_data.getString("fish_name");
//                    fishData.catName= json_data.getString("cat_name");
//                    fishData.sizeName= json_data.getString("size_name");
//                    fishData.price= json_data.getInt("price");
                        JudgementData.mCase =json_data.getString("18");
                        JudgementData.mBefore=json_data.getString("jud");
                        JudgementData.mdate=json_data.getString("dt");
                        JudgementData.mMonth=json_data.getString("mn");
                        JudgementData.myearl=json_data.getString("yer");
                    data.add(JudgementData);
                }

                // Setup and Handover data to recyclerview
                judgementRecyclerView = (RecyclerView)findViewById(R.id.judgementrecycler);

              Adapter  mAdapter = new Adapter(getApplicationContext(), data);
                judgementRecyclerView.setAdapter(mAdapter);
                judgementRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
