package filemanipulation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HumanFileManager {
    private List<Human> humans = new ArrayList<>();

    public List<Human> getHumans() {
        return humans;
    }

    public void readHumansFromFile(Path path) {
        List<String> lines = readFromFile(path);
        readFromLines(lines);
    }

    private void readFromLines(List<String> lines) {
        for (String line : lines) {
            humans.add(new Human(
                    line.split(";")[0],
                    line.split(";")[1]
            ));
        }
    }

    private List<String> readFromFile(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
            return lines;
        } catch (IOException ioe) {
            throw new IllegalStateException("Can't read file!", ioe);
        }
    }

    public void writeMaleHumansToFile(Path path) {
        List<String> maleHumans = findMaleHumans();
        writeToFile(path, maleHumans);
    }

    private List<String> findMaleHumans() {
        List<String> maleHumans = new ArrayList<>();
        for (Human human : humans) {
            char firstChar = human.getIdentityNumber().charAt(0);
            if (firstChar == '1' || firstChar == '3') {
                maleHumans.add(human.getName() + ";" + human.getIdentityNumber());
            }
        }
        return maleHumans;
    }

    private void writeToFile(Path path, List<String> maleHumans) {
        try {
            Files.write(path, maleHumans);
        }
        catch (IOException ioe) {
            throw new IllegalStateException("Can't write file!", ioe);
        }
    }
}
