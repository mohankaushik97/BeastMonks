package xyz.projectaloha.zerodegreebeasts.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import xyz.projectaloha.zerodegreebeasts.Database.BeastDatabase;
import xyz.projectaloha.zerodegreebeasts.Entities.Exercise;
import xyz.projectaloha.zerodegreebeasts.Entities.Mapping;
import xyz.projectaloha.zerodegreebeasts.R;


public class EInWorkoutAdapter extends RecyclerView.Adapter<EInWorkoutAdapter.ViewHolder> {

    private static final String TAG = "EInWorkoutAdapter";
    Context context;
    BeastDatabase db;
    private MultiSpinnerSearch multiSpinnerSearch;




    ArrayList<Exercise> exercisesInWorkout = new ArrayList<>();
    ArrayList<Mapping> mapping = new ArrayList<>();
    ArrayList<Exercise> allExercises = new ArrayList<>();

    public EInWorkoutAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise_in_workout, parent, false);

        context = view.getContext();

        db = BeastDatabase.getInstance(context);


        final List<KeyPairBoolData> listArray0 = new ArrayList<>();
        for (int i = 0; i < allExercises.size();
             i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(allExercises.get(i).getName());
            h.setSelected(false);
            listArray0.add(h);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtExercise.setText(exercisesInWorkout.get(position).getName());

        holder.txtCategory.setText(exercisesInWorkout.get(position).getCategory());

        holder.imgExercise.setImageURI(Uri.parse(exercisesInWorkout.get(position).getImgUrl()));

        // TODO: 10/3/21 Write new Queries and abstract classes to get PrevCounts
        // TODO: 10/3/21 Take the prevCount information from the mapping table and display it in the current adapter.


    }

    @Override
    public int getItemCount() {
        return exercisesInWorkout.size();
    }

    public void setMapping(ArrayList<Mapping> mapping) {
        this.mapping = mapping;
    }

    public void setExercisesInWorkout(ArrayList<Exercise> exercisesInWorkout) {
        this.exercisesInWorkout = exercisesInWorkout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgExercise, dropDown, dropUp;
        TextView txtExercise, txtCategory, txtPrevSets, txtPrevReps, txtPrevWeights;
        EditText edtSets, edtReps, edtWeights;
        MaterialCardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgExercise.findViewById(R.id.imgExerciseInWorkout);
            dropDown.findViewById(R.id.btnDropDown);
            dropUp.findViewById(R.id.btnDropUp);

            txtExercise.findViewById(R.id.txtExerciseInWorkout);
            txtCategory.findViewById(R.id.txtCategoryInWorkout);
            txtPrevReps.findViewById(R.id.txtPrevReps);
            txtPrevSets.findViewById(R.id.txtPrevSets);
            txtPrevWeights.findViewById(R.id.txtPrevWeights);

            edtReps.findViewById(R.id.edtReps);
            edtSets.findViewById(R.id.edtSets);
            edtWeights.findViewById(R.id.edtWeights);

            parent.findViewById(R.id.EInWParent);
        }
    }


}
