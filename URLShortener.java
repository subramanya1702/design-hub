import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class URLShortener {
    private static final String BASE_URL = "https://urlshortener/";
    private final Map<String, String> urlMap = new HashMap<>();

    private String extractUUIDFromURL(final String shortURL) {
        return shortURL.substring(BASE_URL.length());
    }

    public void generateShortenedURL(final String longURL) {
        final String uuid = UUID.randomUUID().toString();
        System.out.println("Short URL: " + BASE_URL + uuid);
        this.urlMap.put(uuid, longURL);
    }

    public String getLongURL(final String shortURL) {
        if (shortURL.startsWith(BASE_URL) && shortURL.length() > BASE_URL.length()) {
            final String key = extractUUIDFromURL(shortURL);

            if (!this.urlMap.containsKey(key)) {
                System.out.println("URL is invalid. Try again with a valid URL.");
                return "";
            }

            return this.urlMap.get(key);
        } else {
            System.out.println("Base URL is invalid. Try again with a valid URL.");
            return "";
        }
    }

    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();

        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the function you want to perform\n1. URL shortening\n2. Get back the long URL\n3. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                System.out.println("Enter the URL you wish to shorten");
                String longURL = scanner.nextLine();

                urlShortener.generateShortenedURL(longURL);
            } else if (choice == 2) {
                System.out.println("Enter the short URL");
                String shortURL = scanner.nextLine();

                System.out.println(urlShortener.getLongURL(shortURL));
            } else if (choice == 3) {
                System.exit(0);
            } else {
                System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
