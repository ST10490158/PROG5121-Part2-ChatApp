# PROG5121 QuickChat

## Overview

QuickChat is a Java console application developed for the PROG5121 Portfolio of Evidence.

The project was developed in three parts and includes user registration, login, message management, message storage, searching and reporting.

## Part 1 - Registration and Login

The application allows a user to:

- Register using a first name and surname.
- Create a username.
- Validate the username format.
- Create a secure password.
- Validate password complexity.
- Enter and validate a South African cellphone number.
- Log in using the registered username and password.

## Part 2 - Sending Messages

After successfully logging in, the user can access QuickChat.

The application allows the user to:

- Select how many messages they want to enter.
- Create and send messages.
- Validate recipient cellphone numbers.
- Limit messages to a maximum of 250 characters.
- Generate a unique 10-digit message ID.
- Generate a message hash.
- Send a message.
- Disregard a message.
- Store a message for later.
- Store messages in a JSON file.
- Display the total number of messages sent.

## Part 3 - Message Management and Reports

The final PoE extends QuickChat with message arrays and reporting functionality.

The application stores:

- Sent messages.
- Disregarded messages.
- Stored messages.
- Message hashes.
- Message IDs.

The user can also:

- Display sent messages.
- Display the sender and recipient of stored messages.
- Display the longest stored message.
- Search for a message using its message ID.
- Search for all messages sent to a specific recipient.
- Delete a message using its message hash.
- Display a complete stored-message report.

## Testing

JUnit tests are included for:

- Username validation.
- Password validation.
- Cellphone validation.
- Login functionality.
- Message length validation.
- Recipient validation.
- Message ID creation.
- Message hash creation.
- Sending messages.
- Disregarding messages.
- Storing messages.
- JSON storage.
- Sent-message arrays.
- Longest-message functionality.
- Message ID searches.
- Recipient searches.
- Message deletion using a message hash.
- Message reporting.

## Technologies Used

- Java 21
- Visual Studio Code
- JUnit 4
- JSON file storage
- Git
- GitHub
- GitHub Actions