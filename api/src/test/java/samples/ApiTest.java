package samples;

import org.testng.annotations.BeforeSuite;
import demo.automationproject.utils.properties.Properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import static io.restassured.RestAssured.*;

public class ApiTest {

    @BeforeSuite
    public void getProperties() {
        baseURI = Properties.getProperty("baseURI");
    }

    /**
     * Compares the given string to the contents of the given file.
     *
     * @param s the string to compare
     * @param f the file that contains the contents to compare to the given string
     * @return Returns true when the file contents matches the given string.
     * @throws IOException
     */
    protected boolean stringMatchesFileContent(String s, File f) throws IOException {
        // Assume null is an empty string.
        if (s == null) {
            s = "";
        }

        if (f == null) {
            throw new FileNotFoundException("The file value given was null. A valid file must be used for comparison.");
        }

        if (!f.exists()) {
            throw new FileNotFoundException("The file given was not found: " + f.getPath());
        }

        // Attempt to read the file's contents.
        String fileContent;
        try {
            fileContent = Files.readAllLines(f.toPath()).toString();
        } catch (IOException e) {
            throw new IOException("Failed to compare file contents: " + e.getMessage());
        }

        return fileContent.equals(f);
    }

    /**
     * Retrieves the resource from the given relative path within the resources directory.
     *
     * @param relativePath the relative path within the resources directory
     * @return the File within the resources directory.
     * @throws FileNotFoundException
     */
    protected File getResource(String relativePath) throws FileNotFoundException {
        try {
            URL url = this.getClass().getClassLoader().getResource(relativePath);

            if (url == null) {
                throw new FileNotFoundException("The given path '" + relativePath + "' does not exist");
            }

            return new File(url.toURI());
        } catch (URISyntaxException | FileNotFoundException e) {
            throw new FileNotFoundException("Resource file not found: " + e.getMessage());
        }
    }
}
