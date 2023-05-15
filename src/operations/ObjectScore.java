package operations;
import java.io.*;
import java.util.ArrayList;

public class ObjectScore implements Serializable {
    private int score;
    private String nick;

    public ObjectScore(int score, String nick) {
        this.score = score;
        this.nick = nick;
    }

    public int getScore() {
        return score;
    }

    public String getNick() {
        return nick;
    }

    public static void writeObject(Object o) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/operations/scores.txt"))) {
            outputStream.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ObjectScore> readObjects() {
        ArrayList<ObjectScore> arrayList = new ArrayList<>();
        File file = new File("src/operations/scores.txt");

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                arrayList = (ArrayList<ObjectScore>) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return arrayList;
    }
}
