package xyz.projectaloha.zerodegreebeasts.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = {@ForeignKey(entity = Exercise.class, parentColumns = "id", childColumns = "exerciseId"),
        @ForeignKey(entity = Workout.class, parentColumns = "id", childColumns = "workoutId")})
public class Mapping {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final int exerciseId;
    private final int workoutId;
    private String setsCount;
    private String repsCount;
    private String maxWeight;


    public Mapping(int exerciseId, int workoutId) {
        this.exerciseId = exerciseId;
        this.workoutId = workoutId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public String getSetsCount() {
        return setsCount;
    }

    public void setSetsCount(String setsCount) {
        this.setsCount = setsCount;
    }

    public String getRepsCount() {
        return repsCount;
    }

    public void setRepsCount(String repsCount) {
        this.repsCount = repsCount;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }
}
