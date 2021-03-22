package id.ac.umn.arsheldyalvin_28323_uts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MusicViewHolder> {

    private final ArrayList<String> songs;
    private LayoutInflater mInflater;

    ListAdapter(Context context, ArrayList<String> songList) {
        mInflater = LayoutInflater.from(context);
        songs = songList;
    }

    @NonNull
    @Override
    public ListAdapter.MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.song_list_item,
                parent, false);
        return new MusicViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MusicViewHolder holder, int position) {
        String title = songs.get(position);
        String mCurrent = title.substring(title.lastIndexOf("/") + 1);
        holder.MusicItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {
        public final TextView MusicItemView;
        final ListAdapter listAdapter;

        public MusicViewHolder(@NonNull View itemView, ListAdapter mAdapter) {
            super(itemView);
            MusicItemView = itemView.findViewById(R.id.list);
            this.listAdapter = mAdapter;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();

                    Context context = itemView.getContext();

                    Intent intent = new Intent(context, PlayerActivity.class);
                    intent.putStringArrayListExtra("List", songs);
                    intent.putExtra("position", position);
                    context.startActivity(intent);

                }
            });
        }
    }
}


