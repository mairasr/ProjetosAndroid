package com.fiap.time.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fiap.time.R;
import com.fiap.time.model.Time;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.TimeViewHolder>{

    private List<Time> times;
    private Context context;
    private TimeOnClickListener timeListener;


    /*********************************************************************************************/
    // ViewHolder
    public static class TimeViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvEstado;
        TextView tvAnoFundacao;
        ImageView ivEscudo;

        ProgressBar progress;

        public TimeViewHolder(View view) {
            super(view);
            tvNome = (TextView) view.findViewById(R.id.tvNome);
            ivEscudo = (ImageView) view.findViewById(R.id.ivEscudo);
            tvEstado = (TextView) view.findViewById(R.id.tvEstado);
            tvAnoFundacao = (TextView) view.findViewById(R.id.tvAnoFundacao);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);

        }
    }

    /**Interface OnClickListener - Acao a ser executada ao clicar em um item da lista - Devera ser implementada na
     Activity ou Fragment **/
    public interface TimeOnClickListener {
        public void onClickTime(View view, int idx);
    }

    /**Inteface - Acao a ser executada ao clicar no botao options - Devera ser implementada na Activity
     ou Fragment **/
    public interface OptionsOnClickListener {
        public void onClickOptions(View view, int idx);
    }

    /*********************************************************************************************/
/*
    public TimeListAdapter(Context context, List<Time> times) {
        this.context = context;
        this.times = times;
    }
*/

    public TimeListAdapter(Context context, List<Time> times, TimeOnClickListener timeOnClickListener) {
        this.context = context;
        this.times = times;
        this.timeListener = timeOnClickListener;
    }

    public Time getItem(int position) {
        return this.times.get(position);
    }

    @Override
    public int getItemCount() {
        return this.times != null ? this.times.size() : 0;
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_time, viewGroup,
                false);
        return new TimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TimeViewHolder holder, final int position) {

        Time t = times.get(position);

        holder.tvNome.setText(t.getNome());
        holder.tvEstado.setText(t.getEstado());
        holder.tvAnoFundacao.setText(t.getAnoFundacao());

        Picasso.with(context)
                .load(t.getEscudo())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivEscudo);

        holder.progress.setVisibility(View.VISIBLE);

        if (timeListener != null) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    timeListener.onClickTime(holder.itemView, position);
                }
            });
        }
        holder.progress.setVisibility(View.GONE);
    }



}
