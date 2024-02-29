package com.androidavanzado.duckhuntproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import static com.androidavanzado.duckhuntproject.common.Constantes.EXTRA_NICK;

public class LoginActivity extends AppCompatActivity {
    EditText etNick;
    Button btnStart;
    String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNick = findViewById(R.id.editTextNick);
        btnStart = findViewById(R.id.buttonStart);

        //cambiar tipo de fuente
        Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
        etNick.setTypeface(typeface);
        btnStart.setTypeface(typeface);

        //Eventos: evento click
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nick = etNick.getText().toString();

                if (nick.isEmpty()) {
                    etNick.setError("El nombre de usuario es obligatorio");
                }else if(nick.length() < 3){
                    etNick.setError("Debe tener al menos 3 caracteres");
                } else {
                    Intent i = new Intent(LoginActivity.this, GameActivity.class);
                    i.putExtra(EXTRA_NICK, nick);
                    startActivity(i);
                }
            }
        });
    }
}