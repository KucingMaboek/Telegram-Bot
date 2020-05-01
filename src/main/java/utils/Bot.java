package utils;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class Bot extends TelegramLongPollingBot {
    private String channelChatId = "@PLN_NewsFeed";

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        MessageHandler message = new MessageHandler();
        String chatId = String.valueOf(update.getMessage().getChatId());
        String text = update.getMessage().getText();
        sendMessage.setText(message.ReadMessage(chatId,text));
        //        String message = String.valueOf(update.getMessage().getText().charAt(0));

        //Ngetes
//        Statement statement = null;
//        try {
//            statement = Helper.connectDatabase().createStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        long chatId = update.getMessage().getChatId();
//        String text = update.getMessage().getText();
//        String query = String.format("insert into tes values (\'%s\',\'%s\');", chatId, text);
//        sendMessage.setText("Chat ID: " + chatId + "\n" + "Pesan: " + text);
//        try {
//            statement.executeUpdate(query);
//            execute(sendMessage);
//        } catch (TelegramApiException | SQLException e) {
//            e.printStackTrace();
//        }
        //end of ngetes


//        if (message.equals("/")) {
//            sendMessage.setText("ini perintah");
//        } else {
//            sendMessage.setText(
//                    "Selamat datang di Bot PLN, berikut layanan yang kami tawarkan untuk anda:\n" +
//                            "\n" +
//                            "Ingin melakukan instalasi dirumah/toko/perusahaan anda? mudah! cukup isi data anda dengan menggunakan perintah\n" +
//                            "/instalasi_listrik\n" +
//                            "\n" +
//                            "Listrik di rumah anda bermasalah? isi data mengenai gangguannya dengan perintah\n" +
//                            "/lapor_gangguan\n" +
//                            "\n" +
//                            "Melihat ada oknum yang sedang melakukan tindakan berupa kecurangan/mengakali listrik?? Laporkan sekarang!\n" +
//                            "/lapor_kecurangann\n" +
//                            "\n" +
//                            "Butuh konsultasi secara langsung? Ajukan permintaanmu segera! kami akan hubungi dalam waktu 1x24 jam selama jam kerja!\n" +
//                            "/live_chat"
//            );
//        }
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