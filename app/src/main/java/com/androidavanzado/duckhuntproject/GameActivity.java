package com.androidavanzado.duckhuntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import static com.androidavanzado.duckhuntproject.common.Constantes.EXTRA_NICK;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView tvCounterDucks, tvTimer, tvNick;
    ImageView ivDuck;
    int counter = 0;
    int anchoPantalla;
    int altoPantalla;
    Random aleatorio;
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViewComponent();
        eventos();
        initPantalla();
        moveDuck();
        initCuentaAtras();
    }

    private void initCuentaAtras() {
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                long segundosRestantes = millisUntilFinished / 1000;
                tvTimer.setText(segundosRestantes + "s");
            }

            public void onFinish() {
                tvTimer.setText("0s");
                gameOver = true;
                mostrarDialogoGameOver();
            }
        }.start();

    }

    private void mostrarDialogoGameOver() {
        // 1. Instantiate an AlertDialog.Builder with its constructor.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics.
        builder.setMessage("Haz conseguido cazar " + counter + " patos")
                .setTitle("Game Over");

        // Add the buttons.
        builder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User taps OK button.
                counter = 0;
                tvCounterDucks.setText("0");
                gameOver = false;
                initCuentaAtras();
                moveDuck();
            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancels the dialog.
                dialog.dismiss();
                finish();
            }
        });

        // 3. Get the AlertDialog.
        AlertDialog dialog = builder.create();

        //Mostrar el dialog
        dialog.show();
    }

    private void initPantalla() {
        //Obtener el tamaño de la pantalla del dispositivo en el que estamos ejecutando la app
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        anchoPantalla = size.x;
        altoPantalla = size.y;

        //Inicializamos el objeto para generar número aleatorios
        aleatorio = new Random();
    }

    private void eventos() {
        ivDuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gameOver){
                    counter++;
                    tvCounterDucks.setText(String.valueOf(counter));

                    ivDuck.setImageResource(R.drawable.duck_clicked);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ivDuck.setImageResource(R.drawable.duck);
                            moveDuck();
                        }
                    }, 500);
                }
            }
        });
    }

    private void moveDuck() {
        int min = 0;
        int maximoX = anchoPantalla - ivDuck.getWidth();
        int maximoY = altoPantalla - ivDuck.getHeight();

        //Genramos 2 números aleatorios, uno para la coordenada x y otro para la coordenada Y
        int randomX= aleatorio.nextInt(((maximoX - min) +1) + min);
        int randomY= aleatorio.nextInt(((maximoY - min) +1) + min);

        //Utilizamos los números aleatorios para mover el pato a esa posición
        ivDuck.setX(randomX);
        ivDuck.setY(randomY);
    }

    private void initViewComponent() {
        tvCounterDucks = findViewById(R.id.textViewCounter);
        tvTimer =  findViewById(R.id.textViewTimer);
        tvNick = findViewById(R.id.textViewNick);
        ivDuck = findViewById(R.id.imageViewDuck);

        //cambiar tipo de fuente
        Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
        tvCounterDucks.setTypeface(typeface);
        tvTimer.setTypeface(typeface);
        tvNick.setTypeface(typeface);

        //Extras: obtener nick y setear en TextView
        Bundle extras = getIntent().getExtras();
        String nick = extras.getString(EXTRA_NICK);
        tvNick.setText(nick);
    }
}