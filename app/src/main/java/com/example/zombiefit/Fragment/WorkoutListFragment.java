package com.example.zombiefit.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class WorkoutListFragment extends Fragment {
    private static final String TAG = "List";
    private static final String IMAGE_WORKOUT_KEY = "Getafterthatimage";
    private static final String TITLE_WORKOUT_KEY = "Getafterthattitle";
    private static final String DESCRIPTION_WORKOUT_KEY = "Getafterthatdescription";

    private static String workoutTitle;
    private static String workoutDescription;
    private static String workoutImage;

    private String workoutImageView;
    private String workoutTitleView;
    private String workoutDescriptView;

    private RecyclerView recyclerView;
    private WorkoutListAdapter adapter;
    private List<WorkoutInnerObject> workoutInnerObjects;


    public static WorkoutListFragment newInstance() {
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
            workoutTitleView = getArguments().getString(TITLE_WORKOUT_KEY);
            workoutDescriptView = getArguments().getString(DESCRIPTION_WORKOUT_KEY);
            workoutImageView = getArguments().getString(IMAGE_WORKOUT_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_zlistworkouts, container, false);
        recyclerView = view.findViewById(R.id.fragment_recyclerview);


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Retrofit retrofit = ZFitnessRetrofitSingleton.getInstance();
        final ZFitnessService service = retrofit.create(ZFitnessService.class);
        service.getListOfWorkouts().enqueue(new Callback<WorkoutListWrapper>() {
            @Override
            public void onResponse(Call<WorkoutListWrapper> call, Response<WorkoutListWrapper> response) {
                final List<WorkoutInnerObject> workoutLists = response.body().getWorkoutlist();

                adapter = new WorkoutListAdapter(workoutLists);
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new WorkoutListAdapter.onItemClickListener() {
                    @Override
                    public void onItemViewClick(int position) {
                        getFragmentManager().beginTransaction()
                                .replace(R.id.mainactivity_container, ExerciseDetailedFragment.getInstance("Apple", "https://www.dialfredo.com/wp-content/uploads/2015/05/redapplepic.jpg"))
                                .addToBackStack("Listview")
                                .commit();


                    }
                });
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<WorkoutListWrapper> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return view;
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        listener = null;
//    }

//    public interface onFragmentInteractionListener {
//        void onFragmentInteraction(String title, String description, String image);
//    }
}
