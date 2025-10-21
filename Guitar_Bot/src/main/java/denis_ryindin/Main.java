package denis_ryindin;

import denis_ryindin.bot.GuitarTecherBot;
import denis_ryindin.repo.HibernateUtil;
import org.hibernate.Session;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.close();
            System.out.println("Подключено успешно");

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new GuitarTecherBot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}