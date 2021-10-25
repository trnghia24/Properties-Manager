package persistence;

import model.ManagementList;
import model.Property;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// This class references code from this repo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ManagementList ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //expected
        }
    }

    @Test
    void testReaderEmptyManagementList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyManagementList.json");
        try {
            ManagementList ml = reader.read();
            assertEquals(0, ml.getProperties().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralManagementList.json");
        try {
            ManagementList ml = reader.read();
            List<Property> properties = ml.getProperties();
            assertEquals(2, properties.size());
            checkProperty("1 ubc", 1000.0, 2, true, false, properties.get(0));
            checkProperty("1 sfu", 500.0, 1, false, false, properties.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
