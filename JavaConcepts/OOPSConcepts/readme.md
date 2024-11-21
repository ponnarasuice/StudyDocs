## OOPS concepts
class - blue print e.g. car class. attributes wheel, steering. functionality is driving.
object - live or physical entity of class. jvm contains the objects in java. these are real live entity.

### Encapsulation
Also known as data hiding. keeps the fields private. expose only necessary details. e.g. say exposes only get method. we can read only the data from this object. in this case, data set during object creatoin usign constructor.

### Inheritence
java achieves code reusalility by inheritence.
class can inherit the properties and methods of existing class.

### Polymorphism
refers same methods to do differnet things based on object it acted upon.
compile time polymorphism - same class different method signature.
runtime polymorphism - child class has same method name and signature. only during object creation it can be used so runtime.

### Abstraction
Hiding the implementation only shows how to use it.
achieves code reusability, maintainability.
interfaces - it wont contain instance variables and methods. methods are abstract by default. in java 8, we can define static/default methods but it wont contain any instance method. it mainly useful unrelated class can inherit interface e.g. serilisation can be implemented by unrelated classes. can be used to achieve multiple inheritence.
abstract - it also achieves inheritence. instance cannot be created directly only by child class. contains abstract and instance methods. mostly related classes are extending the abstract class. 


## SOLID principles