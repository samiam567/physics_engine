
from finch import Finch
from time import sleep

#Main function for the race track driver example program."""
    
#Initialize the finch
finch = Finch()
left_wheel = 0
right_wheel = 0
led_red = 50
led_green = 50
led_blue = 50
while True:
    finchy = input("")
    finchy = finchy.lower()
    if finchy == "w":
        if right_wheel + left_wheel <= 1:
            right_wheel = right_wheel + 0.2
            left_wheel = left_wheel + 0.2
    elif finchy == "ss":
        right_wheel = 0
        left_wheel = 0
        break
    elif finchy == "d":
        if right_wheel + left_wheel <= 1:
            left_wheel = left_wheel + 0.2
        else:
            right_wheel = right_wheel - 0.2

    elif finchy == "a":
        if right_wheel + left_wheel <= 1:
            right_wheel = right_wheel + 0.2
        else:
            left_wheel = left_wheel - 0.2
    elif finchy ==  "s":
            left_wheel = 0
            right_wheel = 0
    elif finchy == "red":
        led_red = input("How much?")
    elif finchy == "green":
        led_green = input("How much?")
    elif finchy == "blue":
        led_blue = input("How much?")
    
            
    elif finchy == "temp":
        print(str(finch.temperature()) + " degrees celcius")
            
    finch.wheels(left_wheel,right_wheel)
    finch.led(led_red, led_green, led_blue)
    

finch.close()
