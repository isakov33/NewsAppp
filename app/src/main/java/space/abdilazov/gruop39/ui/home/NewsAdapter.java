package space.abdilazov.gruop39.ui.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import space.abdilazov.gruop39.R;
import space.abdilazov.gruop39.databinding.ItemRvBinding;
import space.abdilazov.gruop39.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> newsList;
    private ItemRvBinding binding;
    private OnItemClick listener;

    @SuppressLint("NotifyDataSetChanged")
    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    public void setListener(OnItemClick listener) {
        this.listener = listener;
    }

    public void setNews(News news) {
        this.newsList = new ArrayList<>();
        newsList.add(news);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRvBinding.inflate(LayoutInflater
                        .from(parent.getContext()), parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull ItemRvBinding binding) {
            super(binding.getRoot());
        }

        public void onBind(News news) {
            binding.tvTitle.setText(news.getTitle());
            binding.tvDate.setText(String.valueOf(news.getCreateAd()));
            initListeners(news);
        }

        private void initListeners(News news) {
            binding.getRoot().setOnClickListener(v -> listener.onClick(news));

            binding.getRoot().setOnLongClickListener(v -> {
                listener.onLongClick(getAdapterPosition());
                return true;
            });
        }
    }

    interface OnItemClick {
        void onClick(News news);
        void onLongClick(int position);

    }
}
