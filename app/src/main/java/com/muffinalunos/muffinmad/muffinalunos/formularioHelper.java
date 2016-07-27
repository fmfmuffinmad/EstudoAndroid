package com.muffinalunos.muffinmad.muffinalunos;

import android.widget.EditText;
import android.widget.RatingBar;

import com.muffinalunos.muffinmad.muffinalunos.modelo.Aluno;

/**
 * Created by muffinmad on 20/07/2016.
 */
public class formularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoRating;

    private Aluno aluno;

    public formularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_txtNome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_txtEndereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_txtTelefone);
        campoSite = (EditText) activity.findViewById(R.id.formulario_txtSite);
        campoRating = (RatingBar) activity.findViewById(R.id.formulario_nota);
        aluno = new Aluno();
    }
    public boolean saveAluno(){


        return true;
    }

    public Aluno pegaAluno() {
        aluno.set_nome(campoNome.getText().toString());
        aluno.set_endereco(campoEndereco.getText().toString());
        aluno.set_telefone(campoTelefone.getText().toString());
        aluno.set_site(campoSite.getText().toString());
        aluno.set_nota(campoRating.getRating());

        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.get_nome());
        campoEndereco.setText(aluno.get_endereco());
        campoTelefone.setText(aluno.get_telefone());
        campoSite.setText(aluno.get_site());
        campoRating.setRating(aluno.get_nota());
        this.aluno = aluno;
    }
}
