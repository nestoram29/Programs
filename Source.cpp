#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

class randomInt {
    int max, value;

public:
    void set_values (int);
    //void reset_value () {};
    int getRand () {
        return value;
    };
};

void randomInt::set_values (int maxValue) {
    max = maxValue;
    srand (time (NULL));
    value = rand () % max + 1;
}

int getInput () {
    int input;

    cout << "This program creates a random value between 1 and a specified max\n";
    cout << "Please enter what you wish the max to be: ";

    while (!(cin >> input) || input <= 9) {
        cin.clear ();
        cin.ignore (1000, '\n');
        cout << "Your input was invalid. Please try again!\n" <<
        "Make sure your value is numeric and greater than 9.\n" << "Max: ";
    }

    return input;
}

//Header for guessInput for main visibility
void guessInput (int max, int value);

int main () {
    int max = getInput ();
    randomInt value;
    value.set_values (max);

    guessInput (max, value.getRand());

    return 0;
}

void guessInput (int max, int value) {
    int guess;
    int numOfGuesses = 1;
    bool correct = false;

    cout << "A value between 1 and " << max << " was picked.\n" <<
	    "What is your guess?\n" << "Input: ";

    while (!correct) {
        while (!(cin >> guess) || guess < 1 || guess > max) {
	        cin.clear ();
	        cin.ignore (1000, '\n');
            cout << "Your input was invalid. Please try again!\n" <<
            "Make sure your input is between 1 and " << max << "\n";
	    }

        if (guess < value) {
	        cout << "Your guess was less than the random value. Try again\n";
	        numOfGuesses++;
	    }
        else if (guess > value) {
	        cout << "Your guess was more than the random value. Try again\n";
	        numOfGuesses++;
	    }
        else {
	        cout << "Your guess was correct! It took you " << numOfGuesses 
	        << " guesses to find the value. Thanks for playing!";
	        correct = true;
	    }
    }
}