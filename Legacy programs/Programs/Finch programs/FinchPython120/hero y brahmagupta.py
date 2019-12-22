from math import sqrt
while True:
    print("hero")
    a = (input())
    b = (input())
    c = (input())
    if(len(a+b+c)>=3):
        a = int(a)
        b = int(b)
        c = int(c)
        s=(a+b+c)/2
        ans=(s*((s-a)*(s-b)*(s-c)))
        ans = str(ans)
        print("sqrt(" + ans)
        ans = float(ans)
        ans = sqrt(ans)
        print(ans)
    print("brahmin")
    a = (input())
    b = (input())
    c = (input())
    d=(input())
    if(len(a+b+c+d) >= 4):
        a = int(a)
        b = int(b)
        c = int(c)
        d = int(d)
        s=(a+b+c+d)/2
        ans=((s-a)*(s-b)*(s-c)*(s-d))
        ans = str(ans)
        print("sqrt(" + ans)
        ans = float(ans)
        ans = sqrt(ans)
        print(ans)
        
