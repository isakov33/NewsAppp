package space.abdilazov.gruop39.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import space.abdilazov.gruop39.R;
import space.abdilazov.gruop39.databinding.ItemBoardBinding;
import space.abdilazov.gruop39.interfaces.OnBoardStartClickListener;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private final String[] title = new String[]{"1", "2", "3"};
    private final String[] decs = new String[]{"Description1", "Description2", "Description3"};
    private final int[] image = new int[]{R.drawable.hi,R.drawable.next,R.drawable.nextpage,R.drawable.theend};
    private ItemBoardBinding binding;


    public void setClickListener(OnBoardStartClickListener clickListener) {
        this.clickListener = clickListener;
    }


    private OnBoardStartClickListener clickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return title.length;

    }



    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding.ptnStart.setOnClickListener(v -> clickListener.onStartClick());
        }

        public void bind(int position) {
            binding.textTitle.setText(title[position]);
            binding.imageView.setImageResource(image[position]);
            binding.textDesc.setText(decs[position]);
            if (position == title.length - 1) {
                binding.ptnStart.setVisibility(View.VISIBLE);
            } else {
                binding.ptnStart.setVisibility(View.INVISIBLE);
            }

        }
    }

}
