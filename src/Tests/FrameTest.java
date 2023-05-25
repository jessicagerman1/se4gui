package Tests;

import code.drawPanel;
import code.frame;
import org.junit.Assert;
import org.junit.Test;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertNotNull;

/**
 *testing the frame set up works
 */
public class FrameTest {
    @Test
    public void checkingSetupOfFrame() {
        frame testFrame = new frame();
        assertNotNull(testFrame.getContentPane());
    }

    /**
     *testing action performed works with clear command
     */
    @Test
    public void testActionPerformedClear() {
        frame testFrame = new frame();
        drawPanel testPanel = testFrame.getdrawPanel();
        ActionEvent e = new ActionEvent(testFrame, ActionEvent.ACTION_PERFORMED, "CLEAR");
        testFrame.actionPerformed(e);
        Assert.assertEquals(0, testPanel.getComponentCount());

    }
}