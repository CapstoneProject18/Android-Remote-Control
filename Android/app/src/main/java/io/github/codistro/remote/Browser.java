package io.github.codistro.remote;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Browser extends AppCompatActivity implements View.OnClickListener, TextWatcher, View.OnFocusChangeListener {

    private ImageView arrowUp, arrowDown, navLeft, navRight, newTab, closeTab, tab;
    private Button search, altTab, enter;
    private EditText text;
    private SharedPreferences IPSharedPreferences;
    private String IP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        search = findViewById(R.id.search);
        text = findViewById(R.id.text);
        altTab = findViewById(R.id.alt_tab);
        enter = findViewById(R.id.enter);

        arrowUp = findViewById(R.id.arrow_up);
        arrowDown = findViewById(R.id.arrow_down);
        navLeft = findViewById(R.id.tab_left);
        navRight = findViewById(R.id.tab_right);
        newTab = findViewById(R.id.new_tab);
        closeTab = findViewById(R.id.close_tab);
        tab = findViewById(R.id.tab);

        IPSharedPreferences = getSharedPreferences("IP", MODE_PRIVATE);
        IP = IPSharedPreferences.getString("IP", "");

        arrowDown.setOnClickListener(this);
        arrowUp.setOnClickListener(this);
        navLeft.setOnClickListener(this);
        navRight.setOnClickListener(this);
        newTab.setOnClickListener(this);
        closeTab.setOnClickListener(this);
        search.setOnClickListener(this);
        altTab.setOnClickListener(this);
        enter.setOnClickListener(this);
        tab.setOnClickListener(this);

        text.addTextChangedListener(this);
        text.setOnFocusChangeListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.arrow_up:
                new Connection().execute(IP, Service.KEYBOARD_UP);
                break;
            case R.id.arrow_down:
                new Connection().execute(IP, Service.KEYBOARD_DOWN);
                break;
            case R.id.tab_left:
                new Connection().execute(IP, Service.BROWSER_TAB_NAV_LEFT);
                break;
            case R.id.tab_right:
                new Connection().execute(IP, Service.BROWSER_TAB_NAV_RIGHT);
                break;
            case R.id.new_tab:
                new Connection().execute(IP, Service.BROWSER_NEW_TAB);
                break;
            case R.id.close_tab:
                new Connection().execute(IP, Service.BROWSER_CLOSE_TAB);
                break;
            case R.id.search:
                new Connection().execute(IP, Service.KEYBOARD_ENTER);
                break;
            case R.id.alt_tab:
                new Connection().execute(IP, Service.KEYBOARD_ALT_TAB);
                break;
            case R.id.enter:
                new Connection().execute(IP, Service.KEYBOARD_ENTER);
                break;
            case R.id.tab:
                new Connection().execute(IP, Service.KEYBOARD_TAB);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(i1 > i2)
            new Connection().execute(IP, Service.KEYBOARD_BACKSPACE);
        else
            new Connection().execute(IP, Service.KEYBOARD_TEXT+charSequence.charAt(i1));
        Log.v("textchange", charSequence.toString()+" "+i+" "+i1+" "+i2);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        new Connection().execute(IP, Service.BROWSER_SEARCH);
    }
}
