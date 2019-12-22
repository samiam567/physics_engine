import turtle
number = "0"
while len(number) != 0:
    number = input("Tell me a number")
    number = float(number)
    turtle.forward(number)
    number = number * 100
    turtle.left(number)
    number = number - 99
    turtle.right(number)
    number = number + number
    number_2 = number / 3
    turtle.goto(number,number_2)
    number = str(number)
