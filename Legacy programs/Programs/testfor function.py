def testfor(data, letter):
    if True:
        #getting preferances
        case = "no"
        replace = "no"
        pip_ans = "no"
        sections = "no"
       
      #  case = input("Do you want it to be case sensitive?")
      #  replace = input("Would you like to replace the word with something?")
      #  replace = replace.lower()
              
     #   pip_ans = input("Would you like to print in progress?")
    #    sections = input("Would you like to see the sections of the document that the word was found in?")
        #setting preferances to easily managable booleans
        if sections.startswith("y"):
            sections = True
        if case.startswith("y"):
            case = True
        
        if pip_ans.startswith("y"):
            pip = True
        else:
            pip = False
            
        if replace.startswith("y"):
            replace = True
          
    if(True):
        #code starts here
    #    data = input("What data do you want to test?")
      
       
        #    letter = input("What character or 2-6 letter word do you want to test for?")
       #     if replace == True:
       #         replace_word = input("What would you like to replace it with?")
           
            chosen_data = ("")
            q = 0
          
            #setting up the variables
            caract = 0
            data = str(data)
            length = len(letter)
            length_data = len(data)
            length = int(length)
            amount = 0
            nada = False
            if case != True:
                data = data.lower()
                letter = letter.lower()
            if replace == True:
                new_data = data
           # print("")
          #  print("")
            while caract != length_data:
                if True:
                    nada = False
                    
                    #advancing to the next set of charactors
                    carac = caract + 1
                    cara = carac + 1
                    car = cara + 1
                    ca = car + 1
                    c = ca + 1
                    cc = c + 1
                    
                    #testing for the letter/word
                    if length == 1 and data[caract] == letter[0]:
                        carac = str(carac)
                        letter = str(letter)
               #         print("character " + carac + " is a/an " + letter)
                        amount = amount + 1
                    elif length == 2 and data[caract] == letter[0] and data[carac] == letter[1]:
                        carac = str(carac)
                        letter = str(letter)
                        cara = str(cara)
              #          print("characters " + carac + " and " + cara + " make " + letter)
                        caract = int(caract)
                        carac = int(carac)
                        amount = amount + 1
                    elif length == 3 and data[caract] == letter[0] and data[carac] == letter[1] and data[cara] == letter[2]:
                        carac = str(carac)
                        cara = str(cara)
                        letter = str(letter)
                        car = str(car)
             #           print("characters " + carac + "," + cara + ",and " + car + " make " + letter)
                        cara = int(cara)
                        car = int(car)
                        caract = int(caract)
                        carac = int(carac)
                        amount = amount + 1
                    elif length == 4 and data[caract] == letter[0] and data[carac] == letter[1] and data[cara] == letter[2] and data[car] == letter[3]:
                        carac = str(carac)
                        cara = str(cara)
                        letter = str(letter)
                        car = str(car)
                        ca = str(ca)
            #           print("characters " + carac + "," + cara + "," + car + ",and " + ca + " make " + letter)
                        cara = int(cara)
                        car = int(car)
                        caract = int(caract)
                        carac = int(carac)
                        ca = int(ca)
                        amount = amount + 1
                    elif length == 5 and data[caract] == letter[0] and data[carac] == letter[1] and data[cara] == letter[2] and data[car] == letter[3] and data[ca] == letter[4]:
                        carac = str(carac)
                        cara = str(cara)
                        letter = str(letter)
                        car = str(car)
                        ca = str(ca)
                        c = str(c)
            #            print("characters " + carac + "," + cara + "," + car + "," + ca + ",and " + c + " make " + letter)
                        cara = int(cara)
                        car = int(car)
                        caract = int(caract)
                        carac = int(carac)
                        ca = int(ca)
                        c = int(c)
                        amount = amount + 1
                    elif length == 6 and data[caract] == letter[0] and data[carac] == letter[1] and data[cara] == letter[2] and data[car] == letter[3] and data[ca] == letter[4] and data[c] == letter[5]:
                        carac = str(carac)
                        cc = str(cc)
                        cara = str(cara)
                        letter = str(letter)
                        car = str(car)
                        ca = str(ca)
                        c = str(c)
           #             print("characters " + carac + "," + cara + "," + car + "," + ca + "," + c + ",and " + cc + " make " + letter)
                        cara = int(cara)
                        car = int(car)
                        caract = int(caract)
                        carac = int(carac)
                        ca = int(ca)
                        c = int(c)
                        cc = int(cc)
                        amount = amount + 1
                    else:
                        nada = True
                    if nada == False:
                        q = q + 1
                        for i in range((caract - 100),(caract + 100)):
                            if i < len(data):
                                if i >= 0:
                                    chosen_data = (chosen_data + data[i])               
                                    if pip == True:                         
                                        print(chosen_data)
                        if q > 1:
                            chosen_data = (chosen_data + "\n - \n")

                        if replace == True:
                            new_data = new_data[:caract] + replace_word + new_data[(caract + length):]
                            
                caract = int(caract)        
                caract = caract + 1
             #printing results   
            amount = str(amount)
            letter = str(letter)
            #print("There are " + amount + " " + letter + "'s in the data")
            amount = int(amount)
       #     if (len(chosen_data) > 0) and (sections == True):
            #    print("here is the section that the selected word or charachter was in:")
           #     print(chosen_data)
      #      if (replace == True) and (amount > 0):
          #      print("")
         #       print("Here is the data with the replacements:")
        #        print(new_data)
            return amount
                
amount = testfor("Hi. My name is alec. Hola, me llamo alec.", "a")   
print(amount)
