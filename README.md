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


## String concepts
### Definition
public final class String ---> Immutable.  marked as final so value cannot be changed. if we try to modify, it creates a new object only.
    implements java.io.Serializable, ---->  convert object to streams to transfer to nw/db etc.
     Comparable<String>,  ---> implement compareTo method for comparsion
     CharSequence, ---> implements char related methods charat, etc.
     Constable, 
     ConstantDesc

### Creation/ Srting Pool concept
```
// Using literals
        String str1 = "main"; // saves in string pool
//Using new operator
        String str2 = new String("main"); // creates as new object.
        // it is also same as above. object created with chars
        char[] str3arr = {'m','a','i','n'};
        String str3 = new String(str3arr);
        System.out.println("literal == new object: " + (str1 == str2)); // false. str1 in string pool str2 in heap memory
        String str4 = str2.intern(); // Make string obj value available in string pool
        System.out.println("after intern: " + (str1 == str4)); // true
```
Output:
```
literal == new object: false
after intern: true
```
Using literal, string is created in string pool. if same value is created again, only variable is created and pointed to the same pool value.
new operator only creates in the heal memory. To make it available in the pool, intern() is called.

### char array
For interview programs, many times string is split into arrays and solve the problems.
```
        String str1 = "main";
        // to int stream
        str1.chars().forEach(System.out::println);
        str1.codePoints().forEach(System.out::println);
        // if characters were differnt language then chars() wont understand. eg
        String str6 = "Hello 👋";
        str6.codePoints().forEach(System.out::println); // it prints correctly the unknown characters
        // to array
        char[] chars= str.toCharArray();
        for (char a : chars) {
            System.out.println((int)a);
        }
```

### Serialization
Before start with serialization, lets start with fundamental concept of byte stream. A byte is a sequence of 8 bits(0/1s) which is directly understood by machines. Byte streams are universal language for data transfer and storage. All digital content such as pfg, image are represented as bytes. 

#### How machine differentiate the byte steams of differnt types like pdf/image/txt etc?
The first few bytes of the file/image contains the specific sequence. eg. jpeg starts with bytes FF D8 FF. Also different file formats use differnet compression, meta data which also helps to identify the file type.

####  Why we know the byte stream? 
Because, Serialization is the process of converting an Object(not class) to sequence of bytes, mostly used for storage ot transmission(includes json,xml)
Considerations:
Versioning Issues: if object is altered, then deserialisation may cause issues. so try to implement the serialisation version number.
security: during deserialisation content, some code injection is possible.

Desrialization is recreation of object from bytes. Note that new object is created in this process.

#### Implement serialization
By implements serializable marker interface. Marker refers interface without any method/field definition. ObjectOutputStream class and ObjectInputStream class is used to serialize/deserialize the object. static fields are class specific so they wont serialize. 

If the serialVersionUID of the class differs from that of the object being deserialized, a InvalidClassException will be thrown. If a serializable class does not explicitly declare a serialVersionUID, then runtime will calculate a default serialVersionUID.
Internally Java.serialization automatically interacts with jvm & handles the serialization process based on reflection. 
```
import java.io.Serializable; -- io packet
public class User implements Serializable { -- implements serializable interface
    private static final long serialVersionUID = 6529685098267757690L; // during deserialization to verify that the sender and receiver of a serialized object have loaded classes that are compatible with respect to serialization.

    private String name;
    private transient String password; // 'transient' means it will not be serialized
    .................................

}
public class SerializationExample {
    public static void serialize(User user, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
#### serializable vs externalizable
Java Externalization involves the Externalizable interface, which extends Serializable and requires the implementation of two methods: writeExternal(ObjectOutput out) and readExternal(ObjectInput in). It gives more control of what data to save or trasfer.
```
public interface Externalizable extends java.io.Serializable {
    void writeExternal(ObjectOutput out) throws IOException;
    void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;
}
```

#### serialization in inheritance
If parent class implements serializable, all its child class also serialized.
If child class alone implements serializable, during deserializing, Java will invoke the no-arg constructor of the non-serializable superclass to initialize the superclass fields. If the superclass does not have a no-arg constructor, a InvalidClassException will be thrown during runtime.

#### any relation of volatile in serializable?
```
private transient String user; // skipped during serialization
private volatile Stirng usercount; // it behaves normal like other fields

```
volatile field are serialized normally like other fields. it is helpful in multi threaded enironemnt. volatile fields are stored in main memory instead of local thread memory. so that all threads have visibility to the variable.

#### how to write immutable class like string class?
- make class as final
- make fields as final
- no setters so that no one can update the fields.
- object created usign constructors.
```
public final class ImmutableClass { // final keyword in class
    private final int value; // fields are final
    private final List<String> names;

    public ImmutableClass(int value, List<String> names) { // object creates using constructor
        this.value = value;
        this.names = new ArrayList<>(names); 
    }
................. // no setters only getters
}

```

#### deep cloning in serializable object/Deserialization no-org constructor concept
if a object contains fields which refers another object. serialization happens to all the referred objects as well during serialization.
During desiralization, contructor is not used to initialise the object. it directly sets the values in the fields using reflection. if some values are initialised in constructor, those values are not serialised. To overcome, we can write custom logic in readobject(Inputstream in) 