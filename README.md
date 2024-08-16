# Clean code performance

## Introduction

I'm actually fan of the book "Clean Code" by Robert C. Martin. 
I think it's a must read for every developer. 
I keep recommending it to my colleagues and friends, despite I don't agree with
it's core message: the fact that the rules and statements in the book are the one
and only way to write software and no rules can be broken at any cost.

This little example is a proof that sometimes, 
breaking the rules can lead to better performance.

> **SPOILER ALERT**
> 
> The performance gain is huge, the fastest implementation is 13-14 times faster 
> than the clean code variant.

## References

The inspiration actually came from several videos available on YouTube:

- https://www.youtube.com/watch?v=OtozASk68Os&t=118s an interview by therimeagen
- https://www.youtube.com/watch?v=tD5NrevFtbU&t=705s the original video that inspired me

## The problem

The problem solved in this code is the classic OOP example for polymorphism:
**the calculation of the area of a shape**.

1. We have several shapes: `Circle`, `Rectangle`, `Square`
2. Each shape has a method `area()` that calculates the area of the shape
3. The `area()` method is implemented in each class
4. We have a huge list of shapes and we want to calculate the total area of all shapes
5. We want to do it in the most efficient way

## The "tech stack"

I've chosen Java for several reasons:
- At IP Camp, we are implementing a performance critical trace file dissection tool in Java
- I'm familiar with Java
- Java is a good language to demonstrate the problem as it was referenced in the Clean Code book and a great language for object oriented programming
- As you will see, it will become tricky to achieve the best performance in the end

## The Clean Code rules

The following rules are not strictly from the book, but they are the ones I've learned from it:

### 1. Polymorphism

You should avoid *IF/SWITCH* statements and use polymorphism instead.
In our case it means, we will have an abstract class `Shape` (or an interface) 
with a method `area()` and several classes that extend/implement this class/interface.

### 2. Should not know about internals of an object

Aka hide the implementation details. We will provide methods to access the data of the object, 
but we will not allow direct access to the data. Also, the area calculation shall
be implemented in the class itself.

### 3. Objects shall be small

The classes shall be as small as possible.

### 4. 1 thing

Each class shall do only one thing, and do it's single responsibility well.

### 5. Don't Repeat Yourself (DRY)

Avoid code duplication. Introduce methods to avoid code duplication.

## Another view: the SOLID principles

### Single Responsibility Principle (SRP):  

Each class should have only one reason to change. 
For example, a Circle class should only be responsible for circle-related operations.

### Open/Closed Principle (OCP):  

Classes should be open for extension but closed for modification. 
For example, you can add new shapes without modifying existing shape classes.

### Liskov Substitution Principle (LSP):  

Subtypes must be substitutable for their base types. 
For example, any shape class should be able to replace the base Shape class 
without altering the correctness of the program.
An example is the `Square` class that is a specialization of the `Rectangle` class.
It can be used anywhere a `Rectangle` is used. 
The only restriction is that the width and height must be the same.

### Interface Segregation Principle (ISP):  
Clients should not be forced to depend on interfaces they do not use. 
For example, a Shape interface should only include methods that are relevant to all shapes.

### Dependency Inversion Principle (DIP):
High-level modules should not depend on low-level modules but on abstractions. 
For example, a ShapeCalculator should depend on a Shape interface rather than concrete shape classes.

## The environment

All the tests were ran on a MacBook Pro M2 Max with 32GB of RAM.

## The implementations

### Part 1: The Clean Code implementation

The part 1 implements the clean code rules and follows SOLID principles.
However, it does not embrace all Java "best practices" for performance, e.g.:
all classes, methods and fields are not final, thus all methods are virtual.

We have an `AbstractShape` base class implementing the `Shape` interface.
Circle, Rectangle and Triangle classes extend the `AbstractShape` class.
Square class extends the `Rectangle` class.

Our Factory methods simply return instances of the classes.

The base performance score of this implementation was ~`7657` ops/s. 
This test was using `Stream` API to traverse the list of shapes and calculate the total area.

3 other implementations were tested:
- Using `Stream` API, but with *parallel* stream: `10162` ops/s
- Using `forEach` method: `7912` ops/s
- Using classic `for` loop: `7912` ops/s

```text
ShapeBenchmark.part1a_polymorph_shapes_stream           thrpt    2    7657.758          ops/s
ShapeBenchmark.part1b_polymorph_shapes_stream_parallel  thrpt    2   10162.724          ops/s
ShapeBenchmark.part1c_polymorph_shapes_forEach          thrpt    2    7576.890          ops/s
ShapeBenchmark.part1d_polymorph_shapes_for              thrpt    2    7912.852          ops/s
```

> **Conclusion**
> - The parallel stream added ~25% performance gain
> - Using `forEach` method is as fast as using `for` loop
> - Streams used this way are not slower than the classic `for` loop
> 
> **Note**
> - In Mercury dissection pipeline, we were using `Stream` API extensively until finding out that it wastly underperforms when using in a loop.

### Part 2: The naive optimization

When I started Java programming, 
I was told that the JVM is smart enough to optimize the code.
However, senior developers also told me to avoid virtual methods and 
use final methods instead.
For better performance and to calm down their OCD.

Our second implementation is replacing the classes of the first edition
with records. Records are final classes in Java, so the methods are final too.
This shall be faster, right?

Well, not really. The performance is almost the same as the first implementation.

```text
ShapeBenchmark.part2_polymorph_records_for_each         thrpt    2    7926.638          ops/s
ShapeBenchmark.part2b_polymorph_records_for             thrpt    2    7855.549          ops/s
```

> **Conclusion**
> - The performance is almost the same as the first implementation
> - The records are not faster than the classes 
> - Final methods are not faster than virtual methods
> - The `forEach` method is as fast as the `for` loop

### Part 3: Get rid of polymorphism

Let's see what happens if we get rid of inheritance. Part 3 has two options:
The first run will still use the `Shape` interface to access the `area()` method,
but otherwise all logic is implemented in a single class `ShapeData`.

In the second run, we will see what happens if we eliminate the interface and
call the ShapeData methods directly. Those are final methods, so they should be faster.

This implementation gets rid of inheritance, but will rely on IF/SWITCH statements for
determining the shape type. We are starting to break the Clean Code rules here.

```text
ShapeBenchmark.part3_multipurpose_records               thrpt    2   27061.431          ops/s
```

> **Conclusion**
> - The performance is 3.5 times faster than the first implementation
> - The `forEach` method is as fast as the `for` loop

### Part 4: Tweaking ShapeData area calculation

Until now, we implemented the area calculation in the `ShapeData` class using
primary school math formulas. 

Let's see what happens if we use a different approach:
- Notice, that all formulas are the same, just the parameters are different
- All shapes have two length parameters. They are not always edges, but they are used to calculate the area
- We have a different coefficient for each type of shape

The formula for the area calculation is the same for all shapes:
`area = a * b * c`

Where:
- `a` is the first length parameter (radius for circle, width for rectangular shapes, etc.)
- `b` is the second length parameter (a=b for square and circle)
- `c` is the coefficient

The coefficient is different for each shape:
- `PI` for circle
- `1` for square and rectangle
- `0.5` for triangle

```text
ShapeBenchmark.part4_fast_records                       thrpt    2   92309.375          ops/s
```

> **Conclusion**
> - The performance is 12 times faster than the first implementation
> - The performance gain with LUT is huge, 3.5 times faster than without LUT

### Part 5: Data oriented programming

This is where the real performance gain comes from and 
also where things get tricky. Java is not very good at data oriented programming.

DOO means we organize the data in a way that the CPU can process it efficiently.
Data groups that are meant to be processed together are stored together in memory.

Java is not very good at this, because it's reliance on objects and references.
You can't have an array of objects, you have an array of references to objects.
You also can't have a list of primitives, you have a list of primitive wrappers.

The best way to do this in Java is to use arrays of primitives.

The idea is to optimize the LUT implementation by storing a, b and c
in an array of doubles. We will lose the information about the shape type,
but we will gain performance.

The second run is taking even one step further: we will unroll the loop.
It basically means that we will write the loop body multiple times. 
Crazy, right?

```text
ShapeBenchmark.part5a_data_oriented                     thrpt    2  109700.005          ops/s
ShapeBenchmark.part5b_data_oriented_loop_unrolling      thrpt    2  110922.969          ops/s
```

> **Conclusion**
> - The performance is 14 times faster than the SOLID implementation
> - The loop unrolling is not significantly faster than the simple loop, probably because of the JVM optimizations
> - The Java code for data oriented programming is not very readable or maintainable

### Part 6: The POJO

Java folks often say that one must get rid of the getters and setters, and use
data members directly. This is called a POJO (Plain Old Java Object).

Let's see if there is any performance gain in this:

```text
ShapeBenchmark.part6a_pojo                              thrpt    2   77346.612          ops/s
ShapeBenchmark.part6b_pojo_ctab                         thrpt    2   75543.062          ops/s
```

> **Conclusion**
> - The performance is 10 times faster than the SOLID implementation
> - The _multipurpose_ record implementation is still slightly faster

## Conclusion

```text
ShapeBenchmark.part1a_polymorph_shapes_stream           thrpt    2    7561.284          ops/s
ShapeBenchmark.part1b_polymorph_shapes_stream_parallel  thrpt    2   10051.631          ops/s
ShapeBenchmark.part1c_polymorph_shapes_forEach          thrpt    2    7879.182          ops/s
ShapeBenchmark.part1d_polymorph_shapes_for_it           thrpt    2    7854.584          ops/s
ShapeBenchmark.part2_polymorph_records_for_each         thrpt    2    7926.638          ops/s
ShapeBenchmark.part2b_polymorph_records_for             thrpt    2    7855.549          ops/s
ShapeBenchmark.part3_multipurpose_records               thrpt    2   27061.431          ops/s
ShapeBenchmark.part4_fast_records                       thrpt    2   92309.375          ops/s
ShapeBenchmark.part5a_data_oriented                     thrpt    2  109700.005          ops/s
ShapeBenchmark.part5b_data_oriented_loop_unrolling      thrpt    2  110922.969          ops/s
ShapeBenchmark.part6a_pojo                              thrpt    2   77346.612          ops/s
ShapeBenchmark.part6b_pojo_ctab                         thrpt    2   75543.062          ops/s
```

1. One should not blindly follow the rules from the Clean Code book
2. Sometimes, breaking the rules can lead to better performance
3. Don't overrate the JVM optimizations
4. Don't rely on obsolete assumptions about Java performance
5. Clean Code has right about one thing for sure: using IF/SWITCH statements should be avoided, but the answer is not polymorphism, but rather clever programming