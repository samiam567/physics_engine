time = input("Approximately haw many minutes do you have to prepare dinner?")
person = input ("List ONE of the people who you want to like today's meal.")
if (person == "Alec"):
    if (time <= str(30)):
        print ("Breakfast for dinner, PBJ, chili, Grilled Cheese, Egg salad, chickan nachos, and tacos")

    else:
        print ("French dip, Spagetti and Meatballs, Eggplant, Chickan Cutlet, Chinese food w/ Egg drop soup, Hamburgers, Meatloaf, and Steak.")
elif (person == "Erica"):
    if (time <= str(30)):
        print("Breakfast for dinner, grilled cheese, sandwiches, clam chowder, raviolli, PBJ, tacos, and chickan nachos.")
    else:
        print("Thanksgiving dinner, french dip, spagetti and meatballs, chinese food w/ egg drop soup")

else:
    if (person == "Emily"):
        if (time <= str(30)):
            print ("Breaded chickan sandwiches, chickan nachos, raviolli, sandwiches, frozen pizza, tacos, grilled cheese, and egg salad.")
        else:
            print ("Hammburgers, spagetti and meetballs, Chickan Cutlet, Chinese food w/ Egg drop soup, egg plant,  and chickan cudlet.")
    elif (person == "Tracy"):
        if (time <= str(30)):
            print ("anything")
        else:
            print ("anything")
    else:
        #that means the input is daddy
        if (time <= str(30)):
            print ("Leftovers, Sandwiches, Egg salad, ravioli, grilled cheese")
        else:
            print ("anything we have ALL the ingredients for")

        
     
