/***
 * This class handles the background thread to fetch the image from URL.
 *
 * @author Tuan M. Nguyen
 */
package tuanmnguyen.AndroidTestTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class FetchImageFromURL extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;
    private Context context;

    public FetchImageFromURL(ImageView imageView, Context context) {
        this.imageView = imageView;
        this.context = context;
    }

    protected Bitmap doInBackground(String... urls) {
        String imageURL = urls[0];
        Bitmap image = null;
        try {
            InputStream in = new java.net.URL(imageURL).openStream();
            image = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e(context.getString(R.string.message_error), e.getMessage());
            e.printStackTrace();
        }
        return image;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}
