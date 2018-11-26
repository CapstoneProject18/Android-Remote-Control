package io.github.codistro.remote;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PowerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button shutDown, restart, hibernate;
    private String IP;
    private SharedPreferences IPSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);

        shutDown = findViewById(R.id.shutdown);
        restart = findViewById(R.id.restart);
        hibernate = findViewById(R.id.hibernate);

        IPSharedPreferences = getSharedPreferences("IP", MODE_PRIVATE);
        IP = IPSharedPreferences.getString("IP", "");

        shutDown.setOnClickListener(this);
        restart.setOnClickListener(this);
        hibernate.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shutdown:
                new Connection().execute(IP, "1");
                break;
            case R.id.restart:
                new Connection().execute(IP, "2");
                break;
            case R.id.hibernate:
                new Connection().execute(IP, "3");
                break;
        }
    }
}
