
## JVM concepts
VM - virtual machine :  software tool enable to run os/softwares isolated from host os
JVM - java virtual machine: software with isolated area only runs the java code only
```
public class EmployeeTest {
    private static int count = 1;
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.setId(1l);emp.setName("ganesh");
        EmployeeTest employeeTest = new EmployeeTest();
        employeeTest.displayData(emp);
        // shows which class loader loads this class
        System.out.println("Classloader of this class:"
                + EmployeeTest.class.getClassLoader());
    }
    private void displayData(Employee emp) {
        System.out.println(emp.getId());
        System.out.println(emp.getName());
    }
}
```
- When JVM start executes this program, it tries to load the class file(byte code). JVM (refers some class inside rt.jar in the $JAVA_HOME/jre/lib directory) Deligates this work to java.lang.ClassLoader.
- class is searched in the order of classloaders: Application classloaders (usually our custom class/files in the class path). if not found, then try to load using Platform/extention class loader(usually libs). if not found there, finally checks in bootstrap classloader. class loaded by parent classloader is visible to child but not vice versa. only one class with the name is loaded. if class is not found, classnotfound exception is thrown.
- Here, Application classloader can find EmployeeTest class and loads it. we can explicitly ask classloader to load and initialise Class.forName("java.lang.String"). if Class.load() -> it only loads into memory.  
- During loading, methods are loaded & initialised and placed in method area. objects are stored in heap area. Permgen/Metaspace(after java8) is permanent(generation) space & part of heap area, contains metadata of classes loaded by class loader including static values of the classes. After java8, permgen is replaced with metaspace which is native memory to os than jvm so outof memory due to permgen will be reduced. garbage collection is not efficient for classloaders so increase in metaspace will reduce the out of memory issue. we can control the size by metaspacesize/maxmetaspacesize/minmetaspacepercentage etc.
- After initialisation, JVM starts execution. Main thread is created and thread stack is created. Each thread has own thread stack. it calls methods to execute. each method has new stack once method is over it returns to the thread execution flow. 
- Once all the methods are executed, jvm stops. we can stop explicitly by call System.exit(0)

### Jit compiler
- Interpretor interprets the byte code and convert to machine code. it is costly process. some frquently used methods are precompiled to machine code. During execution, these machine codes are dierectly used by jvm thus optimising the performance. this work is done by Jit compiler. Refer the link to refresh the jvm concepts
https://www.freecodecamp.org/news/jvm-tutorial-java-virtual-machine-architecture-explained-for-beginners/

### Garbage collectors

#### Volatile keyword &  multi threaded in jvm
```
class Test  
{  
static int var=5;  // static is only one value in the memory
//  volatile int var=5; // it is not stored in thread local stack
}  
```
Each thread has own stack, when the method is executed, local copy of variable is cached and used for manupulation. when multi threads are access this resource then inconsistence can occur. volatile keyword makes this variable available in main thread memory instead of local thread. so each thread access only from the main memory.
Each thread executes in its own stack memory. after the execution, it will return to the main memory.The problem with threads not seeing the latest value of a variable because it has not yet been written back to main memory by another thread, is called a "visibility" problem.
