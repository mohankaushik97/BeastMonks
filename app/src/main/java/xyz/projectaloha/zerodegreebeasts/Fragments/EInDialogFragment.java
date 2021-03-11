package xyz.projectaloha.zerodegreebeasts.Fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerListener;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;

import java.util.ArrayList;
import java.util.List;

import xyz.projectaloha.zerodegreebeasts.Entities.Exercise;
import xyz.projectaloha.zerodegreebeasts.Entities.Mapping;
import xyz.projectaloha.zerodegreebeasts.Entities.Workout;
import xyz.projectaloha.zerodegreebeasts.R;

public class EInDialogFragment extends DialogFragment {

    public interface OnInputListenerEInW {
        ArrayList<Exercise> exerciseList();
        void mapExerciseToWorkout(ArrayList<Mapping> mappings);
    }

    private MultiSpinnerSearch multiSpinnerSearch;
    private Button btnAdd, btnCancel;
    private static final String TAG = "EInDialogFragment";
    private Context context;
    public OnInputListenerEInW onInputListenerEInW;
    private ArrayList<Exercise> allExercises;
    private ArrayList<Mapping> mappings;
    private Workout workout;
    private final int workoutId;

    public void setOnInputListenerEInW(OnInputListenerEInW onInputListenerEInW) {
        this.onInputListenerEInW = onInputListenerEInW;
    }

    public EInDialogFragment(int workoutId) {
        this.workoutId = workoutId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_add_exercise_in_workout, container, false);

        initializeVariables(view);

        if (onInputListenerEInW != null){
            allExercises = onInputListenerEInW.exerciseList();
        }else {
            Log.d(TAG, "onCreateView: onInputListener is null");
        }



        final List<KeyPairBoolData> allExercisesList = new ArrayList<>();
        for (int i = 0; i < allExercises.size();
             i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(allExercises.get(i).getName());
            h.setSelected(false);
            allExercisesList.add(h);
        }

        multiSpinnerSearch.setSearchEnabled(true);
        multiSpinnerSearch.setEmptyTitle("No Exercises");

        multiSpinnerSearch.setShowSelectAllButton(true);

        multiSpinnerSearch.setClearText("Clear All");

        multiSpinnerSearch.setItems(allExercisesList, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for (int i = 0; i < selectedItems.size(); i++) {
                    if (selectedItems.get(i).isSelected()) {
                        Exercise exercise = (Exercise) selectedItems.get(i).getObject();
                        int exerciseId = exercise.getId();
                        Mapping mapping = new Mapping(exerciseId,workoutId);
                        mappings.add(mapping);
                        // TODO: 11/3/21 add exercise to workout.
                    }
                }
                onInputListenerEInW.mapExerciseToWorkout(mappings);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 10/3/21 Get all the selected exercises from recycler view and add them into the workout
                // TODO: 11/3/21 Add multiple mappings from the mapping arraylist.
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });


        return view;
    }

    void initializeVariables(View view) {

        multiSpinnerSearch = view.findViewById(R.id.multiSpinner);
        btnAdd = view.findViewById(R.id.btnAddDialogExercise);
        btnCancel = view.findViewById(R.id.btnCancelDialogExercise);

        context = view.getContext();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onInputListenerEInW = (OnInputListenerEInW) context;
            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }
}
