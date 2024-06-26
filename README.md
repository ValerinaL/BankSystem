# Bank System Console Application

This is a console application for a bank system that allows users to create accounts, perform transactions, and manage their finances.

## Setup Instructions

### 1. Clone the Repository

Clone this repository to your local machine using the following command:

bash:
git clone https://github.com/ValerinaL/bank-system.git



### 2. Compile the Code

Navigate to the project directory and compile the Java source files using `javac`:

bash:
cd bank-system
javac *.java

### 3. Run the Application

Run the main class of the application to start the bank system:

bash: java Main


Follow the instructions provided in the console to interact with the bank system.

## Features

- Create a bank with all required values
- Create an account
- Perform both flat fee and percent fee transactions from one account to another
- Withdraw and deposit money to an account
- See a list of transactions for any account
- Check account balance for any account
- See a list of bank accounts
- Check bank total transaction fee amount
- Check bank total transfer amount

## Implementation Details

- Object-oriented design principles are followed, including abstract classes, inheritance, overriding/overloading methods.
- Exceptions and error handling are implemented to provide proper error messages.
- In-memory storage is used for bank, account, and transaction data.
- Unit tests are provided to ensure code quality and functionality.

## Bonus Points

- Database Integration: Data can be stored and read from an in-memory database (H2 Database).
- Spring Boot RESTful Web Service: A RESTful web service is designed to expose bank system functionality.
- Unit/Integration Tests: Tests are provided to validate the application's behavior.

## Dependencies

- Java Development Kit (JDK) 8 or higher
- H2 Database (for database integration)
- JUnit (for testing)

## Contributors

- Valerina Lahu 

