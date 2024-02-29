package com.androidavanzado.duckhuntproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import static com.androidavanzado.duckhuntproject.common.Constantes.EXTRA_NICK;

public class GameActivity extends AppCompatActivity {

    TextView tvCounterDucks, tvTimer, tvNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvCounterDucks = findViewById(R.id.textViewCounter);
        tvTimer =  findViewById(R.id.textViewTimer);
        tvNick = findViewById(R.id.textViewNick);

        //Extras: obtener nick y setear en TextView
        Bundle extras = getIntent().getExtras();
        String nick = extras.getString(EXTRA_NICK);
        tvNick.setText(nick);
    }
}