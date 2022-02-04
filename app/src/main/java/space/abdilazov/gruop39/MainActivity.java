package space.abdilazov.gruop39;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import space.abdilazov.gruop39.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean startApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        Prefs prefs = new Prefs(this);

        if (!prefs.isBoardShown()) {
            navController.navigate(R.id.boardFragment);
        }

        openBoardFragment(navController);
        setVisibilityNavView(navController);

    }

    private void setVisibilityNavView(NavController navController) {
        ArrayList<Integer> listFragment = new ArrayList<>();
        listFragment.add(R.id.boardFragment);
        listFragment.add(R.id.newsFragment);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (listFragment.contains(destination.getId())) {
                binding.navView.setVisibility(View.GONE);
            } else {
                binding.navView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void openBoardFragment(NavController navController) {
        startApp = true;
        if (startApp){
            navController.navigate(R.id.boardFragment);
        }
        else startApp = false;
    }
    }