# API OF PLAYING CARDS

## Introduction

This is a complete API for playing cards. It is written in Java 8 and is designed to be used in any Java project.
The API is designed to be as simple as possible, while still being powerful enough to be used in any card game. 
It is also designed to be as flexible as possible, so that it can be used in any project, no matter how big or small. 
The use of the API is very simple, so that it can be used by anyone, even if they have never used an API before.
We provide for the user a model of a card, a deck of cards, and a hand of cards. We also provide a graphical representation
of all of these objects, so that the user can see what is happening in the game.

## Model

The model of a card is very simple. It has a suit and a value. The suit can be one of the following: HEARTS, DIAMONDS, SPADES, CLUBS.
The value can be one of the following: ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING.

The model of a deck of cards is also very simple. It is a list of cards. The deck of cards is shuffled when it is created.

The model of a hand of cards is also very simple. It is a list of cards. The hand of cards is empty when it is created.

### Card

The card class is the model of a card. It has a suit and a value. The suit can be one of the following: HEARTS, DIAMONDS, SPADES, CLUBS.
The value can be one of the following: ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING.
The methods of the card class are as follows:
- `getSuit()` - returns the suit of the card
- `getValue()` - returns the value of the card
- `toString()` - returns a string representation of the card
- `equals(Object o)` - returns true if the card is equal to the object o, false otherwise
- `hashCode()` - returns the hash code of the card
- `compareTo(Card c)` - returns 0 if the card is equal to the card c, -1 if the card is less than the card c, 1 if the card is greater than the card c
- `getSuitAsString()` - returns the suit of the card as a string
- `getValueAsString()` - returns the value of the card as a string
- `getSuitAsUnicode()` - returns the suit of the card as a unicode character
- `getValueAsUnicode()` - returns the value of the card as a unicode character

### Deck

The deck class is the model of a deck of cards. It is a list of cards. The deck of cards is shuffled when it is created.
The methods of the deck class are as follows:
- `getCards()` - returns the list of cards in the deck
- `setCards(List<Card> cards)` - sets the list of cards in the deck to the list of cards cards
- `toString()` - returns a string representation of the deck
- `equals(Object o)` - returns true if the deck is equal to the object o, false otherwise
- `hashCode()` - returns the hash code of the deck
- `shuffle()` - shuffles the deck
- `deal()` - deals a card from the deck
- `deal(int n)` - deals n cards from the deck
- `dealAll()` - deals all the cards from the deck
- `addCard(Card c)` - adds the card c to the deck
- `addCards(List<Card> cards)` - adds the list of cards to the deck
- `removeCard(Card c)` - removes the card c from the deck
- `removeCards(List<Card> cards)` - removes the list of cards from the deck
- `removeAllCards()` - removes all the cards from the deck
- `size()` - returns the number of cards in the deck
- `isEmpty()` - returns true if the deck is empty, false otherwise
- `contains(Card c)` - returns true if the deck contains the card c, false otherwise
- `containsAll(List<Card> cards)` - returns true if the deck contains all the cards in the list of cards cards, false otherwise
- `getCard(int index)` - returns the card at the index index
- `getCards(int fromIndex, int toIndex)` - returns the list of cards from the index fromIndex to the index toIndex
- `getCards(int fromIndex)` - returns the list of cards from the index fromIndex to the end of the deck
- `getCards(Card fromCard, Card toCard)` - returns the list of cards from the card fromCard to the card toCard
- `getCards(Card fromCard)` - returns the list of cards from the card fromCard to the end of the deck
- `getCards(List<Card> cards)` - returns the list of cards in the deck that are in the list of cards cards
- `getCards(Card... cards)` - returns the list of cards in the deck that are in the array of cards cards
- `getCards(Suit suit)` - returns the list of cards in the deck that have the suit suit
- `getCards(Value value)` - returns the list of cards in the deck that have the value value
- `predicate(Predicate<Card> predicate)` - returns the list of cards in the deck that satisfy the predicate predicate
- `sort()` - sorts the deck by suit and then by value
- `sort(Comparator<Card> comparator)` - sorts the deck using the comparator comparator


