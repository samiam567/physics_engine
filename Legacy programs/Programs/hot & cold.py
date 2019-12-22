import turtle
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


