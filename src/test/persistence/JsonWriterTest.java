package persistence;

import model.ManagementList;
import model.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// This class references code from this repo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ManagementList ml = new ManagementList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //expected
        }
    }

    @Test
    void testWriterEmptyManagementList() {
        try {
            ManagementList ml = new ManagementList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyManagementList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyManagementList.json");
            ml = reader.read();
            assertEquals(0, ml.getProperties().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralManagementList() {
        try {
            ManagementList ml = new ManagementList();

            Property p1 = new Property();
            p1.setAddress("1 ubc");
            p1.setPrice(1000.0);
            p1.setCapacity(2);
            p1.setPaid(false);
            p1.setStatus(true);

            Property p2 = new Property();
            p2.setAddress("1 sfu");
            p2.setPrice(500.0);
            p2.setCapacity(1);
            p2.setPaid(false);
            p2.setStatus(false);

            ml.addProperty(p1);
            ml.addProperty(p2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralManagementList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralManagementList.json");
            ml = reader.read();
            List<Property> properties = ml.getProperties();
            assertEquals(2, properties.size());
            checkProperty("1 ubc", 1000.0, 2, true, false, properties.get(0));
            checkProperty("1 sfu", 500.0, 1, false, false, properties.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
