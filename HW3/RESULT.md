# Result
## Run on Terminal
```bash
javac Homework3.java
java Homework3
Let's smoosh arrays!

smooshing [  3  7  7  7  4  5  5  2  0  8  8  8  8  5  ]:
[  3  7  4  5  2  0  8  5  -1  -1  -1  -1  -1  -1  ]
smooshing [  6  6  6  6  6  3  6  3  6  3  3  3  3  3  3  ]:
[  6  3  6  3  6  3  -1  -1  -1  -1  -1  -1  -1  -1  -1  ]
smooshing [  4  4  4  4  4  ]:
[  4  -1  -1  -1  -1  ]
smooshing [  0  1  2  3  4  5  6  ]:
[  0  1  2  3  4  5  6  ]

Let's squish linked lists!

squishing [  3  7  7  7  4  5  5  2  0  8  8  8  8  5  ]:
[  3  7  4  5  2  0  8  5  ]
squishing [  6  6  6  6  6  3  6  3  6  3  3  3  3  3  3  ]:
[  6  3  6  3  6  3  ]
squishing [  4  4  4  4  4  ]:
[  4  ]
squishing [  0  1  2  3  4  5  6  ]:
[  0  1  2  3  4  5  6  ]
squishing [  ]:
[  ]

Let's twin linked lists!

twinning [  6  3  6  3  6  3  ]:
[  6  6  3  3  6  6  3  3  6  6  3  3  ]
twinning [  4  ]:
[  4  4  ]
twinning [  ]:
[  ]
```

## Learnt from This HW  
If you look carefully in this homework, you would find there has a lot of test code, even more than code being tested. In class, Prof. Jonathan Shewchuk said
> In real-world software development, often the size of the test code is larger than the size of the code being tested.  

### Three Aspect
1. Modular Testing:
    (1) Test Driver: call the code, check results  
    (2) Stubs: Bit of code **called** by the code being tested  
2. Integration Testing:  
    (1) Define interfaces well  
    (2) Learn to use a debugger  
3. Result Verification:  
    (1) Inspect a data structure and verify that all invariants are satisfied.  
    (2) Algorithm Result Checker: assert    

### Two Principles
1. All-path testing
2. "Boundary cases" should be tested, as well as non-boundary

### Implement of Test Code in this HW
1. TestHelper.java: verify whether the result is correct or not.  
```java
TestHelper.verify( ... );
```
2. In Homework3.java and SList.java, use many cases which contain boundary cases and non-boundary cases to text the part function in the modular code.

3. In SList.java, the test code is wrapped to function to be called.
```java
public static void main (String[] args) {
    testEmpty();
    testAfterInsertFront();
    testAfterInsertEnd();
} 
```