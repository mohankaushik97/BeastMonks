package xyz.projectaloha.zerodegreebeasts.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import xyz.projectaloha.zerodegreebeasts.Activities.ExerciseActivity;
import xyz.projectaloha.zerodegreebeasts.Activities.WorkoutActivity;
import xyz.projectaloha.zerodegreebeasts.Database.BeastDatabase;
import xyz.projectaloha.zerodegreebeasts.Entities.Workout;
import xyz.projectaloha.zerodegreebeasts.R;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private ArrayList<Workout> workouts = new ArrayList<>();
    private Context context;
    public static final String WORKOUT_ID = "workoutId";
    private static final String TAG = "WorkoutAdapter";
    private BeastDatabase db;
    private final Fragment fragment;

    public interface DeleteWorkout {
        void onDeleteWorkoutResult(int id);
    }

    private DeleteWorkout deleteWorkout;

    public WorkoutAdapter(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout, parent, false);

        context = view.getContext();

        db = BeastDatabase.getInstance(context);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtWorkout.setText(workouts.get(position).getWorkout());

        String txtWorkout =workouts.get(position).getWorkout();

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WorkoutActivity.class);
                intent.putExtra(WORKOUT_ID, workouts.get(position).getId());

                context.startActivity(intent);
            }
        });

        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "Long Click on: " + workouts.get(position).getWorkout(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Are you sure you want to delete the workout: " + workouts.get(position).getWorkout())
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try {
                                    deleteWorkout = (DeleteWorkout) fragment;
                                    int id = workouts.get(position).getId();
                                    Log.d(TAG, "onClick: The Id of the deleted Workout is: " + id);
                                    deleteWorkout.onDeleteWorkoutResult(id);
                                } catch (ClassCastException e) {
                                    Log.e(TAG, "onClick: ClassCastException: "+ e.getMessage());
                                    e.getStackTrace();
                                }


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });

                builder.create().show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void setWorkouts(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtWorkout;
        private final MaterialCardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtWorkout = itemView.findViewById(R.id.txtWorkoutName);
            parent = itemView.findViewById(R.id.workout_parent);

        }

    }


}
