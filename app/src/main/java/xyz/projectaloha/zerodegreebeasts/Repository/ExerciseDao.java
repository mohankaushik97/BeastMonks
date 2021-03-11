package xyz.projectaloha.zerodegreebeasts.Repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import xyz.projectaloha.zerodegreebeasts.Entities.Exercise;

@Dao
public interface ExerciseDao {

    @Insert
    void insertSingleExercise(Exercise exercise);

    @Insert
    void insertMultipleExercises(ArrayList<Exercise> exercises);

    @Delete
    void deleteSingleExercise(Exercise exercise);

    @Update
    void updateSingleStudent(Exercise exercise);

    @Query("SELECT * FROM exercise")
    List<Exercise> getAllExercises();

    @Query("SELECT * FROM exercise WHERE id LIKE :id")
    Exercise getExerciseById(int id);

    @Query("SELECT * FROM exercise ORDER BY name")
    List<Exercise> getAllExercisesinOrder();

    @Query("SELECT * FROM exercise WHERE category=:category ORDER BY name")
    List<Exercise> getExercisesByCategory(String category);

}
