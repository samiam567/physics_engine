a = input()    
if (len(a) > 0):
    def sqrtfac(a , b):
        a = int(a)
        b = int(b)
        if (a%b == 0):
            c = sqrt(b)
            d = (a / b)
            print(c + " * sqrt(" + d + ")")
            return True
        else:
            return False
    if(sqrtfac(a , 25) == False):
        if (sqrtfac(a , 4) == False):
            if (sqrtfac(a , 9) == False):
                if (sqrtfac(a , 16) == False):
                    if (sqrtfac(a , 36) == False):
                        print("Nothing to reveal...")
                    else:
                        no = 23
                else:
                    no = 23
            else:
                no = 23
    				
        else:
            no = 23

    else:
        NO = 23
else:
    NO = 23
