## Collection API

#### Difference between fail-safe and fail-fast iterator?
- Fail-fast iterators operate directly on the collection itself. During iteration, if the collection is structurally modified at any time (except through the iterator's own remove or add methods), the iterator will throw a ConcurrentModificationException.They are used in collections like ArrayList, HashSet, etc.
- Fail-safe iterators make a copy of the collection and iterate over this snapshot. Thus, they do not throw any exception if the collection is modified while iterating but they will not reflect the modifications once the iteration starts. Examples include iterators from CopyOnWriteArrayList and ConcurrentHashMap.
- some ways to prevent concurrent modification exception : use iterator own method to remove, use thread-safe variants of collections from the java.util.concurrent package such as ConcurrentHashMap, CopyOnWriteArrayList, or CopyOnWriteArraySet(These collections have mechanisms to handle concurrent modifications.), Use java 8 features stream which didnt modify underlying dta structure.

#### What is the importance of the Comparable and Comparator interfaces in Java collections?

#### How do you synchronize a collection in Java and why would you do it?

#### What is the importance of equals() and hashCode() methods in java/collection?

#### how hash map works?

#### write custom implementation of array list?

#### write custom implementation of linked list?

