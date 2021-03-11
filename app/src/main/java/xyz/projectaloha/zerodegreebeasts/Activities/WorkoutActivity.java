package xyz.projectaloha.zerodegreebeasts.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import xyz.projectaloha.zerodegreebeasts.Adapters.WorkoutAdapter;
import xyz.projectaloha.zerodegreebeasts.Database.BeastDatabase;
import xyz.projectaloha.zerodegreebeasts.Entities.Exercise;
import xyz.projectaloha.zerodegreebeasts.Entities.Mapping;
import xyz.projectaloha.zerodegreebeasts.Entities.Workout;
import xyz.projectaloha.zerodegreebeasts.Fragments.EInDialogFragment;
import xyz.projectaloha.zerodegreebeasts.R;

public class WorkoutActivity extends AppCompatActivity implements EInDialogFragment.OnInputListenerEInW {

    FloatingActionButton floatingActionButton;
    TextView txtDefault;
    BeastDatabase db;
    Context context;
    ArrayList<Exercise> exercises;
    Workout workout;
    int workoutId;
    private static final String TAG = "WorkoutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        initializeVariables();

        new GetExercises().execute();


        Intent intent = getIntent();

        workoutId = intent.getIntExtra(WorkoutAdapter.WORKOUT_ID, -1);
        Toast.makeText(context, "You are in workout with Id: " + workoutId, Toast.LENGTH_SHORT).show();

        new GetWorkoutById().execute(workoutId);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EInDialogFragment dialogFragment = new EInDialogFragment(workoutId);
                dialogFragment.show(getSupportFragmentManager(), "EInDialogFragment");
            }
        });
    }

    private void initializeVariables() {
        context = this;
        floatingActionButton = findViewById(R.id.floatAddToWorkout);
        db = BeastDatabase.getInstance(context);
        txtDefault = findViewById(R.id.txtDefault);
    }

    @Override
    public ArrayList<Exercise> exerciseList() {
        return exercises;
    }


    @Override
    public void mapExerciseToWorkout(ArrayList<Mapping> mappings) {
        // TODO: 11/3/21 Need to insert the mapping into the database table of mapping.
    }


    private class GetExercises extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            exercises = (ArrayList<Exercise>) db.exerciseDao().getAllExercisesinOrder();
            return null;
        }
    }

    private class GetWorkoutById extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            workout = db.workoutDao().getWorkoutById(integers[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            txtDefault.setText("This is workout: " + workout.getWorkout());
            txtDefault.setVisibility(View.VISIBLE);
        }
    }

    private class InsertMultipleMappings extends AsyncTask<ArrayList<Mapping>, Void ,Void>{

        @Override
        protected Void doInBackground(ArrayList<Mapping>... arrayLists) {
            db.mappingDao().insertMultipleMappings(arrayLists[0]);
            return null;
        }
    }
}