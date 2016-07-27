package com.muffinalunos.muffinmad.muffinalunos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.muffinalunos.muffinmad.muffinalunos.dao.AlunoDAO;
import com.muffinalunos.muffinmad.muffinalunos.modelo.Aluno;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Define qual o layout usado na activity

        //Cria a barrinha no topo da activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Encontrando um objeto no XML pelo ID
        listStudents = (ListView) findViewById(R.id.MainActivity_listaDeAlunos);

        //Associar View ao Menu de contexto
        registerForContextMenu(listStudents);

        //Click em um item da lista
        listStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int i, long l) {
                Aluno aluno = (Aluno) listStudents.getItemAtPosition(i);
                //Toast.makeText(MainActivity.this, aluno.get_nome(), Toast.LENGTH_SHORT).show();
                Intent vaiProFormulario = new Intent(MainActivity.this, FormularioActivity.class);
                vaiProFormulario.putExtra("aluno", aluno);
                startActivity(vaiProFormulario);
            }
        });

        // Click longo na lista. Pode substituir a ação do context menu
//        listStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                return false;
//            }
//        });

        //Botão flutuante com o listener pra click
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaFormulario = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(vaiParaFormulario);
            }
        });


    }

    // Carrega a lista de alunos para ser mostrado na ListView
    private void carregaAlunos() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> aluno = dao.buscaAlunos();// Essa função na DAO é um select
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, aluno);
        listStudents.setAdapter(adapter);
    }


    // On Resume é chamado sempre que trocarmos de contexto ou de programa
    @Override
    protected void onResume() {
        super.onResume();
        carregaAlunos();
    }

    // Menu de contexto. É aquele menu no meio da tela
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) listStudents.getItemAtPosition(info.position);

        // Ação de ligar par ao numero cadastrado
        MenuItem itemLigar = menu.add("Ligar");
        itemLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if( ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 123);

                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:" + aluno.get_telefone()));
                    startActivity(intentLigar);

                }else{
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:" + aluno.get_telefone()));
                    startActivity(intentLigar);
                }

                return false;
            }
        });



        // Item para visualizar endereco
        MenuItem itemEndereco = menu.add("Ver Endereço");
        Intent intentEndereco = new Intent(Intent.ACTION_VIEW);
        intentEndereco.setData(Uri.parse("geo:0,0?q="+aluno.get_endereco()));
        itemEndereco.setIntent(intentEndereco);

        // Item para mandar SMS
        MenuItem itemSMS = menu.add("Enviar SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:"+aluno.get_telefone()));
        itemSMS.setIntent(intentSMS);


        // Item para visitar o site cadastrado no aluno
        MenuItem itemVisitar = menu.add("Visitar Site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        String site = aluno.get_site();
        if(!site.startsWith("http://")){
            site = "http://" + site;
        }
        intentSite.setData(Uri.parse(site));
        itemVisitar.setIntent(intentSite);


        //item para deletar
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Toast.makeText(MainActivity.this, "Aluno "+aluno.get_nome()+" deletado.", Toast.LENGTH_LONG).show();

                AlunoDAO dao = new AlunoDAO(MainActivity.this);
                dao.deletar(aluno);
                dao.close();

                carregaAlunos();

                return false;
            }
        });
    }

    // Cria o menu na barrinha no topo da activity.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Para colocar um item no menu, é necessário dar um inflate
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //essa função é executada sempre depois de uma requisição de permissão
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
}
