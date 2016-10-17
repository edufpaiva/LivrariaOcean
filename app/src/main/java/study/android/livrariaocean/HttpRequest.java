package study.android.livrariaocean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by aluno on 11/10/2016.
 */

public class HttpRequest {


    public static String GET(String path) throws IOException {
        String result = "";


        try {
            URL url = new URL(path);
            HttpURLConnection conexao =(HttpURLConnection) url.openConnection();
            conexao.connect();

            int resposta = conexao.getResponseCode();

            if(resposta == HttpURLConnection.HTTP_OK){

                InputStream is = conexao.getInputStream();
                result = bytesParaString(is);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String bytesParaString(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();
        int bytesLido = 0;
        while(is.read(buffer) != -1){
            bufferzao.write(buffer, 0, bytesLido);

        }



        return new String(bufferzao.toByteArray(), "UTF-8");
    }

}
