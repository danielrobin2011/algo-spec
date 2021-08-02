import subprocess
import zlib

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

string_data_list = str(data_list).replace("'", '"')

#print(string_data_list)

payload = zlib.compress(string_data_list.encode("utf-8"))

subprocess.call(['java',
 '-jar',
 'F:/Practice/graph-algorithms/StronglyConnectedComponents/target/StronglyConnectedComponents-1.0-SNAPSHOT-jar-with-dependencies.jar',
 str(payload)])
