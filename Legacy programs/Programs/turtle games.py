import turtle
def WASD(W):
    if (W == "w"):
        turtle.forward(50)
    elif (W == "a"):
        turtle.left(90)
    elif (W == "z"):
        turtle.undo(1)
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
    else:
        print("invalid move")
turtle.forward(1)
while(5 < 10):
    WASD(input())
