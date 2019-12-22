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



# Instantiate the Finch object and connect to Finch
tweety = Finch()

# Get the Z-Axis acceleration
zAccel = tweety.acceleration()[2]

# Do the following while the Finch is not upside down (z value in gees above -0.7)

while zAccel > -0.7:
    
    left_obstacle, right_obstacle = tweety.obstacle()
    # If there's an obstacle on the left, back up and arc
    if left_obstacle:
        tweety.led(255,0,0)
        
        tweety.wheels(-1.0, -0.3)
        
        sleep(1.0)
        print("Uh oh! looks like we've found an obstical!")
        
    # Else just go straight

    # Back up and arc in the opposite direction if there's something on the right
    elif right_obstacle:
        tweety.led(255,255,0)
        
        tweety.wheels(-1.0, -0.3)
        
        sleep(1.0)
        print("Uh oh! looks like we've found an obstical!")
    # Else just go straight
    else:
        tweety.wheels(1.0, 1.0)
        tweety.led(0,255,0)
    # Keep reading in the Z acceleration
    zAccel = tweety.acceleration()[2]
print("upside down, now shutting down")   
tweety.close()
