package xyz.projectaloha.zerodegreebeasts.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import xyz.projectaloha.zerodegreebeasts.Adapters.ExerciseAdapter;
import xyz.projectaloha.zerodegreebeasts.Database.BeastDatabase;
import xyz.projectaloha.zerodegreebeasts.Entities.Exercise;
import xyz.projectaloha.zerodegreebeasts.R;

public class ExerciseActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView txtExercise, txtCategory;
    private BeastDatabase db;
    private static final String TAG = "ExerciseActivity";
    private Exercise exercise;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        imageView = findViewById(R.id.imageView);
        txtCategory = findViewById(R.id.txtCategoryActivity);
        txtExercise = findViewById(R.id.txtExercise);
        context = getApplicationContext();

        db = BeastDatabase.getInstance(context);

        Intent intent = getIntent();
        int id = intent.getIntExtra(ExerciseAdapter.EXERCISE_ID, -1);
        Log.d(TAG, "onCreate: " + id);
        if (id != -1) {

            new GetExerciseById().execute(id);

        }

    }

    @SuppressLint("StaticFieldLeak")
    public class GetExerciseById extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            exercise = db.exerciseDao().getExerciseById(integers[0]);
//            Log.d(TAG, "doInBackground: the exercise name is: " + exercise.getName());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (null != exercise) {
                txtExercise.setText(exercise.getName());
                txtCategory.setText(exercise.getCategory());

                Glide.with(ExerciseActivity.this)
                        .load(exercise.getImgUrl())
                        .into(imageView);
            } else {
                Log.d(TAG, "onCreate: Exercise is null");
            }
        }
    }
}