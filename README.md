# Wordle Bot

## Overview
This Wordle bot is a Spring Boot application that interacts with the Wordle game API to make random guesses. The bot is designed to help improve your Wordle game strategy by making informed guesses.

## Setup
1. Clone the repository to your local machine.
2. Ensure you have Java 17 and Maven installed.
3. Open the project in your favorite IDE.
4. Build the project using Maven: `mvn clean install`.
5. Run the application: `mvn spring-boot:run`.

## API Endpoint
The bot interacts with the Wordle game API using the following endpoint:
- API Path: `localhost:8080/wordle/guess/random?seed={seed}`
- Method: `GET`
- Description: Provides a random guess for the Wordle game.

## Usage
To make a random guess using the Wordle bot, you can send a GET request to the API endpoint. Here is an example using cURL:
```bash
curl -X GET http://localhost:8080/wordle/guess/random?seed=1234

## Logic
In order to reduce the number of api calls needed to https://wordle.votee.dev:8000/random, I guess all the alphabets in ascending order to see if it is present in the secret word.

for example of the secret word = "theft":
The program will guess with "abcde", "fghij", "klmno", "pqrst", "uvwxy" and "z****"
and found present letters: {e,f,h,t}

Then guess the words with present letters.

Worst scenerio will be (length of the word)^2

