import Classes.Locations;
import Connectors.GetPlaceInformation;
import Parsers.Parser;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class Main {
    public static final String API_KEY = "AIzaSyC0FwITA3cBrD4SBMPW2OgkRqANzBskndU";

    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        // get this from the telegram bot
        String userInput1 = "ice cream museum";
        String userInput2 = "mbs";

        String jsonStr = GetPlaceInformation.getPlaceInfo(userInput1);
        Locations startLocation = Parser.parse(jsonStr);
        jsonStr = GetPlaceInformation.getPlaceInfo(userInput2);
        Locations endLocation = Parser.parse(jsonStr);
        return;
    }
}
