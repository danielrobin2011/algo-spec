import util.StringPerm;

import java.util.*;
import java.util.stream.Collectors;

public class ClusteringBigMain {

    public static void main(String[] args) {

        ClusteringBigMain clusteringBigMain = new ClusteringBigMain();

        String bitString = "1 1 1 0 0 0 0 0 1 1 0 1 0 0 1 1 1 1 0 0 1 1 1 1 ";
        bitString = bitString.replace(" ", "");
        BitSet result = ClusteringBigMain.fromString(bitString);

        // generate 1 hamm xor masks (24)
        List<String> xorStringListWith1Ham = new ArrayList<>();
        StringPerm.findPermutations("000000000000000000000001".toCharArray(), 0, 24, xorStringListWith1Ham);
        List<BitSet> xorListWith1Ham = xorStringListWith1Ham.stream().map(ClusteringBigMain::fromString).collect(Collectors.toList());

        // generate 2 hamm xor masks (276)
        List<String> xorStringListWith2Ham = new ArrayList<>();
        StringPerm.findPermutations("000000000000000000000011".toCharArray(), 0, 24, xorStringListWith2Ham);
        List<BitSet> xorListWith2Ham = xorStringListWith2Ham.stream().map(ClusteringBigMain::fromString).collect(Collectors.toList());



    }



    public static BitSet fromString(String binary) {
        BitSet bitset = new BitSet(binary.length());
        int len = binary.length();
        for (int i = len-1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                bitset.set(len-i-1);
            }
        }
        return bitset;
    }

}