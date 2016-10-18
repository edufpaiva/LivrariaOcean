package study.android.livrariaocean;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.glide.GlideRequest;

public class Detalhes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Livro livro = (Livro) getIntent().getSerializableExtra("book");
        setView(livro);



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

    private void setView(Livro livro){

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
        ano.setText(livro.getAno()+"");
        paginas.setText(livro.getPaginas()+"");
    }
}
