print ("This is the medianorator! Use it to find the mean of any ten, five or two number set!")
numo = input("What is the first number?")
numtw = input("What is the second number?")
ques = input("Type 2 if you have two numbers, 10 for ten, and 5 for five")
 
if (int(ques) == 10):
    numth = input("What is the third number?")
    numf = input("What is the fourth number?")
    numfi = input("What is the fifth number?")
    nums = input("What is the sixth number?") 
    numse = input("What is the seventh number?")
    nume = input("What is the eighth number?")
    numn = input("What is the ninth number?")
    numte = input("What is the tenth number?")
    add = int(numo + numtw + numth + numf + numfi + nums + numse + nume + numn + numte) / 10
    print (add)
 
elif (int(ques) == 5):
    numth = input("What is the third number?")
    numf = input("What is the fourth number?")
    numfi = input("What is the fifth number?")
    an = int(numo + numtw + numth + numf + numfi) / 5
    print (an)
 
else:
    if (int(ques) == 2):
        ans = int(numo + numtw) / 2
        print (ans)
    else:
        print ("Invalid answer, try again")
