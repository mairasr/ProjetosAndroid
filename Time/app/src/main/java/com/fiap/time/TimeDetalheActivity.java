package com.fiap.time;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.fiap.time.model.Time;
import com.squareup.picasso.Picasso;

public class TimeDetalheActivity extends AppCompatActivity {

    private ImageView imagem;
    private TextView nome;
    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_detalhe);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        imagem = (ImageView)findViewById(R.id.imagem);
        nome = (TextView)findViewById(R.id.nome);

        if (getIntent() != null) {
            Time t = (Time)getIntent().getParcelableExtra("time");

            Picasso.with(getApplicationContext())
                    .load(t.getEscudo())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imagem);
            collapsingToolbar.setTitle(t.getNome());
            nome.setText(t.getNome());
        }
    }
}
