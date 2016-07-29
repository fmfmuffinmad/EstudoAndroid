package com.muffinalunos.muffinmad.muffinalunos.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.muffinalunos.muffinmad.muffinalunos.MainActivity;
import com.muffinalunos.muffinmad.muffinalunos.R;
import com.muffinalunos.muffinmad.muffinalunos.helpers.ImageHelper;
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
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).get_ID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Aluno aluno = alunos.get(i);
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewInflada = view;

        if (viewInflada == null){
            viewInflada = inflater.inflate(R.layout.list_item, viewGroup, false);
        }


        TextView campoNome = (TextView) viewInflada.findViewById(R.id.item_nome);
        campoNome.setText(aluno.get_nome());
        TextView campoTelefone = (TextView) viewInflada.findViewById(R.id.item_telefone);
        campoTelefone.setText(aluno.get_telefone());
        ImageView campoImagem = (ImageView) viewInflada.findViewById(R.id.item_foto);
        ImageHelper imageHelper = new ImageHelper();
        Bitmap bitmap = imageHelper.lessResolution(aluno.get_caminhoFoto(), 64, 64);
        campoImagem.setImageBitmap(bitmap);


        return viewInflada;
    }
}
