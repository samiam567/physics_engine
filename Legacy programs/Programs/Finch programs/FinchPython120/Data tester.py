data = input("What data do you want to test?")
letter = input("What charachter or 2-letter word do you want to test for?")

data = str(data)
length = len(data)
length = int(length)
charac = 0
amount = 0
#if true is a placeholder
if True:
    while charac < length:
        if data[charac] == letter[0]:
            charac = int(charac)
            charac = str(charac)
            charac = int(charac)
            charact = charac + 1
            if len(letter) == 2:
                if data[charact] == letter[1]:
                    charac = charact + 1
                    chara = charac + 1
                    charac = str(charac)
                    chara = str(chara)
                    print("charachtors " + charac + " and " + chara + " of the data make an " + letter)
                    charac = int(charac)
                    charac = charac - 1
                    amount = amount + 1
                    
                else:
                    nada = True
            else:
                amount = amount + 1
                print("charachtor " + charac + " of the data is an " + letter)

        else:
            nada = True
        charac = int(charac)
        charac = charac + 1
else:
    while charac < length:
        if data[charac] == letter[0]:
            charac = int(charac)
            charac = str(charac)
            charac = int(charac)
            charact = charac + 1
            if len(letter) == 2:
                if data[charact] == letter[1]:
                    charac = charac + 1
                    chara = charac + 1
                    charac = str(charac)
                    chara = str(chara)
                    print("charachtors " + charac + " and " + chara + " of the data make an " + letter)
                    charac = int(charac)
                    charac = charac - 1
                    amount = amount + 1
                    
                else:
                    nada = True
            else:
                amount = amount + 1
                print("charachtor " + charac + " of the data is an " + letter)

        else:
            nada = True
        charac = int(charac)
        charac = charac + 1
print("Data testing is finished")
amount = str(amount)
letter = str(letter)
print("There are " + amount + " " + letter + "'s in the data.")
