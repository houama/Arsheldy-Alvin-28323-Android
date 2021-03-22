package id.ac.umn.arsheldyalvin_28323_uts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private TextView url1, url2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        url1 = findViewById(R.id.url1);
        url2 = findViewById(R.id.url2);

        url1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent browseIntent = new Intent(Intent.ACTION_VIEW);
                browseIntent.setData(Uri.parse("https://www.youtube.com/watch?v=86JmsXOfyv0"));
                startActivity(browseIntent);
            }
        });

        url2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent browseIntent = new Intent(Intent.ACTION_VIEW);
                browseIntent.setData(Uri.parse("https://www.youtube.com/watch?v=rJ3XbXtjNaE"));
                startActivity(browseIntent);
            }
        });
    }


}
