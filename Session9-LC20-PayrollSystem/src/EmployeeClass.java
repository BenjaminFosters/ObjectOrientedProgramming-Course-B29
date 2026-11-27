class Employee {
    final Integer nimGweh = 83; // 29025833-83

    private final String employeeId;
    private final String employeeName;
    private final Integer baseSalary;

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

    public String displayInfo(){
        return """
                Employee Type : %s
                Employee ID   : %s
                Employee Name : %s""".formatted(employeeId.startsWith("PRMNT") ? "Permanent" : "Contract", employeeId, employeeName);
    }

    public Integer calculateSalary() {
        return baseSalary;
    }
}

class PermanentEmployee extends Employee {
    final int allowance = (50000 + (nimGweh * 10000));

    public PermanentEmployee(String tempId, String tempName, Integer tempBaseSalary) {
        super(tempId, tempName, tempBaseSalary);
    }

    @Override
    public Integer calculateSalary() {
        return super.calculateSalary() + allowance;
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + """
                
                Base Salary   : Rp %,d
                Allowance     : Rp %,d
                Total Salary  : Rp %,d""".formatted(super.calculateSalary(), allowance, calculateSalary());
    }
}

class ContractEmployee extends Employee {
    private final Integer workingDays;
    private final Integer dailyRate;

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

    @Override
    public Integer calculateSalary() {
        return (workingDays * dailyRate);
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + """
                
                Working Days  : %d
                Daily Rate    : Rp %,d
                Total Salary  : Rp %,d""".formatted(workingDays, dailyRate, calculateSalary());
    }
}