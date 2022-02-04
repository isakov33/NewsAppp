package space.abdilazov.gruop39.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import space.abdilazov.gruop39.R;
import space.abdilazov.gruop39.data.locals.DataNews;
import space.abdilazov.gruop39.databinding.FragmentHomeBinding;
import space.abdilazov.gruop39.models.News;

public class HomeFragment extends Fragment implements NewsAdapter.OnItemClick {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;
    private DataNews news;
    private ArrayList<News> arrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        initFragmentResultListener();
        initRv();
    }

    private void initRv() {
        adapter = new NewsAdapter();
        news = new DataNews();
        adapter.setNewsList(arrayList);
        adapter.setListener(this);
        binding.newsRv.setAdapter(adapter);
    }

    private void initListeners() {
        binding.fab.setOnClickListener(v -> openFragment());
    }

    private void initFragmentResultListener() {
        getParentFragmentManager().setFragmentResultListener("rk_news",
                getViewLifecycleOwner(), (requestKey, result) -> {
                    News news = (News) result.getSerializable("news");
                    arrayList.add(news);
                    adapter.setNewsList(arrayList);
                    binding.newsRv.setAdapter(adapter);
                });
    }


    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }


    @Override
    public void onClick(News news) {
        Toast.makeText(getContext(), news.getTitle(), Toast.LENGTH_SHORT).show();
        EditDataFragment(news);
    }


    @Override
    public void onLongClick(int position) {
        Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                    arrayList.remove(position);
                    adapter.setNewsList(arrayList);
                    binding.newsRv.setAdapter(adapter);
    }

    private void EditDataFragment(News news) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}