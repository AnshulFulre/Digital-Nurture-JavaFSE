
-- 1. Insert Customers
-- ID 1: Senior customer (> 60) with a loan
INSERT INTO Customers (customer_id, name, age, is_vip) VALUES (1, 'Bheem', 65, 'FALSE');
-- ID 2: Young customer (< 60) with a loan
INSERT INTO Customers (customer_id, name, age, is_vip) VALUES (2, 'Chutki', 45, 'FALSE');
-- ID 3: Customer with balance > 10,000 
INSERT INTO Customers (customer_id, name, age, is_vip) VALUES (3, 'Raju', 38, 'FALSE');
-- ID 4: Customer with balance <= 10,000
INSERT INTO Customers (customer_id, name, age, is_vip) VALUES (4, 'Jaggu', 29, 'FALSE');
-- ID 5: Senior customer (> 60) without a loan
INSERT INTO Customers (customer_id, name, age, is_vip) VALUES (5, 'Bholu', 62, 'FALSE');

-- 2. Insert Accounts
-- Raju has accounts totaling > 10,000 
INSERT INTO Accounts (account_id, customer_id, account_type, balance) VALUES (101, 3, 'Savings', 6000.00);
INSERT INTO Accounts (account_id, customer_id, account_type, balance) VALUES (102, 3, 'Checking', 5000.00);
-- Jaggu has accounts totaling <= 10,000
INSERT INTO Accounts (account_id, customer_id, account_type, balance) VALUES (103, 4, 'Savings', 4000.00);
INSERT INTO Accounts (account_id, customer_id, account_type, balance) VALUES (104, 4, 'Checking', 2000.00);
-- Bheem accounts (Savings for interest update)
INSERT INTO Accounts (account_id, customer_id, account_type, balance) VALUES (105, 1, 'Savings', 1500.00);
-- Chutki accounts
INSERT INTO Accounts (account_id, customer_id, account_type, balance) VALUES (106, 2, 'Checking', 800.00);

-- 3. Insert Loans
-- Bheem (Age 65) - Due in 15 days (Senior, Due -> Needs discount & reminder)
INSERT INTO Loans (loan_id, customer_id, principal_amount, interest_rate, due_date) 
VALUES (201, 1, 5000.00, 6.50, SYSDATE + 15);

-- Chutki (Age 45) - Due in 45 days (Young, Not Due -> No discount, No reminder)
INSERT INTO Loans (loan_id, customer_id, principal_amount, interest_rate, due_date) 
VALUES (202, 2, 12000.00, 5.00, SYSDATE + 45);

-- Raju (Age 38) - Due in 10 days (Young, Due -> No discount, Needs reminder)
INSERT INTO Loans (loan_id, customer_id, principal_amount, interest_rate, due_date) 
VALUES (203, 3, 20000.00, 7.20, SYSDATE + 10);

-- Bholu (Age 62) - Due in 40 days (Senior, Not Due -> Needs discount, No reminder)
INSERT INTO Loans (loan_id, customer_id, principal_amount, interest_rate, due_date) 
VALUES (204, 5, 15000.00, 5.80, SYSDATE + 40);

-- 4. Insert Employees
INSERT INTO Employees (employee_id, name, department, salary) VALUES (1, 'Bheem', 'IT', 60000.00);
INSERT INTO Employees (employee_id, name, department, salary) VALUES (2, 'Chutki', 'IT', 65000.00);
INSERT INTO Employees (employee_id, name, department, salary) VALUES (3, 'Raju', 'HR', 45000.00);
INSERT INTO Employees (employee_id, name, department, salary) VALUES (4, 'Bholu', 'Sales', 50000.00);

COMMIT;
