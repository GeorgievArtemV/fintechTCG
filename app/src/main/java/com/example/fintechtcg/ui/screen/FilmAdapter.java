package com.example.fintechtcg.ui.screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.fintechtcg.R;
import com.example.fintechtcg.model.Film;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    private OnItemClick onItemClick;
    private OnLongItemClick onLongItemClick;
    private List<String> name;
    private List<Film> filmList;
    private List<String> year;
    private List<String> image;

    public FilmAdapter(List<Film> filmList,/*, List<String> name, List<String> year, List<String> image,*/ OnItemClick onItemClick, OnLongItemClick onLongItemClick) {
        this.filmList = filmList;
        /*this.name = name;
        //this.description = description;
        this.year = year;
        this.image = image;*/
        this.onItemClick = onItemClick;
        this.onLongItemClick = onLongItemClick;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView textViewSecond;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageItemFilm);
            textView = itemView.findViewById(R.id.textItemFilm);
            textViewSecond = itemView.findViewById(R.id.previewMessage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            onItemClick.onItemClick(pos);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onLongItemClick != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            onLongItemClick.onLongItemClick(pos);
                        }
                    }return true;
                }
            });
            Shimmer shimmer = new Shimmer.AlphaHighlightBuilder()
                    .setDuration(1800)
                    .setBaseAlpha(0.7f)
                    .setHighlightAlpha(0.6f)
                    .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                    .setAutoStart(true)
                    .build();


            ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
            shimmerDrawable.setShimmer(shimmer);
        }
        public TextView getTextView() {
            return textView;
        }
        public ImageView getImageView(){ return imageView;}
        public TextView getTextViewSecond() {return textViewSecond;}
        public ShimmerDrawable getShimmerDrawable() {
            Shimmer shimmer = new Shimmer.AlphaHighlightBuilder()
                    .setDuration(1800)
                    .setBaseAlpha(0.7f)
                    .setHighlightAlpha(0.6f)
                    .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                    .setAutoStart(true)
                    .build();
            ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
            shimmerDrawable.setShimmer(shimmer);

            return shimmerDrawable;
        }

    }
    @NonNull
    @Override
    public FilmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

                holder.getTextViewSecond().setText(filmList.get(position).getYear());
                holder.getTextView().setText(filmList.get(position).getNameRu());
                Glide.with(holder.imageView.getContext()).load(filmList.get(position).getPosterUrlPreview()).placeholder(holder.getShimmerDrawable()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

}
