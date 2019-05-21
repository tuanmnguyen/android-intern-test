/***
 * This class defines the list item adapter of RecyclerView to display episode data.
 *
 * @author Tuan M. Nguyen
 */
package tuanmnguyen.AndroidTestTask;
import tuanmnguyen.AndroidTestTask.model.Episode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RecyclerViewItem extends RecyclerView.Adapter<RecyclerViewItem.ViewHolder>  {

    private ArrayList<Episode> allEpisodes;
    private Context context;

    public RecyclerViewItem(ArrayList<Episode> dataSet, Context context) {
        allEpisodes = dataSet;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return allEpisodes.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Episode thisEpisode = allEpisodes.get(position);

        new FetchImageFromURL(holder.imageEpisodeMedium, context)
                .execute(thisEpisode.getImageMedium());

        holder.textEpisodeSeasonNumber.setText(String.format(context.getString(R.string.list_item_title), thisEpisode.getSeason(), thisEpisode.getNumber()));
        holder.textEpisodeName.setText(thisEpisode.getName());
        holder.textEpisodeSummary.setText(thisEpisode.getSummary());

        // Defines onClick listener for the adapter
        holder.layoutListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOpenDetailsActivity = new Intent(context, DetailsActivity.class);

                Bundle dataBundle = new Bundle();

                SimpleDateFormat dateFormat = new SimpleDateFormat(context.getString(R.string.date_format));
                SimpleDateFormat timeFormat = new SimpleDateFormat(context.getString(R.string.time_format));

                dataBundle.putString(context.getString(R.string.episode_name),thisEpisode.getName());
                dataBundle.putString(context.getString(R.string.episode_number), String.valueOf(thisEpisode.getNumber()));
                dataBundle.putString(context.getString(R.string.episode_season), String.valueOf(thisEpisode.getSeason()));
                dataBundle.putString(context.getString(R.string.episode_airdate), dateFormat.format(thisEpisode.getAirdate()));
                dataBundle.putString(context.getString(R.string.episode_airtime), timeFormat.format(thisEpisode.getAirtime()));
                dataBundle.putString(context.getString(R.string.episode_runtime), String.valueOf(thisEpisode.getRuntime()));
                dataBundle.putString(context.getString(R.string.episode_image_large), thisEpisode.getImageOriginal());
                dataBundle.putString(context.getString(R.string.episode_summary), thisEpisode.getSummary());
                dataBundle.putString(context.getString(R.string.episode_url), thisEpisode.getUrl());

                intentOpenDetailsActivity.putExtras(dataBundle);

                context.startActivity(intentOpenDetailsActivity);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageEpisodeMedium;
        public TextView textEpisodeSeasonNumber;
        public TextView textEpisodeName;
        public TextView textEpisodeSummary;

        public LinearLayout layoutListItem;

        public ViewHolder(View itemView) {
            super(itemView);

            this.imageEpisodeMedium = itemView.findViewById(R.id.image_episode_medium);
            this.textEpisodeSeasonNumber = itemView.findViewById(R.id.textView_episode_season_number);
            this.textEpisodeName = itemView.findViewById(R.id.textView_episode_name);
            this.textEpisodeSummary = itemView.findViewById(R.id.textView_episode_summary);

            this.layoutListItem = itemView.findViewById(R.id.layout_list_item);
        }
    }

}
