package com.example.myquizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.myquizapp.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE  " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + "TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + "TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + "TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + "INTEGER" + ")";

        db.execSQL(SQL_CREATE_QUESTION_TABLE);
        fillQuestionTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
            onCreate(db);
    }
    private void fillQuestionTable() {
        Question q1 = new Question("A is correct", "A", "B", "C", 1);
        addQuestion(q1);
        Question q2 = new Question("A is correct", "A", "B", "C", 1);
        addQuestion(q2);
        Question q3 = new Question("A is correct", "A", "B", "C", 1);
        addQuestion(q3);
        Question q4 = new Question("A is correct", "A", "B", "C", 1);
        addQuestion(q4);
        Question q5 = new Question("A is correct", "A", "B", "C", 1);
        addQuestion(q5);
        Question q6 = new Question("A is correct", "A", "B", "C", 1);
        addQuestion(q6);
    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<>();
        db =  getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if(c.moveToFirst()) {
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);

            }while (c.moveToFirst());
        }

        c.close();
        return questionList;
    }
}
