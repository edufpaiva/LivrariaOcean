package study.android.livrariaocean;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.http.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Request.RequestListener, MyAdapter.OnItemClick {

    ArrayList<Livro> livros = new ArrayList<>();

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

    void hideLoad(ArrayList<Livro> livros) {
        if (livros.size() > 0) {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onRequestOk(String resposta, JSONObject jsonObject, int code) {
        if (code == Request.NENHUM_ERROR) {

            stringToGson(resposta);

            criarAdapter(livros);

        }
    }

    public void criarAdapter(ArrayList<Livro> livros) {
        MyAdapter adapter = new MyAdapter(this, livros);
        RecyclerView recycler = (RecyclerView) findViewById(R.id.lista);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCallback(this);

        hideLoad(livros);
    }

    @Override
    public void onItemClick(View view, int position) {

        Livro livro = livros.get(position);
        Intent intent = new Intent(MainActivity.this, Detalhes.class);

        intent.putExtra("position", position);
        intent.putExtra("books", livros);

        /*
        intent.putExtra("titulo", livro.getTitulo());
        intent.putExtra("capa", livro.getCapa());
        intent.putExtra("ano", livro.getAno());
        intent.putExtra("npaginas", livro.getPaginas());
        intent.putExtra("autor", livro.getAutor());
*/

        startActivity(intent);

    }

    private void stringToGson(String resposta) {
        Gson gson = new Gson();

        BookStore bookStore = gson.fromJson(resposta, BookStore.class);

        ArrayList<Item> itens = bookStore.getOcean();

        for (Item iten : itens) {
            Log.d("Categoria", iten.getCategoria());
            livros.addAll(iten.getLivros());

        }


    }

    public void stringToJson(String resposta) {

        if (resposta != null) {
            try {
                JSONObject object = new JSONObject(resposta);
                JSONArray ocean = object.getJSONArray("ocean");

                for (int i = 0; i < ocean.length(); i++) {
                    JSONObject item = ocean.getJSONObject(i);

                    JSONArray books = item.getJSONArray("livros");


                    for (int j = 0; j < books.length(); j++) {

                        JSONObject livro = books.getJSONObject(j);
                        String titulo = livro.getString("titulo");
                        String autor = livro.getString("autor");
                        int ano = livro.getInt("ano");
                        int paginas = livro.getInt("paginas");
                        String capa = livro.getString("capa");


                        Log.d("titulo", titulo);
                        Log.d("autor", autor);
                        Log.d("ano", String.valueOf(ano));
                        Log.d("paginas", String.valueOf(paginas));
                        Log.d("capa", capa);


                        livros.add(new Livro(capa, titulo, autor, paginas, ano));

                    }
                }


                criarAdapter(livros);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}
