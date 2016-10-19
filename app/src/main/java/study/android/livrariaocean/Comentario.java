package study.android.livrariaocean;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

/**
 * Created by pc on 19/10/2016.
 */

public class Comentario implements Serializable {
    private String foto;
    private String comentario;

    public Comentario(String foto, String comentario) {
        this.foto = foto;
        this.comentario = comentario;
    }



    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
