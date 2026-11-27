# Exercise Session 3 - Payroll System

Ruben Wealth Hu / 2902583383 / LC-20 / Object Oriented Programming

---

# Background on Inheritance

We learned how to do inheritance on this assignment, which is a way to make a new child class from an already existing class, with added/modified properties.

In inheritance there's a hierarchy, Parent (Superclass) and Child (Subclass)

Think like this, take a book for example, every single book you found a bookstore have Publisher, Author, Title, and ISBN.

But not all books are identical. Some might have Citation, some might have Series, some might have synopsis, etc.

These are books, but with different properties and have some "already agreed on" properties. So unless you want to make 927 book standards, you want to inherit the known book properties and further mould them.

# Assignment

## Why Employee Class is a Superclass?

If we run the software, we can see this output:

```bash
Employee Type : Permanent
Employee ID   : PRMNT-0001
Employee Name : Benjamin Bearington
Base Salary   : Rp 20,000,000
Allowance     : Rp 880,000
Total Salary  : Rp 20,880,000

================================

Employee Type : Contract
Employee ID   : CNTRC-0002
Employee Name : Joshua Rexborne
Working Days  : 14
Daily Rate    : Rp 200,000
Total Salary  : Rp 2,800,000

================================
```

From this we can build a premise that is:

> Every employee has an ID, Name, Type, and Total Salary. But
>
> - Permanent Employee has **Allowance** and **Base Salary**.
> - Whereas Contracted one has **Working Days** and **Daily Rate**.
>
> Both are having a different way to calculate the overall salary.

So it makes sense to inherit the known universal attributes to a new class with extended/different functionality.

## How do I apply inheritance in both Permanent and Contracted Employee data?

In the assignment, we were told that:

- The Salary for
  - Contracted is calculated from **Working Days \* Daily Rate**
  - But Permanent is from **Base Salary + Allowance**
  - Allowance is calculated from **50K + (** **_Last 2 digits of our Student ID_** **\* 10K )**

The difference is really how the salary is calculated, but we can use the same method/function name, that is `displayInfo()` and `calculateSalary()`.

Both can use the same method's name, which allows the codebase to be simpler later down the road. Yet allows the overall code to be aware how they calculate the salary.

In the file `Employee.java`, `class Employee` has a method to return salary as it is. This is a failover.

```java
class Employee {
	...
	public Integer calculateSalary() {
		return baseSalary;
	}
	...
}
```

But you can see below the codebase where we extends `Employee` to `PermanentEmployee` and `ContractEmployee` (which means inheriting `Employee` to both classes), we can override the salary calculation.

```java
class ContractEmployee extends Employee {
	...
    @Override
    public Integer calculateSalary() {
        return (workingDays * dailyRate);
    }
	...
}
```

Additionally, we can also extends the constructor. Superclass `Employee` has the constructor like this

```java
class Employee {
	...
    public Employee (String tempId, String tempName, Integer tempBaseSalary) {

        // PRMNT = Permanent
        // CNTRC = Contract
        if (tempId.isEmpty() || !tempId.matches("(PRMNT|CNTRC)-[0-9]{4}")) {
            throw new IllegalArgumentException("""
                    ID should be allocated with format either PRMNT-XXXX or CNTRC-XXXX.
                    Can't be Empty and Case Sensitive and if you need \"PRMNT-1\", use \"PRMNT-0001\" instead.""");
        }

        if (tempName.isEmpty()) {
            throw new IllegalArgumentException("Name shouldn't be empty.");
        }

        if (tempBaseSalary < 1) {
            throw new IllegalArgumentException("Base salary should be 1 IDR or more");
        }

        this.employeeId = tempId;
        this.employeeName = tempName;
        this.baseSalary = tempBaseSalary;
    }
	...
}
```

which then we can extends it to something like this (btw `ContractEmployee` is a very good example)

```java
class ContractEmployee extends Employee {
	...
    public ContractEmployee(String tempId, String tempName, Integer tempBaseSalary, Integer tempWorkingDays, Integer tempDailyRate) {
        super(tempId, tempName, tempBaseSalary);
        if (tempWorkingDays < 0) {
            throw new IllegalArgumentException("Working days should be 0 days or more");
        }
        if (tempDailyRate < 1) {
            throw new IllegalArgumentException("Daily rate should be 1 IDR or more");
        }
        this.workingDays = tempWorkingDays;
        this.dailyRate = tempDailyRate;
    }
	...
}
```

`super()` means to call the Superclass. You can even do this to fetch/call certain methods or attributes directly.

## Why `calculateSalary()` Method needs 2 overrides?

We already addressed this before, the calculation of total salary is different from `ContractEmployee()` and `PermanentEmployee()`.

We can make it a separate function, but it means messier codebase. For example, hypothetically you need to do something like this

```java
switch (type) {
	case 1:
		calculateSalaryContract;
		break;
	case 2:
		calculateSalaryTemporary;
		break;
}
```

where you use if or switch logic. But you can use a single function like this:

```java
calculateSalary();
```

A lot simpler right?

## Why Inheritance?

It allows to practice DRY (Don't Repeat Yourself). A principal that minimizes unnecessary duplications. Back to our book example.

Is it reasonable to rewrite

```java
class Book {
    private final String bookISBN;
    private final String bookName;
    private final String bookAuthor;
    private final String bookPublisher;

    public Employee (String tempISBN, String tempName, String tempAuthor, String tempPublisher) {
		// Insert Validation Logics Here
    }
}
```

for books that has different genres?

Not only you have to deal with 5 or so classes, but it's also hard to maintain and crucially, not scalable.

Also probably not so Git Friendly (have you ever done `git diff` before? If you have, congratulations, we're relatable).

So inheritance brings advantages such as:

- Cleaner Code Base
- Scalable Subclasses
- Easier for VCS/SCM system (like Git)
- and many more.
