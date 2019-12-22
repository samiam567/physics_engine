import turtle
def load_level_1():
    stop = False
    WASD("r")
    score = 0
    alreadyx = []
    alreadyy = []
    while(True):
        turtle.forward(1)
        stop = WASD(input())
        if stop == "invalid":
            score = score + 1
        if stop == True:
            score = str(score)
            print("Your score is " + score + "!")
            score = int(score)
            break
        x = turtle.xcor()
        y = turtle.ycor()
        for i in range(0,len(alreadyx)):   
            if ((abs(alreadyy[i] - y)< 1)):
                c = i - 1
                if ((abs(alreadyx[i] - alreadyx[c]))< 1):
                    print("GAME OVER!!!!")
        alreadyx.append(x)
        alreadyy.append(y)
        print(alreadyx)
        print(alreadyy)
    
def xcor():
    return turtle.xcor()

def ycor():
    return turtle.ycor()

def level_load(level):
    level_len = len(level)
    for w in range(0, level_len):
        command = level[w]
        WASD(command)
        
def WASD(W):
    if (W == "/n"):
        nothing = (".")
    elif (W == "w input"):
        far = input("how many units?")
        far = float(far)
        turtle.forward(far)
    elif(W == 'w'):
        turtle.forward(20)
    elif (W == 'a'):
        turtle.left(90)
    elif (W == 'd'):
            turtle.right(90)
    elif (W == 'ss'):
        turtle.backward(25)
    elif (W == 'c'):
        turtle.clear()
    elif (W == 'r'):
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
    elif (W == "fill"):
        turtle.begin_fill()
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
    elif (W == "b"):
        return(True)
    elif (W == " "):
        turtle.penup()
        WASD("w")
        turtle.pendown()
    else:
        print("invalid move")
        return ("invalid")


Win = False
turtle.forward(1)
turtle.reset()
level = 1
print("Welcome to the legend of Link")
tricommands = ['a','a','w','d','w','d','w','a','w','d','w','a','w','d','w','a','w','d','w','d','w','a','w','d','w','a','w','d','w','a','w','d','w','d','w','w','w','w','w','w','ww','d','d','w','w','w','a','w','a','w','d','w','d','w','w','w','d','w','d','w','a','w']
level_1 = list('''awwwwwwwwwwwwwwwwwwwwwrdwwwwwwsdwwwwawwwwwwwwwwwwawwwwwwwwwwwddwwwwwwwwww
w
d
w
w
w
w
w
w
w
w
w
w
w
d
w
w
w
w
d
w
w
w
w
w
w
w
w
a
w
w
w
a
w
w
a
w
a
w
d
w
d
w
w
w
w
w
w
w
d
w
w
w
d
w
d
w
a
w
w
d
w
a
w
w
a
w
w
a
w
w
d
w
a
w
w
w
d
w
w
d
w
w
w
w
d
w
a
w
w
w
w
w
a
w
d
d
w
d
w
w
w
w
w
d
w
a
w
w
w
w
a
w
w
a
w
w
w
d
w
w
w
a
w
w
a
w
w
d
w
w
w
a
ww
a
w
w
d
ww
d
w
w
w
w
w
d
d
w
w
w
w
w
a
w
a
w
w
a
w
d
d
w
a
w
w
w
a
w
a
w
a
w
d
w
d
w
ww
d
d
w
ww
ww
a
a
w
s
a
w
d
ww
a
a
w
w
d
d
w
ww
d
w
w
w
w
w
w
w
d
a
a
w
d
w
w
d
w
a
w
a
w
w
w
w
w
w
a
ww
''')


turtle.bgcolor("yellow")
level_load(tricommands)
print("   The cognative challenge")
input("Press enter to start.")
turtle.bgcolor("black")
WASD("r")
turtle.setpos(-500,0)
WASD("c")
turtle.bgcolor("green")
turtle.forward(1000)
while(Win == False):
    level = input("which level?")
    if level == "1":
        load_level_1()
        break
    else:
        print("Sorry, thatis not a level.")
print("shutting down... beep beep")
