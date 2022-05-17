import Classes.Locations;
import Connectors.GetPlaceInformation;
import Parsers.Parser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    private static boolean addingLocation = false;

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        String command = update.getMessage().getText();
        message.setChatId(update.getMessage().getChatId().toString()) ;

        if (command.equals("/addplace")) {
            message.setText("please enter your text and key in '/done' when you are done adding locations!");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            addingLocation = true;
        }

        if (command.equals("/done")) {
            message.setText("Added all locations to the list!");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            addingLocation = false;
        }

        else {
            // user is in the process of adding locations
            if (addingLocation) {
                String jsonStr = GetPlaceInformation.getPlaceInfo(command);
                Locations startLocation = Parser.parse(jsonStr);
                message.setText(startLocation.toString());
                // add information to a list of clients chat ids
                
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername(){
        return "Easy Itinerary";
    }

    @Override
    public String getBotToken(){
        return "5391896873:AAFj4sPVXl-bB-evlAW_ezlkPuJBwPOjZ6Q";
    }


}
