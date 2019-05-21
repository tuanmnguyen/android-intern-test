/***
 * This class handles the launcher activity of the application.
 *
 * @author Tuan M. Nguyen
 */
package tuanmnguyen.AndroidTestTask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tuanmnguyen.AndroidTestTask.model.Episode;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Episode> episodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        episodeList = new ArrayList<>();

        recyclerView = findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        new FetchDataFromAPI(episodeList).execute();
    }

    /***
     * This class creates a background thread to fetch data from the API & handles
     * JSON parsing into ArrayList of episodes.
     *
     */
    private class FetchDataFromAPI extends AsyncTask<Void, Void, String> {

        private ArrayList<Episode> episodeList;

        public FetchDataFromAPI(ArrayList<Episode> episodeList) {
            this.episodeList = episodeList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                // Fetch data from the API
                URL apiUrl = new URL(getApplicationContext().getString(R.string.API_URL));
                HttpURLConnection urlConnection = (HttpURLConnection) apiUrl.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String content;

                    while ((content = bufferedReader.readLine()) != null) {
                        stringBuilder.append(content).append("\n");
                    }
                    bufferedReader.close();

                    // Extract JSON data & add to ArrayList
                    try {
                        JSONArray jsonArrayEpisodes = new JSONArray(stringBuilder.toString());

                        for (int i = 0; i < jsonArrayEpisodes.length(); i++) {

                            JSONObject jsonObjectEpisode = jsonArrayEpisodes.getJSONObject(i);

                            int id = jsonObjectEpisode.getInt(getApplication().getString(R.string.id));
                            String url = jsonObjectEpisode.getString(getApplication().getString(R.string.url));
                            String name = jsonObjectEpisode.getString(getApplication().getString(R.string.name));
                            int season = jsonObjectEpisode.getInt(getApplication().getString(R.string.season));
                            int number = jsonObjectEpisode.getInt(getApplication().getString(R.string.number));
                            String airdate = jsonObjectEpisode.getString(getApplication().getString(R.string.airdate));
                            String airtime = jsonObjectEpisode.getString(getApplication().getString(R.string.airtime));
                            String airstamp = jsonObjectEpisode.getString(getApplication().getString(R.string.airstamp));
                            int runtime = jsonObjectEpisode.getInt(getApplication().getString(R.string.runtime));

                            JSONObject jsonObjectImage =  jsonObjectEpisode.getJSONObject(getApplication().getString(R.string.image));
                            String imageMedium = jsonObjectImage.getString(getApplication().getString(R.string.medium));
                            String imageLarge = jsonObjectImage.getString(getApplication().getString(R.string.original));

                            String summary = jsonObjectEpisode.getString(getApplication().getString(R.string.summary));

                            // Extract summary without HTML tags
                            summary = summary.substring(3,summary.length() - 4);

                            DateFormat dateFormatter = new SimpleDateFormat(getApplication().getString(R.string.date_format_input));
                            Date epDate = dateFormatter.parse(airdate);

                            DateFormat timeFormatter = new SimpleDateFormat(getApplication().getString(R.string.time_format));
                            Date epTime = timeFormatter.parse(airtime);

                            Episode newEpisode = new Episode(id, url, name, season, number, epDate, epTime, airstamp, runtime, imageMedium, imageLarge, summary);

                            episodeList.add(newEpisode);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    return stringBuilder.toString();
                }
                finally {
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e(getApplicationContext().getString(R.string.message_error), e.getMessage(), e);
                return null;
            }
        }


        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            // Put data into RecyclerView adapter
            recyclerViewAdapter = new RecyclerViewItem(episodeList, getApplicationContext());
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }
}
