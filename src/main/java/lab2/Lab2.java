import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
 * 5.
 * java.io: Package for input and output, including reading and writing data, files, and objects.
 * Classes: InputStream, IOException
 *
 * java.net: Package for connecting to the web and creating and handling URLs, URIs and HTTP.
 * Classes: URI, HttpClient, HttpRequest, HttpResponse
 *
 * javax.swing: Package for interacting with a GUI components, such as windows.
 * Classes: JFrame, JLabel, ImageIcon
 *
 * java.awt: Contains all of the classes for creating user interfaces and for painting graphics and images.
 * Classes: Color, Image, BorderLayout
 *
 * javax.imageio: Package for reading and writing images.
 * Classes: ImageIO
 *
 */

void main() {
    try {
        /* 2.
         * getRandomAvatarStream() gets a random style of avatar from a list and a number to get a random
         * avatar from the selected collection. Then it makes an http request to get the information of the image.
         * It returns an InputStream. This then will save the image information to the variable avatarStream.
         *
         * 6. This is a InputStream object
         */
        var avatarStream = getRandomAvatarStream();


        /*  2.
         * showAvatar() receives the image information, creates a window, and adds the image to the window
         * with a black background to display the image collected at the previous function.
         * While it does not return any information, it does create a window for the user.
         */
        showAvatar(avatarStream);
    } catch (IOException | InterruptedException e) {
        // 1. Instance method
        /* 2.
         * The method printStackTrace() prints out the stack where the error occurred, to make
         * it easier for the developer to understand where the error happened. It does not return any values.
         */
        // 4. IOException,  from the java.io package, processes the exceptions/errors
        e.printStackTrace();
    }

}

InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    // Pick a random style
    // 6. List of Strings, referenced variable.
    String[] styles = {"adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral"};
    // 1. Math.random() is a class method
    // 1. styles.length is an instance variable
    // 6. String, referenced variable.
    var style = styles[(int) (Math.random() * styles.length)];

    // Generate a random seed
    // 1. Math.random() is a class method
    // 6. int, primary value
    var seed = (int) (Math.random() * 10000);

    // Create an HTTP request for a random avatar
    // 1. URI.create() is a class method
    // 1. String.formatted() is an instance method
    /* 2.
     * URI.create() creates an URI by parsing a string, which had the content of the style
     * and the id of the image formatted by the String method .formatted to turn the int to a string
     * to add to the URI.
     */
    // 4. Type: URI Represents a Uniform Resource Identifier reference from the Java.net Type
    // 6. URI, Object
    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));

    // 1. HttpRequest.newBuilder() is a class method
    // 1. HttpRequest.build() is an instance method
    /* 2. & 3.
     * HttpRequest.newBuilder() is a constructor method, used to instance a new Http request
     * it receives the URI and .build() method will build the request to be called later.
     * .newBuilder() returns a request builder and .build() returns a HttpRequest
     */
    // 4. HttpRequest, class from Java.net package, used to handle HTTP requests using Java
    // 6. HttpRequest Object
    var request = HttpRequest.newBuilder(uri).build();

    // Send the request
    /* 1. HttpClient.newHttpClient() is a class method (and maybe a constructor method too?)
     * 2. & 3. HttpClient.newHttpClient() constructor method used to create an instance of an HTTP Client to
     * be used, it returns a new Http Client
     */
    // 4. HttpClient class, used to send requests and retrieve their responses. from Java.net Package
    // 6. HttpClient Object
    try (var client = HttpClient.newHttpClient()) {
        /* 1. HttpClient.send() is an instance method
         * 1. HttpResponse.BodyHandlers.ofInputStream() is an class method
         * 2. HttpClient.send() will execute the request and return its results.
         * 2. HttpResponse.BodyHandlers.ofInputStream() tells the client to process the returns as an InputStream.
         * Thus, the client will return the response in a InputStream Format.
         */
        // 6. HttpResponseImpl Object
        // 4. HttpResponse from the Java.net Package used to handle the response from HTTP requests.
        // 4. Input Stream from JavaIO, class for handling inputs
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        /* 1. HttpClient.body() is an instance method
         * 2. The method body returns only the body of the response, leaving other information that isn't relevant
         * for this aside.
         */
        return response.body();
    }
}

void showAvatar(InputStream imageStream) {
    // 3. This is a constructor method, it will create an instance of the Jframe with the title PNG Viewer.
    // 7. This method receives a referenced type.
    // 6. JFrame, object
    // 4. JFrame, from the Javax.Swing package, used to create windows on the computer.
    JFrame frame = new JFrame("PNG Viewer");

    /* 1. JFrame.setDefaultCloseOperation(), JFrame.setResizable(),
     * JFrame.setSize(), JFrame.getContentPane().setBackground()
     * are all instance methods
     * 2 .setDefaultCloseOperation() defines how this instance will handle the closure of the window.
     * in this case, it will exit the application. it does not return any values.
     * 7. This method receives a referenced type.
     */
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /* 2. & 7.
     * JFrame.setResizable will update how the regular resizable windows will work, since it's false
     * it won't let the window be resized. It does not return any values.
     * It receives a primary value (boolean) as its argument.
     */
    frame.setResizable(false);

    /* 2. & 7.
     * JFrame.setSize() sets the size of the window, receiving both width and height as primary values for
     * its arguments. It does not return any values.
     */
    frame.setSize(200, 200);

    // 2. & 7. JFrame.getContentPane() returns the contentPane property, so it can be changed by
    // the .setBackground() which receives a referenced type Color as its argument, this function does not return any values.
    frame.getContentPane().setBackground(Color.BLACK);
    // 1. JFrame.EXIT_ON_CLOSE and Color.BLACK are both class variables


    try {
        // Load the PNG image
        // 1. ImageIO.read() is a class method
        // 2. ImageIO.read() reads an image from the given InputStream and decodes it, returning as BufferedImage
        // 7. The argument in this a referenced type.
        // 6. Image, object
        // 4. Image, from the java.awt package, used to handle images in Java
        Image image = ImageIO.read(imageStream);

        // Create a JLabel to display the image
        // 3. Both of these constructor methods create an ImageIcon and a JLabel, respectively.
        // 4. ImageIcon, Creates an ImageIcon from an image object. from the javax.Swing package
        // 4. JLabel, from the javax.Swing Library.Creates an area for the image.
        // 6. JLabel, object
        // 6. ImageIcon, object
        // 7. This receives a referenced type.
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        // 1. JFrame.add() is a instance method
        // 2. the .add() adds the image to the JFrame. It does not return any values.
        // 7. Both arguments are Referenced types.
        frame.add(imageLabel, BorderLayout.CENTER);

    } catch (IOException e) {
        // 1. IOException.printStackTrace() is a instance method.
        /* 2.
         * The method printStackTrace() prints out the stack where the error occurred, to make
         * it easier for the developer to understand where the error happened. It does not return any values.
         */
        // 4. IOException,  from the java.io package, processes the exceptions/errors
        e.printStackTrace();
    }
    // 1. JFrame.setVisible() is a instance method
    // 2. This is turn the frame visible to the user. it does not return any values.
    // 7. Its argument is a primary type.
    frame.setVisible(true);
}
