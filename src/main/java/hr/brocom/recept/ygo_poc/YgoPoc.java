package hr.brocom.recept.ygo_poc;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class YgoPoc {
    public static void print() {

        String json = jsonGetRequest("https://db.ygoprodeck.com/api/v5/cardinfo.php");
        Gson gson = new Gson();
        CardInfo set[] = gson.fromJson(json, CardInfo[].class);

        CardInfo darkHole = set[2014];

        List<CardEntity> cardEntityList = convertToCardEntity(darkHole);

        System.out.println("done");


    }


    public static String jsonGetRequest(String urlQueryString) {
        String json = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    private static List<CardEntity> convertToCardEntity(CardInfo cardInfo){
       List<CardEntity> cardEntityList = new ArrayList<>();

      for (CardSet cardSet : cardInfo.getCardSets()) {
          CardEntity cardEntity = new CardEntity();
          cardEntity.setId(null);
          cardEntity.setCode(cardSet.getSetCode());
          cardEntity.setName(cardInfo.getName());
          cardEntity.setType(cardInfo.getType());
          cardEntity.setDesc(cardInfo.getDesc());
          cardEntity.setRace(cardInfo.getRace());
          cardEntity.setArchetype(cardInfo.getArchetype());
          cardEntity.setSetName(cardSet.getSetName());
          cardEntity.setSetRarity(cardSet.getSetRarity());
          cardEntity.setPrice(cardSet.getSetPrice());
          cardEntity.setBanlist(cardInfo.getBanlistInfo().getBanGoat());
          cardEntity.setCardImage("za sad jos nista");
          cardEntity.setCardImageSmall("za sad jos nista");
          cardEntityList.add(cardEntity);
      }
        return cardEntityList;

    }
}
