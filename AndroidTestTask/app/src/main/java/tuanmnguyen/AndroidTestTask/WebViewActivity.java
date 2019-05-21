/***
 * This class handles the activity (views) to display URL on web view.
 *
 * @author Tuan M. Nguyen
 */
package tuanmnguyen.AndroidTestTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    private WebView webViewer;
    private String webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webViewer = this.findViewById(R.id.webView_displayUrl);

        webUrl = getIntent().getStringExtra(this.getString(R.string.episode_url));
        webViewer.loadUrl(webUrl);
    }
}
