package xyz.projectaloha.zerodegreebeasts.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.io.File;

import xyz.projectaloha.zerodegreebeasts.Entities.Exercise;
import xyz.projectaloha.zerodegreebeasts.Entities.Mapping;
import xyz.projectaloha.zerodegreebeasts.Entities.Workout;
import xyz.projectaloha.zerodegreebeasts.Repository.ExerciseDao;
import xyz.projectaloha.zerodegreebeasts.Repository.MappingDao;
import xyz.projectaloha.zerodegreebeasts.Repository.WorkoutDao;

@Database(entities = {Exercise.class, Workout.class, Mapping.class}, version = 1)
public abstract class BeastDatabase extends RoomDatabase {

    public abstract ExerciseDao exerciseDao();

    public abstract WorkoutDao workoutDao();

    public abstract MappingDao mappingDao();


    private static BeastDatabase instance;

    public static synchronized BeastDatabase getInstance(Context context) {
        if (null == instance) {
            instance = Room.databaseBuilder(context, BeastDatabase.class, "BeastMonks.db")
//                    .createFromAsset("databases/BeastMonks.db")
//                    .createFromFile(new File("/Users/mohankaushik/Desktop/BeastMonks/BeastMonks.db"))
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
