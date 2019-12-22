fo = input("Who is the first compeditor?")
ft = input("What is the second compeditor?")
fo_score = 0
ft_score = 0

#intro & first question
print("Welcome to trivial persute!")
print("The First question is for " + fo + ".")
fq = input("What is 1 + 1?")
if (fq == "2"):
    print("RIGHT! One point for " + fo + "!")
    
else:
    print("WRONG!")
    
#question function
def question(person , question , answer):
    print("The next question is for ")
    print (person + ".")
    print (question)
    fq = input()
    if (fq == answer): 
        print("RIGHT! One point for ")
        print (person)
        if person == "fo":
            ft_answer = fo_answer + 1
        elif person == "ft":
            ft_answer = ft_answer + 1
    else:
        print("WRONG!")
       
#put questions into the asking function
question(ft , "2+2" , "4")
question(fo , "9*3" , "27")
question(ft , "10*10" , "100")       
question(fo , "3x = 27, what is x?" , "9")
question(ft , "3x**2 = 81, what is x?" , "3")
question(fo , "25ab/2b**3 * 4b/5a" , "10/b")
