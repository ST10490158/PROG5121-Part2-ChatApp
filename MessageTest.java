import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MessageTest {

    @Before
    public void resetTests() {

        Message.resetMessageData();
    }

    @Test
    public void testMessageLengthSuccess() {

        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage =
                "A".repeat(251);

        Message message =
                new Message(
                        "+27718693002",
                        longMessage
                );

        assertEquals(
                "Message exceeds 250 characters by 1, please reduce the size.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testRecipientCorrectlyFormatted() {

        Message message =
                new Message(
                        "+27718693002",
                        "Hi Mike, can you join us for dinner tonight?"
                );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {

        Message message =
                new Message(
                        "0857597589",
                        "Hi Keegan, did you receive the payment?"
                );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain "
                + "an international code. Please correct the number and try again.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testMessageIDCreated() {

        Message message =
                new Message(
                        "+27718693002",
                        "Hi Mike, can you join us for dinner tonight?"
                );

        assertTrue(
                message.checkMessageID()
        );

        assertEquals(
                10,
                message.getMessageID().length()
        );
    }

    @Test
    public void testMessageHash() {

        Message message =
                new Message(
                        "+27718693002",
                        "Hi Mike, can you join us for dinner tonight?"
                );

        String expected =
                message.getMessageID().substring(0, 2)
                + ":0:HITONIGHT?";

        assertEquals(
                expected,
                message.getMessageHash()
        );
    }

    @Test
    public void testSendMessage() {

        Message message =
                new Message(
                        "+27718693002",
                        "Hi Mike, can you join us for dinner tonight?"
                );

        message.setMessageAction(1);

        assertEquals(
                "Message successfully sent.",
                message.SentMessage()
        );
    }

    @Test
    public void testDiscardMessage() {

        Message message =
                new Message(
                        "0857597589",
                        "Hi Keegan, did you receive the payment?"
                );

        message.setMessageAction(2);

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage()
        );
    }

    @Test
    public void testStoreMessage() {

        Message message =
                new Message(
                        "+27718693002",
                        "This message will be stored."
                );

        message.setMessageAction(3);

        assertEquals(
                "Message successfully stored.",
                message.SentMessage()
        );
    }

    @Test
    public void testReturnTotalMessages() {

        Message message =
                new Message(
                        "+27718693002",
                        "Hi Mike, can you join us for dinner tonight?"
                );

        message.setMessageAction(1);

        message.SentMessage();

        assertEquals(
                1,
                message.returnTotalMessages()
        );
    }
}