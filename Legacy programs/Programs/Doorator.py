print ("Do you have trouble finding what to do? Try out the Doorator! Just type in what you want to do, and get cooking!")
cat = input("So, will it be video games, or other?")

if (cat == ("video games")):
    ocat = input("computer, console, or mobile?")

elif(cat == "other"):
    ocat = input("educational, or other?")
    

    if ocat == ("other"):
        print ("How about Lacrosse, tennis, RC Car, Mini Reo, Lazer Tag, TV, Scooters, Bikes, Play in the snow, Play in Woods, Play on the play ground, a board game, cards, perplexuses, or lift weights?")

        if ocat == ("educational"):
            print ("What do you think about Codeing, Times Attack, ALEKS, Study, FPS Reaserch, How It's made, educational TV, Rokenbok, or The Science lab?")

        elif ocat == ("computer"):
            print ("How about Roblox, Minecraft, Times Attack, Pokemon Tower Defence, Codeing, or a silly powerpoint?")    

        elif ocat == ("console"):
                    print ("How about a go with Twilight Princess, Windwaker, Super Smash Bros., ocorina of time, Four swords, or skyword sword?")
        elif ocat == ("mobile"):
            print ("Whould you be interested in Bad Piggies, Bike Race, Angry birds, Split Pic, Sonic all stars raceing, listening to music, Cars Spy thing, 3d rollercoster rush, or Physics free full version?")
    else:
        print("invalid answer")
else:
    print("Invalid answer")
