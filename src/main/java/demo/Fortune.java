package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Fortune class provides functionality to read fortunes from a JSON file
 * and retrieve a random fortune. It also includes a method to print the
 * fortune slowly to the console.
 */
public class Fortune {

    /**
     * A static instance of Random used to generate random numbers for selecting
     * fortunes.
     */
    private static final Random RANDOM = new Random();

    /**
     * A list that holds the fortunes read from the JSON file.
     */
    private final ArrayList<String> fortunes = new ArrayList<>();

    /**
     * Constructs a Fortune object by reading and parsing a JSON file containing
     * fortunes. The JSON file is expected to be located in the classpath as
     * "fortunes.json". This constructor processes the JSON data and populates
     * the list of fortunes.
     *
     * @throws JsonProcessingException if there is an error processing the JSON
     * data.
     */
  public Fortune() throws JsonProcessingException {
        // Scan the file into the array of fortunes
        String json = readInputStream(ClassLoader.getSystemResourceAsStream("fortunes.json"));
        ObjectMapper omap = new ObjectMapper();
        JsonNode root = omap.readTree(json);
        JsonNode data = root.get("data");
        Iterator<JsonNode> elements = data.elements();
        while (elements.hasNext()) {
            JsonNode quote = elements.next().get("quote");
            fortunes.add(quote.asText());
        }
    }

    /**
     * Reads the input stream and returns its content as a string.
     *
     * @param is the input stream to read from.
     * @return the content of the input stream as a string.
     */
    private String readInputStream(InputStream is) {
        StringBuilder out = new StringBuilder();
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

        } catch (IOException e) {
            Logger.getLogger(Fortune.class.getName()).log(Level.SEVERE, null, e);
        }
        return out.toString();
    }

    /**
     * Returns a random fortune from the list of fortunes.
     *
     * @return a random fortune as a string.
     */
    public String randomFortune() {
        // Pick a random number
        int r = RANDOM.nextInt(fortunes.size());
        // Use the random number to pick a random fortune
        return fortunes.get(r);
    }

    /**
     * Prints a random fortune to the console slowly, character by character.
     *
     * @throws InterruptedException if the thread is interrupted while sleeping.
     */
    private void printRandomFortune() throws InterruptedException {
        String f = randomFortune();
        // Print out the fortune s.l.o.w.l.y
        for (char c : f.toCharArray()) {
            System.out.print(c);
            Thread.sleep(100);
        }
        System.out.println();
    }

    /**
     * The main method to execute the Fortune program.
     *
     * @param args the command line arguments.
     * @throws InterruptedException if the thread is interrupted while sleeping.
     * @throws JsonProcessingException if there is an error processing the JSON
     * data.
     */
    public static void main(String[] args) throws InterruptedException, JsonProcessingException {
        Fortune fortune = new Fortune();
        fortune.printRandomFortune();
    }
}