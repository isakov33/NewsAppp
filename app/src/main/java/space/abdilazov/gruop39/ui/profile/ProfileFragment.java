package space.abdilazov.gruop39.ui.profile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import space.abdilazov.gruop39.R;
import space.abdilazov.gruop39.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ActivityResultLauncher<String> mGetContent;
    private boolean change;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initActivityResultLauncher();
    }

    private void initActivityResultLauncher() {
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    Glide.with(binding.image).load(uri).circleCrop().into(binding.image);
                    change = true;
                });
        binding.image.setOnClickListener(view -> {
            if (change) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setNeutralButton("Заменить", ((dialog, which) -> mGetContent.launch("image/*")));
                builder.setPositiveButton("Удалить", ((dialog, which) -> {
                    binding.image.setImageResource(R.drawable.neymar);
                }));
                @SuppressLint("InflateParams") ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.item_alert_dialog, null);
                builder.setView(constraintLayout);
                AlertDialog dialog = builder.create();
                dialog.show();
                change = false;
            } else {
                mGetContent.launch("image/*");
            }
        });
    }
}


