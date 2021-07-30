

filename = "SCC.txt"
data_list = []

with open(filename, 'r') as fobj:
    for line in fobj:
        numbers = [int(num) for num in line.split()]
        data = {
            "source": numbers[0],
            "sink": numbers[1]
        }
        data_list.append(data)

print(len(data_list))
