package study.android.livrariaocean;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.glide.GlideRequest;

import java.util.ArrayList;
import java.util.Random;

public class Detalhes extends AppCompatActivity {
    int posicao;
    private ArrayList<Livro> livros;
    private ArrayList<Comentario> comentarios;
    Random random = new Random();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        posicao = getIntent().getIntExtra("position", 0);
        livros = (ArrayList<Livro>) getIntent().getSerializableExtra("books");
        Livro livro = livros.get(posicao);
        setView(livro);
        setRecomendacao(posicao, livros);
        iniciaComentarios();
        criarAdapter(comentarios);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_detalhes);

        scrollView.scrollTo(0, 0);




    }

    /*private Livro recuperaLivro(){
        Livro livro = new Livro();
        livro.setTitulo(getIntent().getStringExtra("titulo"));
        livro.setCapa(getIntent().getStringExtra("capa"));
        livro.setAutor(getIntent().getStringExtra("autor"));
        livro.setPaginas(Integer.parseInt(getIntent().getStringExtra("npaginas")));
        livro.setAno(Integer.parseInt(getIntent().getStringExtra("ano")));

        return livro;
    }*/

    private void setView(Livro livro) {

        ImageView capa = (ImageView) findViewById(R.id.imgCapaDescription);
        TextView titulo = (TextView) findViewById(R.id.nomeLivroDescription);
        TextView autor = (TextView) findViewById(R.id.nomeAutorDescription);
        TextView ano = (TextView) findViewById(R.id.anoDescription);
        TextView paginas = (TextView) findViewById(R.id.nPaginasDescription);

        Ocean.glide(this)
                .load(livro.getCapa())
                .build(GlideRequest.BITMAP)
                .into(capa);

        titulo.setText(livro.getTitulo());
        autor.setText(livro.getAutor());
        ano.setText(livro.getAno() + "");
        paginas.setText(livro.getPaginas() + "");
    }

    public void setRecomendacao(int posicao, ArrayList<Livro> livros) {

        int a = 0, b = 0;

        do {
            a = random.nextInt(livros.size());
            b = random.nextInt(livros.size());


        } while (a == b || a == posicao);

        ImageView capaRec1 = (ImageView) findViewById(R.id.rec1);
        ImageView capaRec2 = (ImageView) findViewById(R.id.rec2);

        Ocean.glide(this)
                .load(livros.get(a).getCapa())
                .build(GlideRequest.BITMAP)
                .into(capaRec1);

        Ocean.glide(this)
                .load(livros.get(b).getCapa())
                .build(GlideRequest.BITMAP)
                .into(capaRec2);


    }

    public void mudaRecomendacao(View view){
        setRecomendacao(posicao, livros);

    }

    void iniciaComentarios(){
        ArrayList<String> txtComments = new ArrayList<>();
        txtComments.add("Fantastico e lindo");
        txtComments.add("Puro luxo e sedução");
        txtComments.add("Lixo");
        txtComments.add("Demais");
        txtComments.add("5 Estrelas");
        txtComments.add("Não recomendo");
        txtComments.add("recomendo a todos");
        txtComments.add("o ó do borogodó");
        txtComments.add("to com fome");


        comentarios = new ArrayList<>();



        Comentario a = new Comentario(getString(R.string.img_comentario_default), txtComments.get(random.nextInt(txtComments.size())));
        Comentario b = new Comentario(getString(R.string.img_comentario_default), txtComments.get(random.nextInt(txtComments.size())));
        Comentario c = new Comentario(getString(R.string.img_comentario_default), txtComments.get(random.nextInt(txtComments.size())));

        comentarios.add(a);
        comentarios.add(b);
        comentarios.add(c);

    }

    public void criarAdapter(ArrayList<Comentario> comentarios) {
        ComentariosAdapter adapter = new ComentariosAdapter(this, comentarios);
        RecyclerView recycler = (RecyclerView) findViewById(R.id.comentarios);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //adapter.setCallback(this);


    }

}
