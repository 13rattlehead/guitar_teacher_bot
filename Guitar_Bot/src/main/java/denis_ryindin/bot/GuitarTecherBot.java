package denis_ryindin.bot;

import denis_ryindin.repo.StudentRepository;
import denis_ryindin.model.Student;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class GuitarTecherBot extends TelegramLongPollingBot{

    private final StudentRepository studentRepository = new StudentRepository();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            switch (message.split(" ")[0]){
                case "/start":
                    sendMessage(chatId, "Привет! Я бот музыкальной школы. \nТы можешь зарегистрироваться здесь с помощью /register Имя");
                    break;
                case "/register":
                    handleRegisterCommand(chatId,message);
                    break;
                case "/students":
                    handleShowStudents(chatId);
                    break;
                default:
                    sendMessage(chatId, "Неизвестная команда. Вот список доступных вам команд: /start, /register, /students");
            }
        }
    }

    private void handleShowStudents(Long chatId) {
        var students = studentRepository.findAll();
        if (students.isEmpty()) {
            sendMessage(chatId, "Пока нет зарегестрированных учеников.");
        } else {
            StringBuilder sb = new StringBuilder("Список учеников: \n");
            for (Student student : students) {
                sb.append(student.getName()).append("\n");
            }
            sendMessage(chatId, sb.toString());
        }
    }

    private void handleRegisterCommand(Long chatId, String messageText) {
        String[] parts= messageText.split(" ", 2);
        if (parts.length == 2) {
            sendMessage(chatId, "Укажи имя: /register Имя");
            return;
        }

        String name = parts[1];
        Student student = new Student();
        student.setName(name);
        studentRepository.saveOrUpdate(student);

        sendMessage(chatId, "Ученик " + name + " успешно зарегестрирован!");
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
    }

    private static final String BOT_TOKEN = "8323411156:AAEwtrimDS61VSDnRucFzYsLG1XAyZ2rLIo";
    private static final String BOT_USERNAME = "@g_teacher_bot";

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }
}
