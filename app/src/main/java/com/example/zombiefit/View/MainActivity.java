package com.example.zombiefit.View;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.zombiefit.Fragment.ExerciseDetailedFragment;
import com.example.zombiefit.Fragment.WorkoutListFragment;
import com.example.zombiefit.OnFragmentInteractionListener;
import com.example.zombiefit.R;
import com.example.zombiefit.Service.RetrofitSingleton;
import com.example.zombiefit.Service.Service;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private static final String TAG = "Main";
    private final Retrofit retrofit = RetrofitSingleton.getInstance();
    private final Service service = retrofit.create(Service.class);

    private View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workoutListFragmentLauncher();
        mainView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        mainView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void onDetailedFragmentInteraction(String title, String image, String description, String youTube, String congrats) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainactivity_container, ExerciseDetailedFragment.newInstance(title, image, description
                        , youTube, congrats)).addToBackStack(null).commit();
    }

    private void workoutListFragmentLauncher() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainactivity_container, WorkoutListFragment.newInstance())
                .commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        menu.add(0, 0, 0, menuItemWithIcon(getResources().getDrawable(R.drawable.megaoctocattiny2), getResources().getString(R.string.see_my_github)));
        menu.add(0, 1, 1, menuItemWithIcon(getResources().getDrawable(R.drawable.linkedintiny), getResources().getString(R.string.visit_my_linkedin)));
        return true;
    }

    private CharSequence menuItemWithIcon(Drawable r, String title) {
        r.setBounds(0, 0, r.getIntrinsicHeight(), r.getIntrinsicWidth());
        SpannableString spannableString = new SpannableString("  " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BASELINE);
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Uri uri1 = Uri.parse(getResources().getString(R.string.Github_link));
                Intent i1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(i1);
                break;
            case 1:
                Uri uri2 = Uri.parse(getResources().getString(R.string.linkedin_link));
                Intent i2 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(i2);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }


}
