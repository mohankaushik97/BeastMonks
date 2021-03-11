package xyz.projectaloha.zerodegreebeasts.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import xyz.projectaloha.zerodegreebeasts.R;

public class WorkoutDialogFragment extends DialogFragment {

    //widgets
    private EditText edtWorkoutName;
    private Button btnAdd, btnCancel;
    private static final String TAG = "WorkoutDialogFragment";

    public interface OnInputListener {
        void InsertWorkout(String input);
    }

    public OnInputListener onInputListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_workout, container, false);

        btnAdd = view.findViewById(R.id.btnAdd);
        btnCancel = view.findViewById(R.id.btnCancel);

        edtWorkoutName = view.findViewById(R.id.edtWorkout);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String workoutInput = edtWorkoutName.getText().toString();
                Log.d(TAG, "onClick: workoutInput: " + workoutInput);
                if (!workoutInput.equals("")) {
                    onInputListener.InsertWorkout(workoutInput);
                }


                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onInputListener = (OnInputListener) getTargetFragment();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }
}