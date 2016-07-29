package com.muffinalunos.muffinmad.muffinalunos.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.muffinalunos.muffinmad.muffinalunos.FormularioActivity;
import com.muffinalunos.muffinmad.muffinalunos.R;
import com.muffinalunos.muffinmad.muffinalunos.modelo.Aluno;

/**
 * Created by muffinmad on 20/07/2016.
 */
public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoRating;
    private final ImageView campoFoto;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_txtNome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_txtEndereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_txtTelefone);
        campoSite = (EditText) activity.findViewById(R.id.formulario_txtSite);
        campoRating = (RatingBar) activity.findViewById(R.id.formulario_nota);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
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
        aluno.set_caminhoFoto((String) campoFoto.getTag());
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.get_nome());
        campoEndereco.setText(aluno.get_endereco());
        campoTelefone.setText(aluno.get_telefone());
        campoSite.setText(aluno.get_site());
        campoRating.setRating(aluno.get_nota());
        carregaImagem(aluno.get_caminhoFoto());
        this.aluno = aluno;
    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto != null) {
            ImageHelper imageHelper = new ImageHelper();
            Bitmap bitmap = imageHelper.lessResolution(caminhoFoto, 300, 300);
            campoFoto.setImageBitmap(bitmap);
            campoFoto.setTag(caminhoFoto);
        }
    }

}
