package kz.gairu;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botApi = new TelegramBotsApi();
        try {
            botApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "ThemeMaker_4drwng_Bot";
        //возвращаем юзера
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String msg = message.getText();

            if ("/start".equals(msg)) {
                sendMsg(message, "Приветсвую! С чего начнем?");
            } else if ("/help".equals(msg)) {
                sendMsg(message, "Чем могу помочь?");
            } else if ("/info".equals(msg)) {
                sendMsg(message, "Я помогу выбрать или подберу Вам тему для рисования." +
                        "Нажмите /random чтобы получить тему.");

            }
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButton(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void setButton(SendMessage sendMessage){
        List<KeyboardRow> keyboardRowsList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/info"));
        keyboardFirstRow.add(new KeyboardButton("/random"));
        keyboardRowsList.add(keyboardFirstRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        replyKeyboardMarkup.setKeyboard(keyboardRowsList);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    @Override
    public String getBotToken() {
        return "1418809515:AAGnLeg-tdBNMHdROT9oYw8cfaPOvW9tSl0";
        //Токен бота
    }


}

