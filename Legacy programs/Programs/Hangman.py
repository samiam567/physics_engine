import random
score = 0
HINTS = 5
HANGMANPICS = ['''

  +---+
  |   |
      |
      |
      |
      |
=========''', '''

  +---+
  |   |
  O   |
      |
      |
      |
=========''', '''

  +---+
  |   |
  O   |
  |   |
      |
      |
=========''', '''

  +---+
  |   |
  O   |
 /|   |
      |
      |
=========''', '''

  +---+
  |   |
  O   |
 /|\  |
      |
      |
=========''', '''

  +---+
  |   |
  O   |
 /|\  |
 /    |
      |
=========''', '''

  +---+
  |   |
  O   |
 /|\  |
 / \  |
      |
=========''']
words = 'ant baboon badger bat bear beaver camel cat clam cobra cougar coyote crow deer dog donkey duck eagle ferret fox frog goat goose hawk lion lizard llama mole monkey moose mouse mule newt otter owl panda parrot pigeon python rabbit ram rat raven rhino salmon seal shark sheep skunk sloth snake spider stork swan tiger toad trout turkey turtle weasel whale wolf wombat zebra'.split()

def hint(correctLetters, alreadyGuessed, secretWord, HINTS): #this function gives the player a hint
    hintDone = False
    if HINTS > 0: #checks if you have a hint left
        HINTS = HINTS - 1
        HINTS = str(HINTS)
        print("You have " + HINTS + " hints left")
        HINTS = int(HINTS)
        while(hintDone != True):
            alphabet = list("abcdefghijklmnopqrstuvwxyz")
            hintLetterIndex = random.randint(-1,len(alphabet) -1)
            hintLetter = alphabet[hintLetterIndex]
            if (hintLetter not in correctLetters) and (hintLetter not in alreadyGuessed) and (hintLetter not in secretWord):
                print(hintLetter + " is not in the secret word")
                alreadyGuessed = alreadyGuessed + hintLetter
                returns = [alreadyGuessed, HINTS]
                return returns
                hintDone = True
    else:
        print("You do not have any guesses left")
    returns = [alreadyGuessed, HINTS]
    return returns
def getRandomWord(wordList):
    # This function returns a random string from the passed list of strings.
    wordIndex = random.randint(0, len(wordList) - 1)
    return wordList[wordIndex]

def displayBoard(HANGMANPICS, missedLetters, correctLetters, secretWord):
    print(HANGMANPICS[len(missedLetters)])
    print()

    print('Missed letters:', end=' ')
    for letter in missedLetters:
        print(letter, end=' ')
    print()

    blanks = '_' * len(secretWord)

    for i in range(len(secretWord)): # replace blanks with correctly guessed letters
        if secretWord[i] in correctLetters:
            blanks = blanks[:i] + secretWord[i] + blanks[i+1:]
            

    for letter in blanks: # show the secret word with spaces in between each letter
        print(letter, end=' ')
    print()

def getGuess(alreadyGuessed, HINTS):
    # Returns the letter the player entered. This function makes sure the player entered a single letter, and not something else.
    while True:
        print('Guess a letter.')
        guess = input()
        guess = guess.lower()
        if guess == "hint":
            returns = hint(correctLetters, alreadyGuessed, secretWord, HINTS)
            alreadyGuessed = returns[0]
            HINTS = returns[1]
        elif len(guess) != 1:
            print('Please enter a single letter.')
        elif guess in alreadyGuessed:
            print('You have already guessed that letter. Choose again.')
        elif guess not in 'abcdefghijklmnopqrstuvwxyz':
            print('Please enter a LETTER.')
        else:
            returns = [guess , alreadyGuessed , HINTS]
            return returns

def playAgain():
    # This function returns True if the player wants to play again, otherwise it returns False.
    print('Do you want to play again? (yes or no)')
    return input().lower().startswith('y')


print('H A N G M A N')
missedLetters = ''
correctLetters = ''
secretWord = getRandomWord(words)
gameIsDone = False

while True:
    displayBoard(HANGMANPICS, missedLetters, correctLetters, secretWord)

    # Let the player type in a letter.
    GuessHintsAlreadyGuessed = getGuess(missedLetters + correctLetters, HINTS)
    guess = GuessHintsAlreadyGuessed[0]  #gets guess from the list of returns
    alreadyGuessed = GuessHintsAlreadyGuessed[1] #gets alreadyGuessed from the list of returns
    HINTS = GuessHintsAlreadyGuessed[2] #gets HINTS from the list of returns
    if guess in secretWord:
        correctLetters = correctLetters + guess

        # Check if the player has won
        foundAllLetters = True
        for i in range(len(secretWord)):
            if secretWord[i] not in correctLetters:
                foundAllLetters = False
                break
        if foundAllLetters:
            displayBoard(HANGMANPICS, missedLetters, correctLetters, secretWord)
            print("-")
            print('Yes! The secret word is "' + secretWord + '"! You have won!')
            score = score + 1 #add one game to the player's score
            HINTS = HINTS + 2
            gameIsDone = True
    else:
        missedLetters = missedLetters + guess

        # Check if player has guessed too many times and lost
        if len(missedLetters) == len(HANGMANPICS) - 1:
            displayBoard(HANGMANPICS, missedLetters, correctLetters, secretWord)
            print("")
            print('You have run out of guesses!\nAfter ' + str(len(missedLetters)) + ' missed guesses and ' + str(len(correctLetters)) + ' correct guesses, the word was "' + secretWord + '"')
            score = score - 1
            HINTS = HINTS + 1
            gameIsDone = True

    # Ask the player if they want to play again (but only if the game is done).
    if gameIsDone:
        score = str(score)
        print("You have " + score + " point(s)!")
        score = int(score)
        if playAgain():
            missedLetters = ''
            correctLetters = ''
            gameIsDone = False
            secretWord = getRandomWord(words)
        else:
            break

 
