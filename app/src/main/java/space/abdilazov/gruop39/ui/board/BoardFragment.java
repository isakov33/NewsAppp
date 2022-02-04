package space.abdilazov.gruop39.ui.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;
import space.abdilazov.gruop39.Prefs;
import space.abdilazov.gruop39.R;
import space.abdilazov.gruop39.databinding.FragmentBoardBinding;

public class BoardFragment extends Fragment {

    private BoardAdapter adapter;
    private FragmentBoardBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new BoardAdapter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        finish();
        btnNext();
        dots();
    }

    private void dots() {
        binding.indicator.setViewPager2(binding.viewPager);
    }

    private void btnNext() {
        binding.btnSkip.setOnClickListener(v -> close());
    }

    private void finish() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    private void initAdapter() {
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2)
                    binding.btnSkip.setVisibility(View.GONE);
                else
                    binding.btnSkip.setVisibility(View.VISIBLE);
            }
        });
        adapter.setClickListener(this::close);
    }

    private void close() {
        Prefs prefs = new Prefs(requireActivity());
        prefs.saveBoardState();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();

    }
}