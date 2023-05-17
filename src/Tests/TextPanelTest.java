package Tests;

import org.junit.Assert;
import org.junit.Test;

import code.*;
import static org.junit.Assert.*;

public class TextPanelTest {

    /**
     * testing that the textArea isn't null when constructing a code.textPanel object
     */
    @Test
    public void testTextPanelConstructor() {
        textPanel panel = new textPanel();
        assertNotNull(panel.textArea);
    }

    /**
     * testing that the textArea is editable after having constructed code.textPanel object
     */
    @Test
    public void testTextAreaIsEditable() {
        textPanel panel = new textPanel();
        assertTrue(panel.textArea.isEditable());
    }

    /**
     * testing whether the line wrapping works
     */
    @Test
    public void testTextAreaLineWraps() {
        textPanel panel = new textPanel();
        assertTrue(panel.textArea.getLineWrap());
    }

    /**
     * testing getText method returns correct text in text area
     */
    @Test
    public void testGetTextMethod() {
        textPanel panel = new textPanel();
        // Set up some sample text to display in the text area
        String expectedText = "Testing";
        panel.textArea.append(expectedText);

        // Verify that the getText() method returns the expected text
        Assert.assertEquals(panel.getText(), expectedText);
        }
}
