from math import sqrt
numbers = ["0","1","2","3",'4','5',"6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","53","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70"]
numbers_ = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70]
numbers__ = list(range(1,1000))
numbers___= str(list(range(1,1000)))
letters_lower = list("#abcdefghijklmnopqrstuvwxyz._ !,'()/\-<>?:~1234567890=+")
letters_upper = list("#ABCDEFGHIJKLMNOPQRSTUVWXYZ._ !,'()/\-<>?:~1234567890=+")
skip = False
while True:
    translation = []
    prim = input("What do you want to encode/decode?")
    if (prim == "stop"):
        break


    if prim[0] in letters_lower:
       type_p = "letter"
       translation = []
    elif prim[0] in letters_upper:
        type_p = "letter"
        translation = []
    else:
        type_p = "number"
        translation = []
    

    
    for i in range(0,len(prim)):
#        print("line 28")
        
        if skip == True:
            skip = False
        else:
            char = prim[i]
        #    print("line 33")
#            print(char)
            if type_p == "letter":
                if char in letters_lower:
                    if " " not in letters_lower:
                        letters_lower.append(" ")
                    for z in range(0, len(letters_upper)):
                        if (char == letters_lower[z]):
                            trans = numbers_[z]                            
                            translation.append(trans)
    #                    else:
    #                        print("...")
                    skip = False

                elif char in letters_upper:
                    if " " not in letters_upper:
                        letters_upper.append(" ")
                    for z in range(0, len(letters_upper)):
                        if (char == letters_upper[z]):
                            trans = numbers_[z]
                            translation.append(trans)
       #                     print("-----------------------------")
     #                   else:
      #                      print("...")
                    skip = False
                    

            elif type_p == "number":
       #             print("Line 67")
          #          print(char)
                    if char in numbers:
         #               print("line 69")
                        char = int(char)                
      #                  print (char)
                        for x in range(0, len(numbers)):
          #                  print("line 72")
                            if (char == numbers_[x]):
              #                  print("line 73")
                                q = i + 1
                                if True:
                                    if True:
                 #                       print("prim[q] = ")
                             #           print(prim[q])
                                        if (prim[q] in numbers):                              
                                            char_2 = (prim[q])
                                            char_2 = str(char_2)
                                            char = str(char)
                                            char_com = char + char_2
                                            char_com = int(char_com)
                                            char_com = char_com
                                            char = int(char)
                                            char_2 = int(char_2)
                                            char_com = str(char_com)
                                           
         #                                   print("-------------------------------")
                                            skip = True                      
                                            for k in range(0,len(numbers)):
                                                if char_com == numbers[k]:
                                                    trans = letters_lower[k]
                                                    trans = str(trans)
                                                   # print(trans)
                                                    translation.append(trans)
                                                    #print(translation)
                                                    
                                        else:
                                            trans = letters_lower[x]
                                            trans = str(trans)
                                         #   print(trans)
                                            translation.append(trans)
                                          #  print(translation)
        #                                    print("--------------------------------")
                                            
                                
                                
                                

                            #else:
                                
                

    print("\/ Translation on next line \/")
    print(translation)

            
                            
