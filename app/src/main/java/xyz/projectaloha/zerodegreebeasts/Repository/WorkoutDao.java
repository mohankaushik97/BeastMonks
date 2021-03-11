package xyz.projectaloha.zerodegreebeasts.Repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import xyz.projectaloha.zerodegreebeasts.Entities.Workout;

@Dao
public interface WorkoutDao {

    @Insert
    void insertSingleWorkout(Workout workout);

    @Update
    void updateSingleWorkout(Workout workout);

    @Delete
    void deleteSingleWorkout(Workout workout);

    @Query("DELETE FROM workout")
    void deleteAllWorkouts();

    @Query("SELECT * FROM workout")
    List<Workout> getAllWorkouts();

    @Query("SELECT * FROM workout")
    LiveData<List<Workout>> getAllWorkoutsLive();

    @Query("SELECT * FROM workout WHERE id LIKE :id")
    Workout getWorkoutById(int id);

}
