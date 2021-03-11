package xyz.projectaloha.zerodegreebeasts.Repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import xyz.projectaloha.zerodegreebeasts.Entities.Mapping;

@Dao
public interface MappingDao {

    @Insert
    void insertSingleMapping(Mapping mapping);

    @Insert
    void insertMultipleMappings(ArrayList<Mapping> mappings);

    @Delete
    void deleteSingleMapping(Mapping mapping);

    @Update
    void updateSingleMapping(Mapping mapping);

    @Query("SELECT * FROM mapping")
    List<Mapping> getAllMappings();

    @Query("SELECT * FROM Mapping WHERE id LIKE :id")
    Mapping getMappingById(int id);

    @Query("SELECT * FROM mapping WHERE exerciseId LIKE :exerciseId")
    List<Mapping> getMappingByExercise(int exerciseId);

    @Query("SELECT * FROM mapping WHERE workoutId LIKE :workoutId")
    List<Mapping> getMappingByWorkout(int workoutId);

//    @Query("SELECT repsCount,setsCount,maxWeight FROM mapping WHERE exerciseId = :exerciseId")
//    AbstractList<Mapping> getPrevDataByExercise(int exerciseId);
}
