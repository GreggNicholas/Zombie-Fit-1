package com.example.zombiefit.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zombiefit.Controller.WorkoutListAdapter;
import com.example.zombiefit.Model.ListFragment.WorkoutInnerObject;
import com.example.zombiefit.Model.ListFragment.WorkoutListWrapper;
import com.example.zombiefit.R;
import com.example.zombiefit.Service.ZFitnessRetrofitSingleton;
import com.example.zombiefit.Service.ZFitnessService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


final public class WorkoutListFragment extends Fragment {
    private static final String TAG = "List";
    private static final String IMAGE_WORKOUT_KEY = "Getafterthatimage";
    private static final String TITLE_WORKOUT_KEY = "Getafterthattitle";
    private static final String DESCRIPTION_WORKOUT_KEY = "Getafterthatdescription";

    private static String workoutTitle;
    private static String workoutDescription;
    private static String workoutImage;

    private ImageView workoutImageView;
    private TextView workoutTitleView;
    private TextView workoutDescriptView;

    private RecyclerView recyclerView;
    private WorkoutListAdapter adapter;
    private List<WorkoutInnerObject> workoutInnerObjects;
    private onFragmentInteractionListener listener;


    public static WorkoutListFragment newInstance(String title, String description, String image) {
        WorkoutListFragment fragment = new WorkoutListFragment();
        Bundle args = new Bundle();
        args.putString(TITLE_WORKOUT_KEY, workoutTitle);
        args.putString(DESCRIPTION_WORKOUT_KEY, workoutDescription);
        args.putString(IMAGE_WORKOUT_KEY, workoutImage);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof onFragmentInteractionListener) {
//            listener = (onFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + "No interface implemented");
//        }
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            workoutTitle = getArguments().getString(TITLE_WORKOUT_KEY);
            workoutDescription = getArguments().getString(DESCRIPTION_WORKOUT_KEY);
            workoutImage = getArguments().getString(IMAGE_WORKOUT_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_listworkouts, container, false);
        recyclerView = view.findViewById(R.id.fragment_recyclerview);


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Retrofit retrofit = ZFitnessRetrofitSingleton.getInstance();
        final ZFitnessService service = retrofit.create(ZFitnessService.class);
        service.getListOfWorkouts().enqueue(new Callback<WorkoutListWrapper>() {
            @Override
            public void onResponse(Call<WorkoutListWrapper> call, Response<WorkoutListWrapper> response) {
                final List<WorkoutInnerObject> workoutLists = response.body().getWorkoutlist();

                adapter = new WorkoutListAdapter(workoutLists, listener);
                adapter.setItems(workoutLists);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<WorkoutListWrapper> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), getResources().getString(R.string.retrofit_onfailure), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        listener = null;
//    }

    public interface onFragmentInteractionListener {
        void onWorkoutListFragmentInteraction(String title, String description, String image);

        void onItemViewClick(int position);
    }
}
