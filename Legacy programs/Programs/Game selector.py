import random
import turtle
hangmanTimes = 0
hot_and_coldTimes = 0
trivial_persuiteTimes = 0
lines_n_arrowsTimes = 0


HINTS = 5

Finchy = input("Do you have a finch connected?")
def WASD(W):
    if (True):
        if (True):
            if (True):
                print("-")
                if (W == "w"):
                    turtle.forward(50)
                elif (W == "a"):
                    turtle.left(90)
                elif (W == "s"):
                    turtle.right(180)
                    turtle.forward(25)
                elif (W == "d"):
                    turtle.right(90)
                elif (W == "ss"):
                    turtle.backward(25)
                elif (W == "c"):
                    turtle.clear()
                elif (W == "r"):
                    turtle.reset()
                elif (W == "bgc"):
                   color = input("What color?")
                   turtle.bgcolor(color)
                elif (W == "shape"):
                    shape = input("What shape?")
                    turtle.shape(shape)
                elif (W == "pic"):
                    pic = input("Type the name of the pic")
                    turtle.bgpic(pic)
                elif (W == "efill"):
                    turtle.end_fill()
                elif (W == "sleep"):
                    print("Going to Sleep")
                    WASD("st")
                    wer = 0
                    while (wer < 10):
                        WASD("a")
                        wer = (wer+1)
                    WASD("ht")
                    WASD("bcb")
                    input()
                    wer = 0
                    while (wer < 10):
                        WASD("a")
                        wer = (wer+1)
                    WASD("ht")
                    WASD("r")
                    WASD("ht")
                    turtle.bgcolor("white")
                    WASD("stop")
                elif (W == "shutdown script"):
                    print("Running Shutdown Scripts")
                    turtle.shape("turtle")
                    wer = 0
                    WASD("st")
                    while (wer < 10):
                        WASD("a")
                        wer = (wer+1)
                    WASD("ht")
   #                 turtle.bgpic("shutdown")
                    WASD("bcb")
                    WASD("www")
                    WASD("d")
                    WASD("ww")
                elif (W == "fill"):
                    turtle.begin_fill()
                elif (W == "stop"):
                    return(31415926535897)
                elif (W == "bcb"):
                    turtle.bgcolor("black")
                elif (W == "2x2"):
                    WASD("w")
                    WASD("a")
                    WASD("w")
                    WASD("a")
                    WASD("w")
                    WASD("a")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("w")
                    WASD("d")
                    WASD("w")
                    WASD("d")
                    WASD("w")
            
        
                elif (W == "ww"):
                    turtle.forward(25)
                elif (W == "www"):
                    turtle.forward(100)
                elif (W == "wwww"):
                    turtle.forward(200)
                elif (W == "bgw"):
                    turtle.bgcolor("white")
                elif (W == "w2"):
                    turtle.forward(9500)
                elif (W == "w1"):
                    turtle.forward(4750)
                elif (W == "ht"):
                    turtle.ht()
                elif (W == "st"):
                    turtle.st()
                else:
                    print("invalid move")
            else:
                print("")
        else:
            print("")


    else:
        print("")
def trivial_persuite():
    fo = input("Who is the first compeditor?")
    ft = input("What is the second compeditor?")
    def question(person , question , answer, points, turn):
        print("The next question is for ")
        print (person + ".")
        print (question)
        fq = input()
        if (fq == answer):
            if (Finchy == "y"):
                Alec.led(0,255,0)
            print("RIGHT! One point for ")
            print (person)
            WASD(points)
            WASD("d")
            WASD("d")    
            WASD(points)
            WASD(turn)
            WASD("wwww")
            WASD(turn)
            if (Finchy == "y"):
                Alec.led(255,255,255)
        else:
            if (turn == "a"):
                turn = "d"
            else:
                turn = "a"
            if (Finchy == "y"):
                Alec.led(0,0,255)
            print("WRONG!")
            WASD(turn)
            WASD("wwww")
            WASD(turn)
            WASD(turn)
            WASD(turn)
            if (Finchy == "y"):
                Alec.led(255,255,255)
            print ("The answer is " + answer)
    WASD("d")
    turtle.ht()
    WASD("wwww")
    WASD("w")
    WASD("c")
    WASD("a")
    WASD("wwww")
    WASD("a")
    WASD("a")
    WASD("wwww")
    WASD("wwww")
    WASD("ss")
    WASD("d")
    print("Welcome to trivial persute!")
    
    question(ft , "2+2" , "4", "ww", "a")
    question(fo , "9*3" , "27", "ww" , "d")
    question(ft , "10*10" , "100", "w", "a")       
    question(fo , "3**2" , "9", "www", "d")
    question(ft , "3x**2 = 27, what is x?" , "3", "www", "a")
    question(fo , "25ab/2b**3 * 4b/5a" , "10/b", "wwww", "d")
    question(ft , "sqrt(-1) sqrt(64)  3.141592653589793238462643383279502... ' and it was good.'" , "i ate pie and it was good." , "wwww" , "a")
    
def hot_and_cold():

    def random(x):
        xold = x
        x = x - 500
        x = x + 2000
        x = x - xold
        while x >= 300:
            x = x - (xold / 20)
        while x <= (0-300):
            x = x + (xold / 20)
    # print the coordinates of the cheese
        print(x)
        return x
    def xcor():
        return turtle.xcor()
    def ycor():
        return turtle.ycor()
    def WASD(W):
        if (W == "w"):
            far = input("how many units?")
            far = float(far)
            turtle.forward(far)
        elif (W == "a"):
            turtle.left(90)
        elif (W == "d"):
                turtle.right(90)
        elif (W == "bgc"):
            color = input("What color?")
            turtle.bgcolor(color)
        else:
            print ("invalid move")
    cheese = False
    #Put the seed for the cheese coordinate generator here. (Under 5000) Multable of 10 => easy Even # => medium, Odd # => Hard
    #
    cheese_corx = 2000
    cheese_cory = 1000
    #
    #
    print("Welcome to hot & cold!")
    print("You are a turtle.")
    print("")
    print("")
    print("")
    print("Find the cheese.")
    turtle.reset()
    turtle.shape("turtle")
    cheese_corx = random(cheese_corx)
    cheese_cory = random(cheese_cory)
    time = 1
    while(True):
        WASD(input())
        if(True):
            x = xcor()
            y = ycor()
            xdiff = abs(cheese_corx - x)
            ydiff = abs(cheese_cory - y)
            length = abs(xdiff - ydiff)
            if (xdiff <= 10 and ydiff <= 10 and xdiff > 0 and ydiff > 0):
                print("Your burning hot!")
            elif (x == cheese_corx and y == cheese_cory):
                print("You found the cheese!")
                break
            elif (xdiff < 200 and ydiff < 200):
                print("You are cool")
            elif (xdiff < 150 and ydiff <150):
                print("you're slightly warm!")
            elif (xdiff < 100 and ydiff <100):
                print("your warm!")
            
            else:
                print("your cold")
        else:
            print("Logic error")
        if(time > 1):
            if (length < length_old):
                print("Your warmer!")
            elif(length > length_old):
                print("your colder...")
            else:
                print("You are not any closer or farther from the cheese.")
        else:
            print("")
        length_old = length
        x = str(x)
        y = str(y)
        print("Your coordinates are (" + x + "," + y + ")")
        time = time + 1
    input()




def trivia_persuite():
    fo = input("Who is the first compeditor?")
    ft = input("What is the second compeditor?")
    print("Welcome to trivial persute!")
    print("The First question is for " + fo + ".")
    fq = input("What is 1 + 1")
    if (fq == "2"):
        print("RIGHT! One point for " + fo + "!")
    else:
        print("WRONG!")
    def question(person , question , answer):
        print("The next question is for ")
        print (person + ".")
        print (question)
        fq = input()
        if (fq == answer): 
            print("RIGHT! One point for ")
            print (person)
        else:
            print("WRONG!")
    question(ft , "2+2" , "4")
    question(fo , "9*3" , "27")
    question(ft , "10*10" , "100")       
    question(fo , "3x = 27, what is x?" , "9")
    question(ft , "3x**2 = 81, what is x?" , "3")
    question(fo , "25ab/2b**3 * 4b/5a" , "10/b")

def lines_n_arrows():
    def bad_guy():
        WASD("fill")
        turtle.forward(100)
        turtle.right(25)
        turtle.forward(50)
        turtle.left(115)
        turtle.forward(43)
        turtle.left(115)
        turtle.forward(50)
        turtle.right(25)
        turtle.forward(100)
        turtle.left(180)
        WASD("efill")
        turtle.setpos(x , y)
        print(x + "," + y)
    print("Welcome to my first game! The object is to survive all 10 levels. You cannot leave the screen, backtrack, or cross your trail, even if the computer takes over. Everytime you go out of the screen you subrtract 1 from a strt of 10 points, and the other 2 violations are 5 points. If get negative it is a game over. The controls are W,A,S,and D. Don't give up, you'll get the hang of it! Good Luck!")
    turnlist = [10,9,8,7,6,5,4,3,2,1,0]
    WASD("a")
    points = 10
    already_doneX = []
    already_doneY = []
    for i in turnlist:
        turns_taken = 0
        i = str(i)
        print(i + " moves")
        i = int(i)
        while(turns_taken <= i):
            W = input()
            WASD(W)
            turns_taken = turns_taken + 1
            x = turtle.xcor()
            y = turtle.ycor()
            print(x + "," + y)
            if (x in already_doneX) and (y in already_doneY) and (W != "a") and (W != "d"):
                points = points - 1
                if points <= 0:
                    print("Game over")
                    break
            already_doneX.append(x)
            already_doneY.append(y)
        bad_guy()
        
    
    print("Congrats! You beat the game!")
    print("Credits: programer - Alec")
    print(" creator - Alec")
    print(" everything else - Alec")

def hangman(HINTS):
    score = 0
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
            print("You do not have any hints left")
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
                HINTS = str(HINTS)
                print("You have " + HINTS + "hints!")
                HINTS = int(HINTS)
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
            HINTS = str(HINTS)
            print("you have " + HINTS + " hints left!")
            HINTS = int(HINTS)
            score = int(score)
            if playAgain():
                missedLetters = ''
                correctLetters = ''
                gameIsDone = False
                secretWord = getRandomWord(words)
            else:
                break



 #Code starts here
gameList = ["hangman", "hot_and_cold", "trivial_persuite", "lines_n_arrows"]
print("Hello")
input()

while True:
    WASD("r")
    print("Which game would you like to play?")
    gameIndex = input(" Type 1 for hangman \n Type 2 for hot and cold \n Type 3 for trivia popcorn \n Type 4 for lines and arrows \n Type 0 to quit")
    gameIndex = int(gameIndex)
    gameIndex = gameIndex -1
    ranges = list(range(len(gameList)))
    if gameIndex in ranges:
        game = gameList[gameIndex]
        if game == gameList[0]: #figure out which game the user wants to play and call the appropriate function
            hangmanTimes = hangmanTimes + 1
            hangman(HINTS)
        elif game == gameList[1]:
            hot_and_cold()
            hot_and_coldTimes = hot_and_coldTimes + 1
        elif game == gameList[2]:
            trivial_persuite()
            trivial_persuiteTimes = trivial_persuiteTimes + 1
        elif game == gameList[3]:
            lines_n_arrows()
            lines_n_arrowsTimes = lines_n_arrowsTimes + 1
        else:
            print("logic error")
    elif (gameIndex == -1):
        break
    
    else:
        print("that is not a valid game")
