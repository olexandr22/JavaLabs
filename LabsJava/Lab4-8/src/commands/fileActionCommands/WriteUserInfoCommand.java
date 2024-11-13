package commands.fileActionCommands;

import baseClasses.User;
import commands.Command;

import java.io.*;

public class WriteUserInfoCommand implements Command {
    private User user;
    private String filePath;
    //Labs/Lab4-8/src/progFiles/userList.dat

    public WriteUserInfoCommand(User user, String filePath) {
        this.user = user;
        this.filePath = filePath;
    }

    @Override
    public void execute() {

        try {
            // Перевірка, чи існує файл
            boolean append = new java.io.File(filePath).exists();
            ObjectOutputStream oos;

            if (append) {
                // Використовуємо AppendableObjectOutputStream для додавання об'єктів без нового заголовка
                oos = new AppendableObjectOutputStream(new FileOutputStream(filePath, true));
            } else {
                // Записуємо перший об'єкт з ObjectOutputStream (пише заголовок)
                oos = new ObjectOutputStream(new FileOutputStream(filePath));
            }

            oos.writeUnshared(user);
            oos.reset();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // Не записуємо заголовок, щоб уникнути конфлікту при додаванні даних
            reset();
        }
    }

    @Override
    public String getDescription() {
        return "Запис користувачів до файлу";
    }
}
