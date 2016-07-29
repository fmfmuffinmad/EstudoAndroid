package com.muffinalunos.muffinmad.muffinalunos.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.muffinalunos.muffinmad.muffinalunos.MainActivity;
import com.muffinalunos.muffinmad.muffinalunos.modelo.Aluno;

import java.util.List;

/**
 * Created by muffinmad on 29/07/2016.
 */
public class AlunosAdapter extends BaseAdapter{
    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context, List<Aluno> aluno) {
        this.alunos = aluno;
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(context);
        textView.setText("Pos: "+ i);
        return null;
    }
}
