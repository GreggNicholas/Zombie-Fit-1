package com.example.zombiefit.View;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.zombiefit.Fragment.SplashFragment;
import com.example.zombiefit.Fragment.WorkoutListFragment;
import com.example.zombiefit.Model.ListFragment.WorkoutInnerObject;
import com.example.zombiefit.Model.ListFragment.WorkoutListWrapper;
import com.example.zombiefit.R;
import com.example.zombiefit.Service.ZFitnessRetrofitSingleton;
import com.example.zombiefit.Service.ZFitnessService;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SplashFragment.OnFragmentInteractionListener {
    private String splashImage;
    private String splashTitle;
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splashFragmentLauncher();
        workoutListFragmentLauncher();
        // retrofitCall();

//        detailedExerciseFragmentLauncher();

    }


    private void splashFragmentLauncher() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainactivity_container, SplashFragment.getInstance(splashImage, splashTitle))
                .commit();

    }

    private void workoutListFragmentLauncher() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mainactivity_container, WorkoutListFragment.newInstance())
                .commit();
    }

//    private void detailedExerciseFragmentLauncher() {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.mainactivity_container, ExerciseDetailedFragment.getInstance()
//                        .commit();
//    }


    private void retrofitCall() {
        Retrofit retrofit = ZFitnessRetrofitSingleton.getInstance();
        final ZFitnessService service = retrofit.create(ZFitnessService.class);
        service.getListOfWorkouts().enqueue(new Callback<WorkoutListWrapper>() {
            @Override
            public void onResponse(Call<WorkoutListWrapper> call, Response<WorkoutListWrapper> response) {
                Log.d(TAG, "onResponse: " + response.body().getWorkoutlist().get(1).getImage());

                final List<WorkoutInnerObject> workoutList = new LinkedList<>();
                for (int i = 0; i < response.body().getWorkoutlist().size(); i++) {
//                    workoutList.add(0,response.body().getWorkoutlist().get(0).getImage().tp)
//                    workoutList.add(WorkoutListFragment.newInstance();
                }

            }

            @Override
            public void onFailure(Call<WorkoutListWrapper> call, Throwable t) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_LONG).show();
            }
        });

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


    @Override
    public void onFragmentInteraction() {

    }
}
