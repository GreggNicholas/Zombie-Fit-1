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

import com.example.zombiefit.Fragment.ZListWorkoutsFragment;
import com.example.zombiefit.Model.ZWorkoutInnerObject;
import com.example.zombiefit.Model.ZWorkoutViewList;
import com.example.zombiefit.R;
import com.example.zombiefit.Service.ZFitnessRetrofitSingleton;
import com.example.zombiefit.Service.ZFitnessService;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements ZListWorkoutsFragment.onFragmentInteractionListener {

    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workoutListFragmentLauncher();

        // retrofitCall();

//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.mainactivity_container, ZDetailedFragment.getInstance("image"))
//                .commit();
    }

    private void workoutListFragmentLauncher() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainactivity_container, ZListWorkoutsFragment.newInstance())
                .commit();
    }


    private void retrofitCall() {
        Retrofit retrofit = ZFitnessRetrofitSingleton.getInstance();
        final ZFitnessService service = retrofit.create(ZFitnessService.class);
        service.getListOfWorkouts().enqueue(new Callback<ZWorkoutViewList>() {
            @Override
            public void onResponse(Call<ZWorkoutViewList> call, Response<ZWorkoutViewList> response) {
                Log.d(TAG, "onResponse: " + response.body().getData().get(1).getImage());

                final List<ZWorkoutInnerObject> workoutList = new LinkedList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
//                    workoutList.add(ZListWorkoutsFragment.newInstance();
                }

            }

            @Override
            public void onFailure(Call<ZWorkoutViewList> call, Throwable t) {

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
                Uri uri1 = Uri.parse("https://github.com/GreggNicholas");
                Intent i1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(i1);
                break;
            case 1:
                Uri uri2 = Uri.parse("https://www.linkedin.com/in/gregg-nicholas/");
                Intent i2 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(i2);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(String title, String description, String image) {

    }
}
