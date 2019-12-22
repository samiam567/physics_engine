import turtle
def testfor(test_criteria):
    number = 0
    test_criteria = int(test_criteria)
    while (number != test_criteria):
        number = str(number)
        print(number + " is not the number you are looking for.")
        number = int(number)
        number = number + 1
    number = str(number)
    print(number + " is the number you are looking for.")
    
