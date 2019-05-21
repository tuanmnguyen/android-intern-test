/***
 * This class handles the activity (views) to display the episode details.
 *
 * @author Tuan M. Nguyen
 */
package tuanmnguyen.AndroidTestTask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    private String episodeTitle, episodeSeason, episodeSummary,
                    episodeUrl, episodeAirdate, episodeAirtime, episodeRuntime, episodeImageLarge;

    private ImageView imageEpisodeLarge;
    private TextView textEpisodeTitle, textEpisodeSeason, textEpisodeSummary,
            textEpisodeAirdate, textEpisodeRuntime;
    private Button buttonEpisodeUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle dataBundle = getIntent().getExtras();

        this.imageEpisodeLarge = this.findViewById(R.id.imageView_episode_large);
        this.textEpisodeTitle = this.findViewById(R.id.textView_episode_title);
        this.textEpisodeSeason = this.findViewById(R.id.textView_episode_season);
        this.textEpisodeSummary = this.findViewById(R.id.textView_episode_summary);
        this.buttonEpisodeUrl = this.findViewById(R.id.button_view_url);
        this.textEpisodeAirdate = this.findViewById(R.id.textView_episode_airdate);
        this.textEpisodeRuntime = this.findViewById(R.id.textView_episode_runtime);


        // Extract data from bundle & display on View components
        episodeImageLarge = dataBundle.getString(this.getString(R.string.episode_image_large));
        new FetchImageFromURL(imageEpisodeLarge, this).execute(episodeImageLarge);

        episodeTitle = String.format(this.getString(R.string.details_episode_title),
                dataBundle.getString(this.getString(R.string.episode_number)),
                dataBundle.getString(this.getString(R.string.episode_name)));

        textEpisodeTitle.setText(episodeTitle);

        episodeSeason = String.format((this.getString(R.string.details_season_title)), dataBundle.getString(this.getString(R.string.episode_season)));
        textEpisodeSeason.setText(episodeSeason);

        episodeRuntime = dataBundle.getString(this.getString(R.string.episode_runtime));
        textEpisodeRuntime.setText(String.format(this.getString(R.string.details_runtime_format), episodeRuntime));

        episodeSummary = dataBundle.getString(this.getString(R.string.episode_summary));
        textEpisodeSummary.setText(episodeSummary);

        episodeAirdate = dataBundle.getString(this.getString(R.string.episode_airdate));
        episodeAirtime = dataBundle.getString(this.getString(R.string.episode_airtime));
        textEpisodeAirdate.setText(String.format(this.getString(R.string.details_airdate_format), episodeAirdate, episodeAirtime));

        episodeUrl = dataBundle.getString(this.getString(R.string.episode_url));

        // Defines the onClick event for buttonEpisodeUrl
        buttonEpisodeUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOpenWebView = new Intent(getApplicationContext(), WebViewActivity.class);
                intentOpenWebView.putExtra(getApplicationContext().getString(R.string.episode_url), episodeUrl);

                Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.message_loading_url),Toast.LENGTH_SHORT).show();

                getApplicationContext().startActivity(intentOpenWebView);
            }
        });

    }
}
