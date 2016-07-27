package com.muffinalunos.muffinmad.muffinalunos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.muffinalunos.muffinmad.muffinalunos.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muffinmad on 20/07/2016.
 */
public class AlunoDAO extends SQLiteOpenHelper{
    public AlunoDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE ALUNOS (" +
                "ID INTEGER PRIMARY KEY," +
                "NOME TEXT NOT NULL," +
                "ENDERECO TEXT," +
                "TELEFONE TEXT," +
                "SITE TEXT," +
                "NOTA REAL); ";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS ALUNOS";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }


    public void insert(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(aluno);
        db.insert("ALUNOS", null, dados);
    }

    @NonNull
    private ContentValues getContentValues(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("NOME", aluno.get_nome());
        dados.put("ENDERECO", aluno.get_endereco());
        dados.put("TELEFONE", aluno.get_telefone());
        dados.put("SITE", aluno.get_site());
        dados.put("NOTA", aluno.get_nota());
        return dados;
    }

    public List<Aluno> buscaAlunos() {
        String sql = "SELECT * FROM ALUNOS";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();
        Aluno aluno = null;
        while (c.moveToNext()) {
            aluno = new Aluno();
            aluno.set_ID(c.getLong(c.getColumnIndex("ID")));
            aluno.set_nome(c.getString(c.getColumnIndex("NOME")));
            aluno.set_endereco(c.getString(c.getColumnIndex("ENDERECO")));
            aluno.set_telefone(c.getString(c.getColumnIndex("TELEFONE")));
            aluno.set_site(c.getString(c.getColumnIndex("SITE")));
            aluno.set_nota(c.getFloat(c.getColumnIndex("NOTA")));

            alunos.add(aluno);
        }

        c.close();
        return alunos;
    }

    public void deletar(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {Long.toString(aluno.get_ID())};
        db.delete("Alunos", "id = ?", params);

    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(aluno);
        String[] param = {Long.toString(aluno.get_ID())};
        db.update("ALUNOS", dados, "ID = ?", param);
    }
}
