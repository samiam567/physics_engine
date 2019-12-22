# a simple program that calculates the standerd deviation of # system
from math import sqrt
#name the #s...
dig1 = 20

dig2 = 29

dig3 = 26

dig4 = 25

dig5 = 10
"""
dig6 = 4

dig7 =

dig9 =

dig10 =
"""
# set the # of #s
times = 5
# and compute!
mean = dig1 + dig2 + dig3 + dig4 + dig5#  + dig6 #+ dig7 + dig8 + dig9 + dig10
mean = mean / times
mean = str(mean)
print('The mean is ' + mean)
mean = float(mean)
sp_mean = (dig1 - mean)**2 + (dig2 - mean)**2 + (dig3 - mean)**2 + (dig4 - mean)**2 + (dig5 - mean)**2 # + (dig6 - mean)**2 #+ (dig7 - mean)**2 + (dig8 - mean)** + (dig9 - mean)**2 + (dig10 - mean)**2
sp_mean = sp_mean / times
standard_deviation = sqrt(sp_mean)
standard_deviation = str(standard_deviation)
print("The standard deviation of that set is " + standard_deviation)
