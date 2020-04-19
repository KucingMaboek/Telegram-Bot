package telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getFrom().getFirstName() + ": " + update.getMessage().getText());
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        sendMessage.setText("Hello " + update.getMessage().getFrom().getFirstName() + "\n" + update.getMessage().getText());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return "1215264476:AAHsl7wn5-1YGP-vk8VkYjQdNP0BH7dsQME";
    }

    public void sendMessageToChannel() {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String apiToken = "1215264476:AAHsl7wn5-1YGP-vk8VkYjQdNP0BH7dsQME";
        String chatId = "@PLN_NewsFeed";
//        String text = "Hello world!";
        String text = "PLN BERTINDAK LAGI, BERIKUT BERITANYA  https://medium.com/@xabaras/sending-a-message-to-a-telegram-channel-the-easy-way-eb0a0b32968";
        urlString = String.format(urlString, apiToken, chatId, text);

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection conn = null;
        try {
            conn = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        try {
            is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String inputLine = "";
        while (true) {
            try {
                if (!((inputLine = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(inputLine);
        }
        String response = sb.toString();
        // Do what you want with response
    }

    public void sendPhotoToChannel(File file, String messages) throws TelegramApiException {

        if (file != null) {
//            SendPhoto msg = new SendPhoto().setChatId("@PLN_NewsFeed")
//                    .setPhoto(file)
//                    .setCaption("PLN BERTINDAK LAGI, BERIKUT BERITANYA  https://medium.com/@xabaras/sending-a-message-to-a-telegram-channel-the-easy-way-eb0a0b32968");
//            execute(msg);

            SendDocument document = new SendDocument().setChatId("@PLN_NewsFeed")
                    .setDocument(file)
                    .setCaption(messages);
//            sendDocument(document);
            execute(document);


        } else {
            SendMessage msg = new SendMessage().setChatId("@PLN_NewsFeed")
                    .setText(messages);
            execute(msg);
//            sendMessage(msg);
        }
    }
}
