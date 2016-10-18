package study.android.livrariaocean;

import java.util.ArrayList;

/**
 * Created by aluno on 17/10/2016.
 */

public class Item {
    public String categoria;
    public ArrayList<Livro> livros;


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }
}
