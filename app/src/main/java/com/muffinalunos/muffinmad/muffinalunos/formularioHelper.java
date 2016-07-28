package com.muffinalunos.muffinmad.muffinalunos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
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
    private final ImageView campoFoto;

    private Aluno aluno;

    public formularioHelper(FormularioActivity activity){
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
            Bitmap bitmap = lessResolution(caminhoFoto, 300, 300);
            campoFoto.setImageBitmap(bitmap);
            campoFoto.setTag(caminhoFoto);
        }
    }

    public static Bitmap lessResolution (String filePath, int width, int height) {
        int reqHeight = height;
        int reqWidth = width;
        BitmapFactory.Options options = new BitmapFactory.Options();

        // First decode with inJustDecodeBounds=true to check dimensions
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
