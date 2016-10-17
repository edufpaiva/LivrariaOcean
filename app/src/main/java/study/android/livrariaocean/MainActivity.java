package study.android.livrariaocean;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.http.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Request.RequestListener {

    ArrayList<Livro> livros ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //kevin.takano@gmail.com


        //criar adapter



        //progress bar



//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    HttpRequest.GET("http://gitlab.oceanmanaus.com/snippets/1/raw");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        Ocean.newRequest("http://gitlab.oceanmanaus.com/snippets/1/raw", this).get().send();

    }

    void hideLoad(ArrayList<Livro> livros){
        if(livros.size() >0){
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
        }

    }


    public ArrayList<Livro> iniciarLista() {
        livros = new ArrayList();

        Livro livro = new Livro("http://172.25.1.17/oceanbook/NoSQLEssencial.png", "NoSQL Essencial", "Pramod J. Sadalage / Martin Fowler", 216, 2013);
        Livro livro1 = new Livro("http://172.25.1.17/oceanbook/BancoDeDadosComC.jpg", "Fundamentos de Bancos de Dados com C#", "Michael Schmalz", 120, 2012);
        Livro livro2 = new Livro("http://172.25.1.17/oceanbook/JovemeBem-Sucedido.jpg", "Jovem e Bem-Sucedido", "Juliano Niederauer", 192, 2013);
        Livro livro3 = new Livro("http://172.25.1.17/oceanbook/LidandocomaIncerteza.png", "Lidando com a Incerteza", "Jonathan Fields", 208, 2013);
        Livro livro4 = new Livro("http://172.25.1.17/oceanbook/EquipesdeSoftware.jpg", "Equipes de Software", "Brian W. Fitzpatrick / Ben Collins-Sussman", 208, 2012);

        livros.add(livro);
        livros.add(livro1);
        livros.add(livro2);
        livros.add(livro3);
        livros.add(livro4);


        return livros;
    }


    @Override
    public void onRequestOk(String resposta, JSONObject jsonObject, int code) {
        if(code == Request.NENHUM_ERROR){

            livros = new ArrayList<>();

            if(resposta != null){
                try {
                    JSONObject object = new JSONObject(resposta);
                    JSONArray ocean = object.getJSONArray("ocean");

                    for (int i = 0 ; i < ocean.length(); i++){
                        JSONObject item = ocean.getJSONObject(i);

                        JSONArray books = item.getJSONArray("livros");


                        for (int j = 0; j < books.length(); j++){

                            JSONObject livro = books.getJSONObject(j);
                            String titulo =livro.getString("titulo");
                            String autor =livro.getString("autor");
                            int ano = livro.getInt("ano");
                            int paginas = livro.getInt("paginas");
                            String capa = livro.getString("capa");


                            Log.d("titulo", titulo);
                            Log.d("autor", autor);
                            Log.d("ano", String.valueOf(ano));
                            Log.d("paginas", String.valueOf(paginas));
                            Log.d("capa", capa);



                            livros.add(new Livro(capa, titulo, autor, paginas, ano ));

                        }
                    }




                    criarAdapter(livros);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public void criarAdapter(ArrayList<Livro> livros){
        MyAdapter adapter = new MyAdapter(this, livros);
        RecyclerView recycler = (RecyclerView) findViewById(R.id.lista);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));



        hideLoad(livros);
    }

}
