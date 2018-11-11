package io.github.codistro.remote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PowerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button shutDown, restart, hibernate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);

        shutDown = findViewById(R.id.shutdown);
        restart = findViewById(R.id.restart);
        hibernate = findViewById(R.id.hibernate);

        shutDown.setOnClickListener(this);
        restart.setOnClickListener(this);
        hibernate.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shutdown:
                new Connection().execute("1");
                break;
            case R.id.restart:
                new Connection().execute("2");
                break;
            case R.id.hibernate:
                new Connection().execute("3");
                break;
        }
    }
}
