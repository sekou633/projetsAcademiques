# BLACKJACK

## Description

Blackjack is a card game where the goal is to get as close to 21 as possible without going over. It is played with 
one or more decks of 52 cards. The values of the cards are as follows:

| Card | Value   |
|------|---------|
| 2    | 2       |
| 3    | 3       |
| 4    | 4       |
| 5    | 5       |
| 6    | 6       |
| 7    | 7       |
| 8    | 8       |
| 9    | 9       |
| 10   | 10      |
| J    | 10      |
| Q    | 10      |
| K    | 10      |
| A    | 1 or 11 |

The player and the dealer are each initially dealt two cards. The player can then request additional cards, one at a time, 
until they either decide to "stand" or exceed 21. If the player exceeds 21, they "bust" and lose. If the player chooses to
"stand", their total is compared to the dealer's total. If the dealer has more than the player, the dealer wins. If the
dealer has less than the player, the player wins. If the dealer has the same total as the player, it is a "push" and no
one wins.

## Getting Started

### Prerequisites

* [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Ant](https://ant.apache.org/bindownload.cgi)
* [Git](https://git-scm.com/downloads)
* [JUnit](https://junit.org/junit5/docs/current/user-guide/#running-tests-build-gradle)

### Installing

1. Clone the repository
2. Run `ant` to build the project
3. Run `ant test` to run the tests
4. Run `ant run` to run the game
5. Run `ant clean` to clean the project
6. Run `ant doc` to generate the documentation
7. Run `ant package` to generate a jar file
8. Run `ant run-jar` to run the jar file
9. Run `ant clean-jar` to clean the jar file
10. Run `ant clean-all` to clean the project and the jar file
11. Run `ant package-all` to generate a jar file and run it

## Running the tests

Run `ant test` to run the tests. The tests are located in the `test` directory. The tests are written using JUnit 5.

## Architecture

The project is created using the Model-View-Controller (MVC) architecture. Its purpose is to separate the application
into three main components: the model, the view, and the controller. The model is responsible for the data and the
business logic. The view is responsible for the user interface. The controller is responsible for the communication
between the model and the view.

## Sequence Diagram

The sequence diagram below shows the flow of the game. The player is represented by the `Player` class and the dealer
is represented by the `Dealer` class. The `Game` class is responsible for the communication between the player and the
dealer. The `Game` class is also responsible for the game logic.

### Game
