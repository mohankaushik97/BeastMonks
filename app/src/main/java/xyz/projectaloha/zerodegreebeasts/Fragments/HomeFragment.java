package xyz.projectaloha.zerodegreebeasts.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import xyz.projectaloha.zerodegreebeasts.Adapters.WorkoutAdapter;
import xyz.projectaloha.zerodegreebeasts.Database.BeastDatabase;
import xyz.projectaloha.zerodegreebeasts.Entities.Workout;
import xyz.projectaloha.zerodegreebeasts.R;

public class HomeFragment extends Fragment implements WorkoutDialogFragment.OnInputListener, WorkoutAdapter.DeleteWorkout {

    private FloatingActionButton floatButton;
    BeastDatabase db;
    Context context;
    RecyclerView recyclerView;
    WorkoutAdapter adapter;
    private static final String TAG = "HomeFragment";

    ArrayList<Workout> workouts;
    private static final int DIALOG_REQUEST = 101;

    private void initializeVariables(View view) {
        context = view.getContext();

        db = BeastDatabase.getInstance(context);

        recyclerView = view.findViewById(R.id.workoutRecycler);
        floatButton = view.findViewById(R.id.btnAddWorkout);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initializeVariables(view);
        settingTheAdapter();

        new GetAllWorkouts().execute();

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkoutDialogFragment dialogFragment = new WorkoutDialogFragment();
                dialogFragment.setTargetFragment(HomeFragment.this, DIALOG_REQUEST);
                if (getFragmentManager() != null) {
                    dialogFragment.show(getFragmentManager(), "WorkoutDialogFragment");
                }
            }
        });
        return view;
    }

    private void settingTheAdapter() {
        adapter = new WorkoutAdapter(context, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public void InsertWorkout(String workoutName) {
        new insertingWorkouts().execute(workoutName);
        workouts = (ArrayList<Workout>) db.workoutDao().getAllWorkouts();
    }

    @Override
    public void onDeleteWorkoutResult(int id) {
        new deleteById().execute(id);
        new GetAllWorkouts().execute();
        adapter.notifyDataSetChanged();
    }

    private class GetAllWorkouts extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            workouts = (ArrayList<Workout>) db.workoutDao().getAllWorkouts();
            Log.d(TAG, "doInBackground GetAllWorkouts: Workouts size: " + workouts.size());
            adapter.setWorkouts(workouts);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class deleteById extends AsyncTask<Integer, Void, Void> {


        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Integer... integers) {
            Workout tempWorkout = db.workoutDao().getWorkoutById(integers[0]);
            db.workoutDao().deleteSingleWorkout(tempWorkout);
            Log.d(TAG, "doInBackground deleteById: Delete this workout: " + tempWorkout.getWorkout());
            new GetAllWorkouts().execute();
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class insertingWorkouts extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            Workout workout = new Workout(strings[0]);
            db.workoutDao().insertSingleWorkout(workout);
            Log.d(TAG, "sendInput insertingWorkouts: Workout Inserted: " + workout.getWorkout());
            new GetAllWorkouts().execute();
            return null;
        }
    }
}