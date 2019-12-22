teacher = input("Which teacher do you want to rate?")
rateing = input("How do you rate " + teacher + " on a scale of 1 to 10, 1 being mean, and 10 being really good?")
if str(rateing) <= str(1):
    print ( teacher + " is from the black lagoon, I feel bad for you!")
elif str(rateing) >= str(10):
    print (teacher + " is AWSOME!")
else:
    print ( teacher + " is your average techer.")
