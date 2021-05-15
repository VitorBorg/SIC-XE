package com.ps.Memory;

import com.ps.Helpers.Helpers;

import java.util.Arrays;

public class MemoryBlock {
    String[] block = {"", "", "", "", "", ""}; // CADA ESPACO TEM 1 HEXA E CADA HEXA TEM 4 BITS

    public MemoryBlock(String data) {
        if (data.length() == 2) {
            this.block[0] = Helpers.printIfNotNull(data.substring(0, 1));
            this.block[1] = Helpers.printIfNotNull(data.substring(1, 2));
        }

        if (data.length() == 4) {
            this.block[0] = Helpers.printIfNotNull(data.substring(0, 1));
            this.block[1] = Helpers.printIfNotNull(data.substring(1, 2));
            this.block[2] = Helpers.printIfNotNull(data.substring(2, 3));
            this.block[3] = Helpers.printIfNotNull(data.substring(3, 4));
        } else if (data.length() == 6) {
            this.block[0] = Helpers.printIfNotNull(data.substring(0, 1));
            this.block[1] = Helpers.printIfNotNull(data.substring(1, 2));
            this.block[2] = Helpers.printIfNotNull(data.substring(2, 3));
            this.block[3] = Helpers.printIfNotNull(data.substring(3, 4));
            this.block[4] = Helpers.printIfNotNull(data.substring(4, 5));
            this.block[5] = Helpers.printIfNotNull(data.substring(5, 6));
        }

    }

    public void printMemoryBlock() {
        System.out.println(block[0] + block[1] + " " + block[2] + block[3] + " " + block[4] + block[5]);

    }

    @Override
    public String toString() {
        return block[0] + block[1] + block[2] + block[3] + block[4] + block[5];
    }
}
