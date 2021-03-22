package id.ac.umn.arsheldyalvin_28323_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText Username, Password;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(Username.getText().toString().equals("uasmobile") && Password.getText().toString().equals("uasmobilegenap")){
                    Toast.makeText(getApplicationContext(), "Login Successfully !!!", Toast.LENGTH_SHORT).show();
                    Intent intentMusicList = new Intent(LoginActivity.this,
                            ListActivity.class);
                    startActivityForResult(intentMusicList, 1);
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid Username & Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
