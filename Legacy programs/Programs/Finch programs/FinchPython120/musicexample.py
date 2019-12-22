#User instructions
""" Dear user,
 to correctly run the code, place the finch head toward the bed on my chair.
 Press "f5" on the keyboard, and a window caled "python 3.3 shell" should come up.
 after that, it should ask you what you want, music player or alarm. If you want the alarm,
 type "a". If you want the music player, type "m". After this, the instructions shown to you
 when the code runs should be sufficiant.

 If at first you dont succead, f5 f5 again. :)
 
"""
# ---------Code starts Here-------------#
"""
Note:
Make sure the finch is firmly connected before you run the code
Plays a list of songs. Input a number to choose.
1. Michigan fight song
2. Intro to Sweet Child of Mine
3. Mario Theme Song
Also plays an alarm. (beta version)

Uses notes.py, an add-on library that simplifies buzzer song creation.
Thanks to Justas Sadvecius for the library!

The Finch is a robot for computer science education. Its design is the result
of a four year study at Carnegie Mellon's CREATE lab.

http://www.finchrobot.com
"""

from finch import Finch
from time import sleep
import notes

#Main function for the music player example program"""

#Initialize the finch    
mode = input("What mode, alarm or muysic player?")
finch = Finch()
x, y, z, tap, shake = finch.acceleration()
zAccel = finch.acceleration()[2]
timeList = [0.18,0.1,0.1,0.1]
if mode != 'a':
    

    songList = ['E5  C D E C D E F  D E F D E F G  A  GE F C D E G E D C ',
                    'D D5 A4 G G5 A4 F5# A4 D D5 A4 G G5 A4 F5# A4 '
                    'E D5 A4 G G5 A4 F5# A4 E D5 A4 G G5 A4 F5# A4 '
                    'G D5 A4 G G5 A4 F5# A4 G D5 A4 G G5 A4 F5# A4 '
                    'D D5 A4 G G5 A4 F5# A4 D D5 A4 G G5 A4 F5# A4 ',                'E5 E  E   C E  G    G4   C5  G4   E   A BBb A  G  '
                    'E5  G  A F G  E  C D B4  C5  G4   E   A BBb A  G  '
                    'E5  G  A F G  E  C D B4 -  G5 Gb F D#  E G4# A C5 A4 C5 D '
                    'G5 Gb F D#  E C6 C6 C6   '
                    'G5 Gb F D#  E G4# A C5 A4 C5 D  Eb  D  C    '
                    ' G5 Gb F D#  E G4# A C5 A4 C5 D G5 Gb F D#  E C6 C C--','------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------             -------------------------------------------------------------------------------------------------       ------------------------------------------------------       ------------------                 ---------------------- ------------------------------ -------------------------- ------------------ ------------------------- C10']
   

    song = 1
    while song > 0 and song < 6:
        #get which song
        song = int(input("Enter 1 for the Michigan fight song, 2 for Sweet Child of Mine,"
                    "3 for the Mario theme song, 4 for an alarm; any other number to exit."))
    
        if song >=1 and song <= 4:
            finch.led(0,255,255)
            notes.sing(finch, songList[song -1],timeList[song-1])
            finch.led(255,255,255)
        else:
            print('Exiting...')
else:
    print("alarm chosen")
    song = input("type 1 for the normal alarm and type 2 for a harsh alarm (not recommended), and 3 for mario theme song")
    snooze_time = input("type the snooze time (mins and without a label")
    song = int(song)
    snooze_time = int(snooze_time)
    songList = ['C  A  ','-   -','E5  G  A F G  E  C D B4  C5  G4   E   A BBb A  G  '
                    'E5  G  A F G  E  C D B4 -  G5 Gb F D#  E G4# A C5 A4 C5 D '
                    'G5 Gb F D#  E C6 C6 C6   '
                    'G5 Gb F D#  E G4# A C5 A4 C5 D  Eb  D  C    '
                    ' G5 Gb F D#  E G4# A C5 A4 C5 D G5 Gb F D#  E C6 C C--']
    song = 1
    if snooze_time == 5:
        time = 17000
    elif snooze_time == 3:
        time = 10000
    elif snooze_time < 3:
        time = 3333
    elif snooze_time > 5:
        time = 20000
    elif snooze_time == 10:
        20000
    else:
        time = 17000
    q = 1
    while q == 1:
        x, y, z, tap, shake = finch.acceleration()
        zAccel = finch.acceleration()[2]
        if tap:
            q = 3
            print("snoozing")
        elif zAccel < -0.7:
            q = 2
            print("Rise amd shine! Have a great day and don't forget to wake up on the right side of the bed!")
        else:
            finch.led(0,0,0)
            notes.sing(finch, songList[song -1],timeList[song-1])
            finch.led(255,255,255)
        if q == 3:
            r = 0
            while r < time:
                x, y, z, tap, shake = finch.acceleration()
                zAccel = finch.acceleration()[2]
                if shake or zAccel < -0.7:
                    r = time
                else:
                    finch.led(0,0,0)
                    r = r+1
                    print(r)
                    finch.led(100,100,100)
            q = 1
finch.close

    
