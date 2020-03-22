import sys


def max_number_of_houses(a, b, price, test_no):
    sum = 0
    cnt = 0
    price.sort(reverse=False)
    for i in range(0, a):
        if sum+price[i] <= b:
            sum = sum + price[i]
            cnt += 1
    print("Case #{}: {}".format(test_no+1, cnt))
    sys.stdout.flush()

T = int(input())
if T >= 1 and T <= 100:
    for i in range(T):
        a, b = map(int, input().split())
        price = list(map(int,  input().split()))
        max_number_of_houses(a, b, price, i)
