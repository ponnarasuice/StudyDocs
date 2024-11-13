# StudyDocs

## How Java string is saved in binary?
As we know computers can understand only the binary language 0&1s. We humans use different language(English, Spanish etc) to communicate each other. In Java, String is basic entity to take human texts. Lets see how the java string is stored into computers.
Java String is nothing but the sequence of characters. String uses UTF-16(Unicode) encoding which is standard these days. UTF-16 is a character encoding that uses one or two 16-bit units (code units) to represent each code point.  (16 bits) can encode characters from U+0000 to U+FFFF directly (these are known as the Basic Multilingual Plane or BMP characters). beyond this range (U+010000 to U+10FFFF, which are known as Supplementary Characters) are encoded in UTF-16 using two code units, called a surrogate pair.

```
        String strA = "A";
        char[] chrArr = strA.toCharArray();
        char chrA = chrArr[0];  // 'A' stored using UTF-16 encoding in a char
        System.out.println("The character is: " + chrA); // A
        System.out.println("Unicode code point: " + (int) chrA); // decimal value 65
        System.out.println("Ascii encoding point: " + (int) chrA); // unicode is backward compatible to ascii so same decimal value 65
        System.out.println("Hex text value: " + Integer.toHexString(chrA)); // U+0041, which is hexadecimal for 65 without any leading zeros or the "U+"
        System.out.println("Binary representation: " + Integer.toBinaryString(chrA));// binary representation
        System.out.println("Hex to decimal value:" +  Integer.parseInt(Integer.toHexString(chrA), 16));
        System.out.println("Binary to decimal:" +Integer.parseInt(Integer.toBinaryString(chrA), 2));
```

Note that Unicode is backward compatible to ASCII which lacks wide characters(eg: other language characters). String is converted to character and then encoded to UTF16/Ascii representation and finally to binary. It is difficult to represent the decimal(base10) to binary(base2). So Hexadecimal(base 16) is used to represent the unicode/ascii decimal value. Hexa values - 0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F, then 01,02... 09,0A,0B,0C,0D,0E,0F Easy to convert binary to hexa and vice versa.


