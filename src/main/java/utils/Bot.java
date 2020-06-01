package utils;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import twitter4j.JSONObject;

import java.io.File;
import java.io.IOException;

public class Bot extends TelegramLongPollingBot {
    private String channelChatId = "@PLN_NewsFeed";

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        MessageHandler message = new MessageHandler();
        update.getMessage().getFrom().getUserName();

        String text = null;
        String chatId = String.valueOf(update.getMessage().getChatId());
        String username = String.valueOf(update.getMessage().getFrom().getUserName());
        String first_name = String.valueOf(update.getMessage().getFrom().getFirstName());
        String last_name = String.valueOf(update.getMessage().getFrom().getLastName());

        String query = String.format("INSERT INTO tg_account_data (chatId, first_name)\n" +
                "SELECT * FROM (SELECT \'%s\', \'%s\') AS tmp\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT chatId FROM tg_account_data WHERE chatId = \'%s\'\n" +
                ") LIMIT 1;", chatId, first_name, chatId);
        String queryUpdate = String.format("update tg_account_data set username = \'%s\'," +
                " first_name = \'%s\'," +
                " last_name = \'%s\' " +
                "where chatId = \'%s\'",
                username, first_name, last_name, chatId);
        Helper.query(query);
        Helper.query(queryUpdate);

        String url;
        if (update.getMessage().hasPhoto()) {
            String fileId = update.getMessage().getPhoto().get(0).getFileId();
            url = String.format("https://api.telegram.org/bot%s/getFile?file_id=%s", getBotToken(), fileId);
            System.out.println(url);
            try {
                JSONObject json = Helper.readJsonFromUrl(url);
                json = (JSONObject) json.get("result");
                String filePath = json.getString("file_path");
                text = String.format("https://api.telegram.org/file/bot%s/%s", getBotToken(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (update.getMessage().hasVideo()) {
            String fileId = update.getMessage().getVideo().getFileId();
            url = String.format("https://api.telegram.org/bot%s/getFile?file_id=%s", getBotToken(), fileId);
            try {
                JSONObject json = Helper.readJsonFromUrl(url);
                json = (JSONObject) json.get("result");
                String filePath = json.getString("file_path");
                text = String.format("https://api.telegram.org/file/bot%s/%s", getBotToken(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (update.getMessage().hasText()) {
            text = update.getMessage().getText();
        }
        sendMessage.setText(message.readMessage(chatId, text, username));

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
        return "1015235115:AAHrIAwEI5H8w4JKjWrasPJtUSeReeyR87M";
//        return "1208946764:AAGXHSYwDRD1h9HHb6ZKLnQChzbNnB4zlr8";
    }

    public void broadcast(File file, String messages) throws TelegramApiException {
        if (file != null) {

            SendDocument document = new SendDocument().setChatId(channelChatId)
                    .setDocument(file)
                    .setCaption(messages);
            execute(document);


        } else {
            SendMessage msg = new SendMessage().setChatId(channelChatId)
                    .setText(messages);
            execute(msg);
        }
    }

    public void broadcast(String messages) throws TelegramApiException {
        SendMessage msg = new SendMessage().setChatId(channelChatId)
                .setText(messages);
        execute(msg);
    }

}
