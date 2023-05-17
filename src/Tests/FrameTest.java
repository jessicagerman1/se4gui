package Tests;

import code.drawPanel;
import code.frame;
import org.junit.Assert;
import org.junit.Test;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertNotNull;

public class FrameTest {
//    //TODO need to get mockito imported to mock so popups don't happen so tests run

    @Test
    public void checkingSetupOfFrame() {
        frame testFrame = new frame();
        assertNotNull(testFrame.getContentPane());
    }

//    @Test
//    public void testActionPerformedExit() {
//        frame testFrame = new frame();
//        ActionEvent e = new ActionEvent(testFrame, ActionEvent.ACTION_PERFORMED, "EXIT");
//        testFrame.actionPerformed(e);
//        assertFalse(testFrame.isVisible());
//    }

    @Test
    public void testActionPerformedClear() {
        frame testFrame = new frame();
        drawPanel testPanel = testFrame.getdrawPanel();
        ActionEvent e = new ActionEvent(testFrame, ActionEvent.ACTION_PERFORMED, "CLEAR");
        testFrame.actionPerformed(e);
        Assert.assertEquals(0, testPanel.getComponentCount());

    }
//
//    @Test
//    public void testActionPerformedRun() {
//        frame testFrame = new frame();
//        ActionEvent e = new ActionEvent(testFrame, ActionEvent.ACTION_PERFORMED, "RUN");
//        testFrame.actionPerformed(e);
//        assertTrue(testFrame.getdrawPanel().isVisible());
//    }
//
//    @Test
//    public void testActionPerformedLoad() {
//        frame testFrame = new frame();
//        textPanel panel = new textPanel();
//        ActionEvent e = new ActionEvent(testFrame, ActionEvent.ACTION_PERFORMED, "LOAD");
//        testFrame.actionPerformed(e);
//        assertTrue(panel.chooser.isVisible()); //asserts that the panel to laod a file is visible
//    }
//
//    @Test
//    public void testActionPerformedSaveCommands() {
//        frame testFrame = new frame();
//        ActionEvent e = new ActionEvent(testFrame, ActionEvent.ACTION_PERFORMED, "SAVE COMMANDS");
//        testFrame.actionPerformed(e);
//
//    }
//
//    @Test
//    public void testActionPerformedAbout() {
//        frame testFrame = new frame();
//        ActionEvent e = new ActionEvent(testFrame, ActionEvent.ACTION_PERFORMED, "ABOUT");
//        testFrame.actionPerformed(e);
//    }
//
//    @Test
//    public void testActionPerformedSaveImage() {
//        frame testFrame = new frame();
//        drawPanel testPanel = testFrame.getdrawPanel();
//        testPanel.drawCircle(100, 100, 50, true);
//        ActionEvent e = new ActionEvent(testFrame, ActionEvent.ACTION_PERFORMED, "SAVE IMAGE");
//        testFrame.actionPerformed(e);
//        File testSaveImage = new File("testImage.png");
//        assertTrue(testSaveImage.exists());
//    }
}
