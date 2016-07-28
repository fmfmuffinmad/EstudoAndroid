package com.muffinalunos.muffinmad.muffinalunos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.muffinalunos.muffinmad.muffinalunos.dao.AlunoDAO;
import com.muffinalunos.muffinmad.muffinalunos.modelo.Aluno;

import java.io.File;

public class FormularioActivity extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 567;
    private formularioHelper helper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helper = new formularioHelper(this);

        // Recebendo parametros do Activity principal atraves do intent
        final Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null){
            helper.preencheFormulario(aluno);
        }

        // botao da foto
        FloatingActionButton btnFoto = (FloatingActionButton) findViewById(R.id.formulario_btnFoto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTiraFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() +".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentTiraFoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentTiraFoto, CODIGO_CAMERA);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == CODIGO_CAMERA){
                helper.carregaImagem(caminhoFoto);

//                Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
//                imgFoto.setImageBitmap(bitmapReduzido);
//                imgFoto.setScaleType(ImageView.ScaleType.MATRIX);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Criar um inflater adiciona um item na action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.fomulario_action_save) {

            Aluno aluno = helper.pegaAluno();
            AlunoDAO dao = new AlunoDAO(this);
            if (aluno.get_ID() != 0){
                dao.altera(aluno);
            }else{
                dao.insert(aluno);
            }

            dao.close();
            Toast.makeText(FormularioActivity.this, "Aluno "+aluno.get_nome()+" Salvo", Toast.LENGTH_LONG).show();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
