package xyz.projectaloha.zerodegreebeasts.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import xyz.projectaloha.zerodegreebeasts.Adapters.ExerciseAdapter;
import xyz.projectaloha.zerodegreebeasts.Database.BeastDatabase;
import xyz.projectaloha.zerodegreebeasts.Entities.Exercise;
import xyz.projectaloha.zerodegreebeasts.R;


public class CatelogFragment extends Fragment {


    BeastDatabase db;
    RecyclerView recyclerView;
    ExerciseAdapter adapter;
    Context context;
    ChipGroup chipGroup;
    Chip pushChip, pullChip, legsChip, absChip;
    Button btnChip;

    ArrayList<String> selectedChipData;
    ArrayList<Exercise> exercises;


    private void initialiseVariables(View view) {
        chipGroup = view.findViewById(R.id.filterChipGroup);

        absChip = view.findViewById(R.id.absChip);
        pushChip = view.findViewById(R.id.pushChip);
        pullChip = view.findViewById(R.id.pullChip);
        legsChip = view.findViewById(R.id.legsChip);

        btnChip = view.findViewById(R.id.btnChip);

        recyclerView = view.findViewById(R.id.recyclerView);

        context = view.getContext();
        db = BeastDatabase.getInstance(context);

        adapter = new ExerciseAdapter(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        selectedChipData = new ArrayList<>();
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_catelog, container, false);

        initialiseVariables(view);

        /**
         * Code to insert exercises from database into recyclerView
         * @param exercises
         */
        exercises = (ArrayList<Exercise>) db.exerciseDao().getAllExercisesinOrder();
        adapter.setExercises(exercises);


        /**
         * Code for filters to select the right filter.
         */
        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    selectedChipData.add(compoundButton.getText().toString());
                } else {
                    selectedChipData.remove(compoundButton.getText().toString());
                }
            }
        };

        legsChip.setOnCheckedChangeListener(checkedChangeListener);
        pullChip.setOnCheckedChangeListener(checkedChangeListener);
        pushChip.setOnCheckedChangeListener(checkedChangeListener);
        absChip.setOnCheckedChangeListener(checkedChangeListener);

        btnChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ArrayList<Exercise> exercisesByCategory = (ArrayList<Exercise>) db.exerciseDao().getExercisesByCategory(selectedChipData.get(0));
                    adapter.setExercises(exercisesByCategory);
                } catch (Exception e) {
                    adapter.setExercises(exercises);
                }
            }
        });


        return view;


    }


}
