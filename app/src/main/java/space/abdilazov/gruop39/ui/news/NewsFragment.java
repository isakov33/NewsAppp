package space.abdilazov.gruop39.ui.news;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import java.text.SimpleDateFormat;
import java.util.Date;
import space.abdilazov.gruop39.R;
import space.abdilazov.gruop39.databinding.FragmentNewsBinding;
import space.abdilazov.gruop39.models.News;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveNews();
    }

    private void saveNews() {
        binding.btnSave.setOnClickListener(v -> save());
    }

    private void save() {
        long timeMillis = System.currentTimeMillis();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Date resultDate = new Date(timeMillis);
        String title = binding.editText.getText().toString();
        News news = new News(title, resultDate);
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}