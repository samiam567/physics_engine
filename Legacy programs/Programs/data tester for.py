def test(data,letter,letter_num,caract):
    data = str(data)
    length = len(letter)
    length_data = len(data)
    caract = 0
    length = int(length)
    amount = 0
    while caract != length_data:
        if True:
            match = True
            for i in letter:
                if i != data[caract]:
                    match = False
                    break
                caract = caract + 1
                
            if match == True:
                print ("match")
                amount = amount + 1
            


            
            
            
        
    
    letter = str(letter)
    amount = str(amount)
    print("There are " + amount + " " + letter + "(s) in the data")
            
      
data = input("What data do you want to test?")
letter = input("What character or 2-6 letter word do you want to test for?")
test(data,letter,0,0)
