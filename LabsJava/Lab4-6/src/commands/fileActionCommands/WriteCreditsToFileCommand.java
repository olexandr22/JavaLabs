package commands.fileActionCommands;

import baseClasses.Credit;
import commands.Command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class WriteCreditsToFileCommand implements Command {
    private String filePath;
    private List<Credit> credits;

    public WriteCreditsToFileCommand(String filePath, List<Credit> credits) {
        this.filePath = filePath;
        this.credits = credits;
    }

    @Override
    public void execute() {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(credits);
            System.out.println("Дані успішно записані до файлу");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Запис кредитів до файлу";
    }
}
