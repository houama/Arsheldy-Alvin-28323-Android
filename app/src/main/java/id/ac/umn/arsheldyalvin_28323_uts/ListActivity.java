package id.ac.umn.arsheldyalvin_28323_uts;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private RecyclerView musicList;
    private ListAdapter mAdapter;
    private final ArrayList<String> songs = new ArrayList<>();

    private static String[] audioColumns = new String[] {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA };

    public void openDialog(){
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "Hello");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
        openDialog();


            String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
            Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, audioColumns, selection, null, null);
            if(cursor != null){
                if(cursor.moveToFirst()) {
                    do {
                        String songPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                        songs.add(songPath);
                       // Log.d("Array", String.valueOf(songs));


                    } while (cursor.moveToNext());
                    musicList = (RecyclerView) findViewById(R.id.recyclerview);
                    mAdapter = new ListAdapter(ListActivity.this, songs);
                    musicList.setAdapter(mAdapter);
                    musicList.setLayoutManager(new LinearLayoutManager(ListActivity.this));
                }
            }
            cursor.close();


    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.menu_profile)
        {
            startActivity(new Intent(ListActivity.this, ProfileActivity.class));
        }
        if (item.getItemId()==R.id.logout)
        {
            onDestroy();
            finish();
        }
        return true;
    }

}
