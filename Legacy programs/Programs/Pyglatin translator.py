pyg = ("ay")
print ("Welcome to the English to Pig Latin translator!")
original = input("What would you like to translate?")
print (original + ", good word.")
print ("Beginning translating sequence with your word, Beep Beep.")
if not original.isalpha() or len(original) <= 1:  
    print ("ERROR! ERROR! Are you trying to be smart? You aren't, that makes no sense in piglatin!") 
    print ("GAME OVER CLOSE THE PROGRAM AND START AGAIN")
   
else:   
    print                                                                    ("So, while you are here wating for the transaction to finish, why don't you tell a little about yourself?")
    name = input("What is your name?")
    input("What is your weapon")
    input("What is the " + name + " 's quest?")
    print ("A nobal quest, worthy of the most valiant knight.")
    word = original
    pyg = 'ay'
    word = original.lower()
    first = word[0]
    half = original[1:]
    if (first == "a" or first == "e" or first == "i" or first == "o" or first == "u"):
        print ("Your word is " + half + pyg + " in pyglatin!")
    else:
        print ("your word is " + half + first + pyg + " in pyglatin!")
