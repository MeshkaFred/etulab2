import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) throws Exception {
        String tmp = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=";
        Gson gson = new Gson(); // Primitives Exxamples https://github.com/google/gson/blob/main/UserGuide.md#compact-vs-pretty-printing-for-json-output-format
       // String str = new String("https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=\"ЛЭТИ\"");
      //  String json = gson.toJson(tmp);
        //System.out.println("tmp           " + tmp);
        String str = "Бетмен";
        str = tmp +  URLEncoder.encode(str, "UTF-8");
        System.out.println(str);
       // String[] strjs = gson.fromJson(json, String[].class);
        //System.out.println("strjs         " + strjs);
//
      //  Gson gson = new Gson();
     //   String str = new String("https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=%22%D0%9B%D0%AD%D0%A2%D0%98%22");


        System.out.println(str);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(str)).header("User-Agent",
                        "GenaNa/1.0 (your-email@example.com)")
                .GET()
                .build();
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString(
                        StandardCharsets.UTF_8
                )
        );
        String json = response.body();

        System.out.println("JSON: " + json);


        JsonObject root = gson.fromJson(
                json,
                JsonObject.class
        );


    }
//        if (!Desktop.isDesktopSupported()) {
//            System.out.println("Desktop не поддерживается");
//            return;
//        }
//
//        Desktop desktop = Desktop.getDesktop();
//
//        // Открыть сайт в браузере
//        desktop.browse(new URI("https://google.com"));
//
//        // Открыть файл
//        desktop.open(new File("test.txt"));
//    }
}