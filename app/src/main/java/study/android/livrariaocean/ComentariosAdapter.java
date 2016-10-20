package study.android.livrariaocean;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.glide.GlideRequest;

import java.util.ArrayList;

/**
 * Created by aluno on 19/10/2016.
 */

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ViewHolder> {
    private ComentariosAdapter.OnItemClick callback;
    private ArrayList<Comentario> comentarios;
    private final Context context;

    public ComentariosAdapter(Context context, ArrayList<Comentario> comentarios) {


        this.context = context;
        this.comentarios = comentarios;
    }

    public void setCallback(ComentariosAdapter.OnItemClick callback) {
        this.callback = callback;
    }

    @Override
    public ComentariosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comentarios, null);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComentariosAdapter.ViewHolder holder, int position) {
        Comentario comentario = comentarios.get(position);
        holder.setTxtComentario(comentario.getComentario());

        holder.setImgComentario(comentario.getFoto());

    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgComentario;
        private TextView txtComentario;

        public ViewHolder(View itemView) {
            super(itemView);
            imgComentario = (ImageView) itemView.findViewById(R.id.imgComentario);
            txtComentario = (TextView) itemView.findViewById(R.id.txtComentario);

            itemView.setOnClickListener(this);
        }


        public void setTxtComentario(String txtComentario) {
            if (txtComentario != null) {
                this.txtComentario.setText(txtComentario);
            }
        }


        public void setImgComentario(String imgComentario) {

            if (imgComentario == null) return;


            Ocean.glide(context)
                    .load(imgComentario)
                    .build(GlideRequest.BITMAP)
                    .circle()
                    .into(this.imgComentario);
        }


        @Override
        public void onClick(View view) {


            if (callback != null) {
                callback.onItemClick(view, getPosition());

            }
        }


    }


    public interface OnItemClick {
        void onItemClick(View view, int position);

    }
}
