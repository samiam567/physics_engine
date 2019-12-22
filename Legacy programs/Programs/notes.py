import turtle

from math import*
def sqrtfac(a , b):
    a = int(a)
    b = int(b)
    if (a%b == 0):
        c = sqrt(b)
        d = (a / b)
        d = str(d)
        c = str(c)
        print(c + " * sqrt(" + d + ")")
        return True
    else:
        return False
def dinner():
    time = input("Approximately haw many minutes do you have to prepare dinner?")
    person = input ("List ONE of the people who you want to like today's meal.")
    if (person == "Alec"):
        if (time <= str(30)):
            print ("Breakfast for dinner, PBJ, chili, Grilled Cheese, Egg salad, chickan nachos, and tacos")

        else:
            print ("French dip, Spagetti and Meatballs, Eggplant, Chickan Cutlet, Chinese food w/ Egg drop soup, Hamburgers, Meatloaf, and Steak.")
    elif (person == "Erica"):
        if (time <= str(30)):
            print("Breakfast for dinner, grilled cheese, sandwiches, clam chowder, raviolli, PBJ, tacos, and chickan nachos.")
        else:
            print("Thanksgiving dinner, french dip, spagetti and meatballs, chinese food w/ egg drop soup")

    else: 
        if (person == "Emily"):
            if (time <= str(30)):
                print ("Breaded chickan sandwiches, chickan nachos, raviolli, sandwiches, frozen pizza, tacos, grilled cheese, and egg salad.")
            else:
                print ("Hammburgers, spagetti and meetballs, Chickan Cutlet, Chinese food w/ Egg drop soup, egg plant,  and chickan cudlet.")
        elif (person == "Tracy"):
            if (time <= str(30)):
                print ("anything")
            else:
                print ("anything")
        else:
            #that means the input is daddy
            if (time <= str(30)):
                print ("Leftovers, Sandwiches, Egg salad, ravioli, grilled cheese")
            else:
                print ("anything we have ALL the ingredients for")
def perfect_square(a):
    n = int(a)
    x = int(0)
    while( x < 1000 and(x**2 * sqrt(n) != n)):
        x = (x+1)
        if (x**2 * sqrt(n) == n):
            n = int(n)
            return(True)
        

        else:
            print("The square root of " + a + " is not simplifyable")
            print("If that is even a word...")
def ratfac2(a):
    if (len(a) > 0):
        a = int(a)
        if(sqrtfac(a , 25) == False):
            if (sqrtfac(a , 4) == False):
                if (sqrtfac(a , 9) == False):
                    if (sqrtfac(a , 16) == False):
                        if (sqrtfac(a , 36) == False):
                            a = str(a)
                   #         if (perfect_square(a) == False):
                    #            print("The square root of " + a + " is not simplifyable")
                    #            print("If that is even a word...")
                       #     else:
                            #    print("it is a perfect square")
                        else:
                            print("hi")
                    else:
                        no = 23
                else:
                    no = 23
    				
            else:
                no = 23

        else:
            NO = 23
    else:
        NO = 23

def doorator():
    print ("Do you have trouble finding what to do? Try out the Doorator! Just type in what you want to do, and get cooking!")
    ocat = input("So, will it be video games, or other?")

    if (ocat == ("video games")):
        ocat = input("computer, console, or mobile?")

    elif(ocat == "other"):
        ocat = input("educational, or other?")
    else:
        print("Invalid answer")

    if ocat == ("other"):
        print ("How about Lacrosse, tennis, RC Car, Mini Reo, Lazer Tag, TV, Scooters, Bikes, Play in the snow, Play in Woods, Play on the play ground, a board game, cards, perplexuses, or lift weights?")

    elif ocat == ("educational"):
        print ("What do you think about Codeing, Times Attack, ALEKS, Study, FPS Reaserch, How It's made, educational TV, Rokenbok, or The Science lab?")

    elif ocat == ("computer"):
        print ("How about Roblox, Minecraft, Times Attack, Pokemon Tower Defence, Codeing, or a silly powerpoint?")    

    elif ocat == ("console"):
        print("How about a go with Twilight Princess, Windwaker, Super Smash Bros., ocorina of time, Four swords, or skyword sword?")
    elif ocat == ("mobile"):
        print("Whould you be interested in Bad Piggies, Bike Race, Angry birds, Split Pic, Sonic all stars raceing, listening to music, Cars Spy thing, 3d rollercoster rush, or Physics free full version?")
    else:
        print("invalid answer")
    
def robinson_cruoe_journal():
    print("ROBINSON CRUSOE JOURNAL")
    print("By: Alec Pannunzio")
    print("The day I learned about Caesar's English")
    print("It was the first day of school, and I was excited to meet my new teachers. First I went to science. I hadn’t heard much about my science teacher, Mrs. O'Neal, but I had heard that she liked rocks. The thought of learning about rocks the whole year wasn't very appealing at the time, so I was not looking forward to that. The 8th grade wing was not totally alien to me, as I had gone to research near it the year before. When I got to class, the first thing I noticed was that it has the type of desks the high school uses. When the bell rang, we said the pledge of allegiance. (It is really awkward when I have to sneeze during it) When class started, Mrs. O’Neal talked about the rules and what we would be learning about. I figured out that we would not be spending the whole year learning about rock, but about half. I also figured out that we would be doing some physics. When I heard this I thought about a certain karate class that involved people shouting out random physics facts while doing push-us. After science I started on my way to humanities and boy, was I in for a surprise.  I had alacrity for it, but my mood was a little somber. When I got to the classroom, my first impression was the prodigious amount of language arts posters.  I also noticed two grid like structures made of green and blue tape one the board, and a shelf of dictionaries. The last thing I noticed was the bounteous amount of vocabulary. I had heard good and bad things about my new humanities teacher. From my sister, I heard that she gave lots of homework. It was proof enough to me, the amount of time my sister had spent in her room doing homework. My teacher Mrs. Krupowicks, however contradicted her by saying we would have little homework throughout the year. She also talked about other things that were over my head such as S.E.E. format and hamburger style paragraphs. Then, it came. Sneaking up to me and exploding like a bomb, the one thing that strikes terror in the hearts of all young teens… Caesar’s English. The grids, the vocabulary, the dictionaries, it all made sense! It would be a long year.")
    print("To Find Out more, Go To www.CEterror.org")

def boston_massacre_nwscast():
    print("*Breaking News* British Fire on Colonists")
    print("")
    print("On Friday the 2d instant, a fight took place between colonist protesters and Captain Preston and his men at Boston Massachusetts. “A quarrel arose between some soldiers of the 29th and the rope makers’ journeymen and apprentices, which was carried to that length, as to become dangerous to the lives of each party , many of them being much wounded,” a colonist states.")
    print("")
    print("After being questioned on the matter, Captain Preston recalls, “They immediately surrounded the sentential post there, and with clubs and other weapons threatened to execute their vengeance on him.' Captain Preston and his soldiers are currently in jail.") 
def documents():
    book = input("What document?")
    book = book.lower()
    
    if (book == "robinson crusoe journal"):
        robinson_cruoe_journal()
    elif (book == "boston massacre newsfeed"):
        boston_massacre_nwscast()
    else:
        print("Invalid document")
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
                    return(3)
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
def lines_n_arrows():
    turtle.left(90)
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
    print("Welcome to my first game! The object is to survive all 10 levels. You cannot leave the screen, backtrack, or cross your trail, even if the computer takes over. Everytime you go out of the screen you subrtract 1 from a strt of 10 points, and the other 2 violations are 5 points. If get negative it is a game over. The controls are W,A,S,and D. Also, you can only use a command other than w,a,s,d,ss,ww,w2,s,www,or wwww once per game. Don't give up, you'll get the hang of it! Good Luck!")
    bad_guy()
    print("10 moves")
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    bad_guy()
    print("level up! 9 moves")
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    bad_guy()
    print("level up! 8 moves")
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    bad_guy()
    print("level up! 7 moves")
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    bad_guy()
    print("Level up! 6 moves")
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    bad_guy()
    print("level up! 5 moves")
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    bad_guy()
    print("level up! 4 moves")
    a = input()
    WASD(a)
    a = input() 
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    bad_guy()
    print("level up! 3 moves")
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    bad_guy()
    print("level up! 2 moves")
    a = input()
    WASD(a)
    a = input()
    WASD(a)
    bad_guy()
    print("level up! 1 move")
    a = input()
    WASD(a)
    a = input()
    print("Level up! 0 moves! (hope for the best) :)")
    a = input()
    print("")
    WASD("wwww")
    WASD("wwww")
    WASD("wwww")
    WASD("wwww")
    print("Congrats! You beat the game!")
    print("Credits: programer - Alec")
    print(" creator - Alec")
    print ("insperation Moastly Alec but partly Davis")
    print(" everything else - Alec")






            
def trivia(fo , ft):
    def question(person , question , answer, points, turn):
        print("The next question is for ")
        print (person + ".")
        print (question)
        fq = input()
        if (fq == answer): 
            print("RIGHT! One point for ")
            print (person)
            WASD(points)
            WASD("d")
            WASD("d")    
            WASD(points)
            WASD(turn)
            WASD("wwww")
            WASD(turn)
        else:
            if (turn == "a"):
                turn = "d"
            else:
                turn = "a"
            print("WRONG!")
            WASD(turn)
            WASD("wwww")
            WASD(turn)
            WASD(turn)
            WASD(turn)
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

def pyg():
    start_over = True
    while (start_over == True):
        pyg = ("ay")
        print ("Welcome to the English to Pig Latin translator!")
        original = input("What would you like to translate?")
        print (original + ", good word.")
        print ("Beginning translating sequence with your word, Beep Beep.")
        if not original.isalpha() or len(original) <= 1:  
            print ("ERROR! ERROR! Are you trying to be smart? You aren't, " + original + " makes no sense in piglatin!") 
            print ("GAME OVER START AGAIN")
            start_over = True
       
        else:   
            word = original
            pyg = 'ay'
            word = original.lower()
            first = word[0]
            half = original[1:]
            if (first == "a" or first == "e" or first == "i" or first == "o" or first == "u"):
                print ("Your word is " + half + pyg + " in pyglatin!")
            else:
                print ("your word is " + half + first + pyg + " in pyglatin!")
            start_over = False
def pass_gas(se):
    secure = input("What is the security code?")
    if (secure.endswith(" "))and (secure.startswith("*"))and (se != False):
        access_denied = False
        se = True
        turtle.shape("turtle")
        WASD("a")
        WASD("st")
        wer = 0
        while (wer < 50):
            WASD("a")
            wer = (wer+1)
        WASD("ht")
        while(se == True):
            se = (mathr())
    elif (se == False):
        print("Terminating sequence")
        return (False)
        
    else:
        print("Access denied")
        se = True
        if (secure.endswith(" "))and (secure.startswith("*"))and (se != False):
            access_denied = False
            WASD("a")
            WASD("st")
            input()
            WASD("ht")
            while(se == True):
                se = (mathr())
        elif (se == True):
            print("Terminating sequence")
            print("Shutting down")
        else:
            print("Access denied")
            se = True
            if (secure.endswith(" "))and (secure.startswith("*"))and (se != False):
                access_denied = False
                WASD("a")
                WASD("st")
                input()
                WASD("ht")
                while(se == True):
                    se = (mathr())
            elif (se == False):
                print("Terminating sequence")
                print("Shutting down")
            else:
                access_denied = True
                while (access_denied == True):
                    print("Access denied")
                    input("What is the security code?")
def mathr():
    if (True):
        WASD("r")
        WASD("ht")
       # to = input("if your equation is in ax**2 + bx +c, type 1. If it is in d(x + a)**2 + b = c, type 2. If you want to use the normal calculator, do not type in anything.")
        a = input()
        ot = input()
        b = input()
        ot = ot.lower()
        if (ot == "1"):
            a = (input("What is the A value?"))
            b = ((input("What is the B value?")))
            c = (input("What is the C Value?"))
            if (type(a) or type(b) or type(c) != float) and (len(a) and len(b) > 0):
                a = float(a)
                b = float(b)
                c = float(c)
                wer = 0
                WASD("st")
                while (wer < 10):
                    WASD("a")
                    wer = (wer+1)
                WASD("ht")
                dis = str(b**2 - (4 * a * c))
                print ("The Discriminent is " + dis + ".")
                v = ((0 - b) / (2 * a))
                d = v
                ve = ((a * (v**2)) + (b * d) + c)
                print ("The axis of symmetry is:")
                print  ("x = ")
                print (v)
                print ("The vertex is ")
                print (v)
                print (",")
                print (ve)
                anso = (0 - b)
                ansoer = (b**2 - (4 * a * c))
                if (ansoer < 0):
                    print ("The answer is no solution")
                else:
                    anso = (anso + sqrt(ansoer))
                    anso = (anso / (2 * a))
                    anst = (0 - b)
                    anst = (anst - sqrt(b**2 - 4 * a * c))
                    anst = (anst / (2 * a))
                    
                    print("The answers are:")
                    print (anso)
                    print ("and")
                    print (anst)
                    anso = (0 - b)
                    ansoer = (b**2 - (4 * a * c))
                    ansot = (0 - anso)
                    ansto = (0 - anst)
#                    print ("The factored form is:")
 #                   print ("(x + ")
  #                  print (ansot)
   #                 print (")")
    #                print ("(x + ")
     #               print(ansto)
      #              print (")")
                qrs = input("do you want to plug in a point?")
                if (qrs == "y"):
                    v = input("What x value?")
                    WASD("ht")
                    v = float(v)
                    ve = ((a * (v**2)) + (b * v) + c)
                    print (ve)
                    ve = (v**2)
                    ve = (ve * a)
                    xe = (b * v)
                    ve = (ve + xe)
                    ve = (ve + c)
                    print(ve)
                    WASD("st")
                    WASD("r")
                    turtle.setpos(v , ve)
                    WASD("c")
                    vor = input("another?")
         
                    if (vor == "y"):
                        v = input("What x value?")  
                        v = float(v)
                        ve = ((a * (v**2)) + (b * v) + c)
                        print(ve)
                        WASD("st")
                        turtle.pendown()
                        turtle.setpos(v , ve)
                        WASD("ht")
                        input()
                    else:
                        print("OK")
                else:
                    print ("OK")
            
    
            else:
                s = 1
        elif (ot == "How are you?"):
            print("I am good.")
        elif (ot == "lines n arrows"):
            WASD("st")
            wer = 0
            while (wer < 5):
                WASD("a")
                wer = (wer+1)
            WASD("r")
            lines_n_arrows()
            WASD("ht")
            WASD("r")
        elif(ot == "ps"):
            print(perfect_square(a))
        elif (ot == "h. asymptote"):
            n = input("What is the degree of the numerator?")
            m = input("What is the degree of the denomonator?")
            if (n < m):
                print("The horizontal asymptote is at the x-axis.")
            elif (n == m):
                a = input("What is a?")
                b = input("What is b?")
                print ("The horizontal assymtote is")
                print (a / b)
            else:
                print("There is no horizontal assymtote")
                
                

       
        
        elif(ot == "trivia"):
            fo = input("Who is the first compeditor?")
            ft = input("Who is the second compeditor?")
            trivia(fo , ft)
            WASD("r")
            WASD("ht")
        elif (ot == "you are a"):
            print("takes one to know one " + person + ".")
        elif (ot == "sq"):
            a = float(a)
            print(a**2)
        elif (ot == "dinner"):
            dinner()
        elif (ot == "sleep"):
            WASD("sleep")

        elif(ot == "2"):
            a = input("What is the A value?")
            b = (input("What is the B value?"))
            c = (input("What is the C Value?"))
            d = input("What is the d value?")
            a = int(a)
            b = int(b)
            c = int(c)
            c = (c - b)
            c = sqrt(c)
            c = (c / d)
            anso = (0 - a)
            anso = (anso + c)
    
            anst = (0 - c)
            anst = (anst -a)
            print ("The answers are:")
            print (anso)
            print ("and")
            print (anst)
            print ("and")
            print (anst)
        elif (ot == "s.i. linear"):
            m = float(input("What is m?"))
            b = float(input("Whar is b?"))
            again = input("Wanna plug in a point")
            if (again == "y"):
                # y = mx + b
                x = float(input("What to plug in for x?"))
                y = (m*x)
                y = (y + b)
                WASD("st")
                WASD("r")
                turtle.setpos(x , y)
                WASD("c")
                print (y)
                again = input("another?")
                if (again == "y"):
                    x = float(input("What to plug in for x?"))
                    y = (m*x)
                    y = (y + b)
                    print (y)
                    WASD("st")
                    turtle.pendown()
                    turtle.setpos(x , y)
                    
                        
                    again = input("another?")
                    if (again == "y"):
                        x = float(input("What to plug in for x?"))
                        y = (m*x)
                        y = (y + b)
                        print (y)
                        WASD("st")
                        turtle.pendown()
                        turtle.setpos(x , y)
                        again = input("another?")
                        if (again == "y"):
                            x = float(input("What to plug in for x?"))
                            y = (m*x)
                            y = (y + b)
                            print (y)
                            WASD("st")
                            turtle.pendown()
                            turtle.setpos(x , y)
                            WASD("ht")
                            input()
        elif (ot == "midpoint"):
            xo = int(input("What is x1?"))
            yo = int(input("What is y1?"))
            xt = int(input("What is x2?"))
            yt = int(input("What is y2?"))


            anso =(xo + xt)
            anso = (anso / 2)
    
            anst = float(yo + yt)
            anst = (anst / 2)
            print ("The answer is ")
            print (anso)
            print (",")
            print (anst)
        
        elif (ot == "print"):
            prin = input()
            pri = input()
            pr = input()
            prin = float(prin)
            pri = float(pri)
            pr = float(pr)
            print (prin + pri + pr)
        elif (ot == "what to do"):
            doorator()
        elif (ot == "type"):
            inp = input()
            print (type(inp))
        elif (ot == "pyg"):
            pyg()
        elif (ot == "sqrtfac"):
            ratfac2(a)
        elif (ot ==  "ratfac"):
            ratfac2(a)
        elif (ot == "isprime"):
            if (len(a) > 0):
                
                 n = a
                 n = int(n)
                 b = (n + 1)
                 for n in range(n, b):
                     for x in range(2, n):
                         n = int(n)
                         if n % x == 0:
                             x = int(x)
                             y = (n//x)
                             n = str(n)
                             x = str(x)
                             y = str(y)
                             print(n + ' equals ' + x + '*'+ y)
                             break
                     else:
                         # loop fell through without finding a factor
                         print(n, 'is a prime number')
            
        elif (ot == "lines n arrows"):
            lines_n_arrows()
        elif (ot == "document"):
            documents()
        elif (ot == "tp"):
            di = int(input("what is the diagonal?"))
            point = int(input("What is the pointy edge?"))
            bt = int(input("What is the leg of the triangle adjasent to the pointy edge?"))
            st = int(input("What is the other leg of the triangle?"))
            ans = (st * bt)
            answ = (di * point)
            answe = (bt * point)
            answer = (st * point)
            anso = (ans + answ + answe + answer)
            print (anso)

       
        elif (ot == ".1"):
            a = (input("What is the A value?"))
            b = ((input("What is the B value?")))
            c = (input("What is the C Value?"))
            if (type(a) or type(b) or type(c) != float) and (len(a) and len(b) > 0):
                a = float(a)
                b = float(b)
                c = float(c)
                dis = str(b**2 - (4 * a * c))
                print ("The Discriminent is " + dis + ".")
                v = ((0 - b) / (2 * a))
                d = v
                ve = ((a * (v**2)) + (b * d) + c)
                print ("The axis of symmetry is:")
                print  ("x = ")
                print (v)
                print ("The vertex is ")
                print (v)
                print (",")
                print (ve)
                anso = (0 - b)
                ansoer = (b**2 - (4 * a * c))
                if (ansoer < 0):
                    print ("The answer is no solution")
                else:
                    anso = (anso + sqrt(ansoer))
                    anso = (anso / (2 * a))
                    anst = (0 - b)
                    anst = (anst - sqrt(b**2 - 4 * a * c))
                    anst = (anst / (2 * a))
                    
                    print("The answers are:")
                    print (anso)
                    print ("and")
                    print (anst)
                    anso = (0 - b)
                    ansoer = (b**2 - (4 * a * c))
                    ansot = (0 - anso)
                    ansto = (0 - anst)
#                    print ("The factored form is:")
 #                   print ("(x + ")
  #                  print (ansot)
   #                 print (")")
    #                print ("(x + ")
     #               print(ansto)
      #              print (")")
                qrs = input("do you want to plug in a point?")
                if (qrs == "y"):
                    v = input("What x value?")
                    v = float(v)
                    ve = ((a * (v**2)) + (b * v) + c)
                    print (ve)
                    ve = (v**2)
                    ve = (ve * a)
                    xe = (b * v)
                    ve = (ve + xe)
                    ve = (ve + c)
                    print(ve)
                    vor = input("another?")
         
                    if (vor == "y"):
                        v = input("What x value?")  
                        v = float(v)
                        ve = ((a * (v**2)) + (b * v) + c)
                        print(ve)
                    else:
                        print("OK")
                else:
                    print ("OK")
            
    
            else:
                s = 1
    
        elif (ot == "+"):
      #      a = (input("What is the 1st #?"))
       #     b = (input("What is the second #?"))
            if (type(a) or type(b) != float) and (len(a) and len(b) > 0):
                a = int(a)
                b = int(b)
                ans = (a + b)
                print (ans)
            else:
                print ("invalid #")
        elif (ot == "thats wrong"):
            print("No way! I am always right!")
        elif (ot == "i like pi"):
            
            print("Really? so do I!")
        elif (ot == "no way!"):
            print("yes way!")
        elif(ot == "sin"):
            a = float(a)
            print(sin(a))
        elif(ot == "tan"):
            a = float(a)
            print(tan(a))
        elif(ot == "cos"):
            a = float(a)
            print(cos(a))
        elif (ot == "hi"):
            name = print("Hello, " + person + ", i'm Alcal.")
            feeling = input("How are you?")
            feeling = feeling.lower()
            if (feeling == "good"):
                print("Happy to hear that, " + person + ".")
            elif (feeling == "not so good"):
                print("I hope you feel better soon.")
            else:
                print("I don't understand...")
        elif (ot == ".+"):
    #        a = (input("What is the 1st #?"))
   #         b = (input("What is the second #?"))
            if (type(a) or type(b) != float) and (len(a) and len(b) > 0):
                a = float(a)
                b = float(b)
                print (a + b)
        
        elif (ot == "*"):
        #    a = (input("What is the 1st #?"))
         #   b = (input("What is the second #?"))
            if (len(a) and len(b) > 0):
                a = int(a)
                b = int(b)
                print (a * b)
            else:
                print("invalid #")
        elif (ot == "turtle"):
            q = 1
            WASD("st")
            turtle.forward(1)
            while(q == 1):
                WASD(input())
                q = WASD(input())
            
            WASD("r")
            WASD("ht")
                
        #elif (ot == "*"):
   #         a = (input("What is the 1st #?"))
    #        b = (input("What is the second #?"))
         #   a = float(a)
          #  b = float(b)
           # print (a * b)
            
        elif (ot == "shut down"):
            return(False)
       
        elif (ot == "/"):
     #       a = (input("What is the 1st #?"))
      #      b = (input("What is the second #?"))
            if (type(a) or type(b) != float) and (len(a) and len(b) > 0):
                a = int(a)
                b = int(b)
                print (a / b)
        elif (ot == "./"):
       #     a = (input("What is the 1st #?"))
        #    b = (input("What is the second #?"))
            a = float(a)
            b = float(b)
            print (a / b)
        elif (ot == "factorial"):
        #    a = (input("What factorial?"))
            if (type(a) or type(b) != float) and (len(a) and len(b) > 0):
                a = int(a)
                print (factorial(a))
            else:
                print("invalid #")
        elif (ot == "porportion"):
            print ("This is the porportion solver #1. Use it only if the second numerator is the only x. (The equasion must look like this: #/# = x/#)")
            num = input("what is the first numerator?")
            deno = input("What is the first denomonator?")
            dent = input("What is the 2nd denomonator?")
            numb = (int(num) * int(dent))
            ans = (int(numb) / int(deno))

            print ("x = " + str(ans))
    
        elif (ot == "-"):
#            a = (input("What is the 1st #?"))
 #           b = (input("What is the second #?"))
            if (type(a) or type(b) != float) and (len(a) and len(b) > 0):
                a = int(a)
                b = int(b)
                print (a - b)
        elif (ot == ".-"):
  #          a = (input("What is the 1st #?"))
   #         b = (input("What is the second #?"))
            a = float(a)
            b = float(b)
            print (a - b)
        elif (ot == "sqrt"):
    #        a = input("What do you want to square root?")
            if (type(a) != "float"):
                a = int(a)
                print (sqrt(a))
            else:
                print("invalad #")
        elif (ot == ".sqrt"):
     #       a = input("What do you want to square root?")
            a = float(a)
            print (sqrt(a))
        elif (ot == "**"):
      #      a = input("What is the base?")
       #     b = input("What is the exponent?")
            if (type(a) or type(b) != float) and (len(a) and len(b) > 0):
                a = int(a)
                b = int(b)
                print (a**b)
        elif (ot == "who am i?"):
            print("Why, you are " + person + " of course!")
        elif (ot == ".**"):
        #    a = input("What is the base?")
         #   b = input("What is the exponent?")
            a = float(a)
            b = float(b)
            print (a**b)
        elif (ot == "pledge"):
            print ("I plege alligence")
            print ("to the flag")
            print ("of the United States of America")
            print ("and to the republic")
            print ("of which it stands")
            print ("one nation")
            print ("Under God")
            print ("Indivisible")
            print ("With liberty and justice for all.")
        elif (ot == "document1"):
            print ("SubotomicOffice Character")
#            print ("Loading...")
            doc1 = input()
            print("document1 is " + doc1)
            ques = input("Woud you like to print that?")
            if (ques == "y"):
                print (doc1)
            else:
                print("OK")

        elif (ot == "document2"):
            print ("SubotomicOffice Character")
      #      print ("Loading...")
            doc2 = input()
            print("document2 is " + doc2)
            ques = input("Woud you like to print that?")
            if (ques == "y"):
                print (doc1)
            else:
                print("OK")
        elif (ot == "document3"):
            print ("SubotomicOffice Character")
        #    print ("Loading...")
            doc3 = input()
            print("document3 is " + doc3)
            ques = input("Woud you like to print that?")
            if (ques == "y"):
                print (doc3)
            else:
                print("OK")
        elif (ot == "dir math"):
            print (dir(math))
        elif (ot == "dir turtle"):
            print (dir(turtle))
        elif (ot == "distance"):
            xo = int(input("What is x1?"))
            yo = int(input("What is y1?"))
            xt = int(input("What is x2?"))
            yt = int(input("What is y2?"))
            ans = (xt - xo)
            ans = (ans**2)
            anst = (yt - yo)
            anst = (anst**2)
            anso = (ans + anst)
            ansto = (sqrt(anso))
            print (ansto)
            print ("or")
            anso = str(anso)
            print("the suare root of " + anso)
 #       elif (ot == "sin"):
  #          print sin(a)
        elif (ot == "slope"):
            xo = float(input("What is x1?"))
            yo = float(input("What is y1?"))
            xt = float(input("What is x2?"))
            yt = float(input("What is y2?"))
            slope = (yt - yo)
            slop = (xt - xo)
            ans = (slope / slop)
            print (ans)
            
 

        
        

        
        else:
            print("invalid answer for the type of equasion.")
    return (True)
WASD("bcb")
WASD("w")
WASD("r")


see = True
access_denied = True

person = input("What is ur name?")
print("Greatings, " + person)
WASD("ht")
WASD("bgw")
finch = input("do you currently have a finch connected?")
if (finch != "y"):
    while (see == True):
       see = pass_gas(True)
    WASD("shutdown script")
else:

    # A simple program that wanders and avoids obstacles

    from finch import Finch
    from time import sleep
    import notes

    #Main function for the music player example program"""

    #Initialize the finch
    finch = Finch()


    

    songList = ['E5  C D E C D E F  D E F D E F G  A  GE F C D E G E D C ',
                    'D D5 A4 G G5 A4 F5# A4 D D5 A4 G G5 A4 F5# A4 '
                    'E D5 A4 G G5 A4 F5# A4 E D5 A4 G G5 A4 F5# A4 '
                    'G D5 A4 G G5 A4 F5# A4 G D5 A4 G G5 A4 F5# A4 '
                    'D D5 A4 G G5 A4 F5# A4 D D5 A4 G G5 A4 F5# A4 ',
                    'E5 E  E   C E  G     ','C E5 G']
    timeList = [0.18,0.1,0.1]

    song = 1

        #get which song
    song = 3

    if song >=1 and song <= 3:
    
        Finch.led(255,0,0)
        notes.sing(finch, songList[song -1],timeList[song-1])
    else:
        print('Exiting...')




   

