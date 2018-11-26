package io.github.codistro.remote;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.zip.Inflater;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private CardView powerCard;

    private String IP;

    private SharedPreferences IPSharedPreferences;
    AlertDialog.Builder signoutDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth = FirebaseAuth.getInstance();


        powerCard = findViewById(R.id.powercard);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(Dashboard.this, SignIn.class));
                }

            }
        };

        IPSharedPreferences = getSharedPreferences("IP", MODE_PRIVATE);

        signoutDialog = new AlertDialog.Builder(this);
        signoutDialog.setMessage("Do you want to Sign Out?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mAuth.signOut();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });



        powerCard.setOnClickListener(Dashboard.this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.signout:
                signoutDialog.show();
                break;
            case  R.id.add_ip:
                showUpdateIPDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showUpdateIPDialog(){
        final AlertDialog ipDialog = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_ip,null);
        ipDialog.setView(view);
        final EditText ipEditText = view.findViewById(R.id.ip_edit_text);
        Button add = view.findViewById(R.id.add_action);
        Button cancel = view.findViewById(R.id.cancel);


        IP = IPSharedPreferences.getString("IP","");
        ipEditText.setText(IP);
        ipDialog.show();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IP = ipEditText.getText().toString();
                SharedPreferences.Editor editor = IPSharedPreferences.edit();
                editor.putString("IP", IP);
                editor.apply();
                ipDialog.dismiss();
                //Toast.makeText(getApplicationContext(), IP, Toast.LENGTH_LONG).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ipDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.powercard){
            startActivity(new Intent(this, PowerActivity.class));
        }

    }
}
