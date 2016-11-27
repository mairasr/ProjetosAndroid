package com.fiap.carango.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fiap.carango.R;
import com.fiap.carango.model.Carro;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CarroListAdapter extends RecyclerView.Adapter<CarroListAdapter.CarrosViewHolder>{

    private List<Carro> carros;
    private Context context;
    private CarroOnClickListener carroListener;


    /*********************************************************************************************/
    // ViewHolder
    public static class CarrosViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvDescricao;
        ImageView ivCarro;

        ProgressBar progress;

        public CarrosViewHolder(View view) {
            super(view);
            tvNome = (TextView) view.findViewById(R.id.tvNome);
            ivCarro = (ImageView) view.findViewById(R.id.ivCarro);
            tvDescricao = (TextView) view.findViewById(R.id.tvDescricao);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);

        }
    }

    /**Interface OnClickListener - Acao a ser executada ao clicar em um item da lista - Devera ser implementada na
     Activity ou Fragment **/
    public interface CarroOnClickListener {
        public void onClickCarro(View view, int idx);
    }

    /**Inteface - Acao a ser executada ao clicar no botao options - Devera ser implementada na Activity
     ou Fragment **/
    public interface OptionsOnClickListener {
        public void onClickOptions(View view, int idx);
    }

    /*********************************************************************************************/
/*
    public CarroListAdapter(Context context, List<Carro> carros) {
        this.context = context;
        this.carros = carros;
    }
*/

    public CarroListAdapter(Context context, List<Carro> carros, CarroOnClickListener carroOnClickListener) {
        this.context = context;
        this.carros = carros;
        this.carroListener = carroOnClickListener;
    }

    public Carro getItem(int position) {
        return this.carros.get(position);
    }

    @Override
    public int getItemCount() {
        return this.carros != null ? this.carros.size() : 0;
    }

    @Override
    public CarrosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_carro, viewGroup,
                false);
        return new CarrosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CarrosViewHolder holder, final int position) {

        Carro c = carros.get(position);

        holder.tvNome.setText(c.getNome());
        holder.tvDescricao.setText(c.getDesc());

        Picasso.with(context)
                .load(c.getFoto())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivCarro);

        holder.progress.setVisibility(View.VISIBLE);

        if (carroListener != null) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    carroListener.onClickCarro(holder.itemView, position);
                }
            });
        }
        holder.progress.setVisibility(View.GONE);
    }



}
