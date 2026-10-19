import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    private int messageAction;

    private static int messageCounter = 0;
    private static int totalMessagesSent = 0;

    private static List<Message> sentMessages = new ArrayList<>();
    private static List<Message> storedMessages = new ArrayList<>();

    public Message(String recipient, String message) {

        this.messageID = generateMessageID();
        this.messageNumber = messageCounter;
        messageCounter++;

        this.recipient = recipient;
        this.message = message;

        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {

        long number = ThreadLocalRandom.current()
                .nextLong(1000000000L, 10000000000L);

        return String.valueOf(number);
    }

    public boolean checkMessageID() {

        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {

        if (recipient.matches("^\\+27\\d{9}$")) {

            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain "
                + "an international code. Please correct the number and try again.";
    }

    public String checkMessageLength() {

        if (message.length() <= 250) {

            return "Message ready to send.";
        }

        int charactersOver = message.length() - 250;

        return "Message exceeds 250 characters by "
                + charactersOver
                + ", please reduce the size.";
    }

    public String createMessageHash() {

        String[] words = message.trim().split("\\s+");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        return messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;
    }

    public void setMessageAction(int messageAction) {

        this.messageAction = messageAction;
    }

    public String SentMessage() {

        switch (messageAction) {

            case 1:

                sentMessages.add(this);
                totalMessagesSent++;

                return "Message successfully sent.";

            case 2:

                return "Press 0 to delete the message.";

            case 3:

                storedMessages.add(this);
                storeMessage();

                return "Message successfully stored.";

            default:

                return "Invalid option.";
        }
    }

    public String printMessages() {

        if (sentMessages.isEmpty()) {

            return "No messages have been sent.";
        }

        StringBuilder output = new StringBuilder();

        for (Message currentMessage : sentMessages) {

            output.append("Message ID: ")
                    .append(currentMessage.messageID)
                    .append("\n");

            output.append("Message Hash: ")
                    .append(currentMessage.messageHash)
                    .append("\n");

            output.append("Recipient: ")
                    .append(currentMessage.recipient)
                    .append("\n");

            output.append("Message: ")
                    .append(currentMessage.message)
                    .append("\n\n");
        }

        return output.toString();
    }

    public int returnTotalMessages() {

        return totalMessagesSent;
    }

    public void storeMessage() {

        try {

            FileWriter writer = new FileWriter("messages.json");

            writer.write("[\n");

            for (int i = 0; i < storedMessages.size(); i++) {

                Message stored = storedMessages.get(i);

                writer.write("  {\n");
                writer.write("    \"messageID\": \""
                        + escapeJSON(stored.messageID) + "\",\n");

                writer.write("    \"messageNumber\": "
                        + stored.messageNumber + ",\n");

                writer.write("    \"recipient\": \""
                        + escapeJSON(stored.recipient) + "\",\n");

                writer.write("    \"message\": \""
                        + escapeJSON(stored.message) + "\",\n");

                writer.write("    \"messageHash\": \""
                        + escapeJSON(stored.messageHash) + "\"\n");

                writer.write("  }");

                if (i < storedMessages.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");
            }

            writer.write("]");

            writer.close();

        } catch (IOException exception) {

            System.out.println(
                    "An error occurred while storing the message."
            );
        }
    }

    private String escapeJSON(String text) {

        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");
    }


    public String getMessageID() {

        return messageID;
    }

    public int getMessageNumber() {

        return messageNumber;
    }

    public String getRecipient() {

        return recipient;
    }

    public String getMessage() {

        return message;
    }

    public String getMessageHash() {

        return messageHash;
    }


    public static void resetMessageData() {

        messageCounter = 0;
        totalMessagesSent = 0;
        sentMessages.clear();
        storedMessages.clear();
    }
}