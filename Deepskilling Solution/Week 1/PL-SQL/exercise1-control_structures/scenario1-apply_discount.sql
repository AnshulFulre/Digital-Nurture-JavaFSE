-- Scenario 1: Apply a 1% discount to loan interest rates for customers above 60 years old.

DECLARE
    CURSOR c_seniorloans IS
        SELECT l.loan_id, c.name, l.interest_rate, c.age
        FROM Customers c
        JOIN Loans l ON c.customer_id = l.customer_id
        WHERE c.age > 60;
    
    v_old_rate NUMBER(5,2);
    v_new_rate NUMBER(5,2);
BEGIN
    FOR r_loan IN c_seniorloans LOOP
        v_old_rate := r_loan.interest_rate;
        v_new_rate := v_old_rate - 1.00;
        
        UPDATE Loans
        SET interest_rate = v_new_rate
        WHERE loan_id = r_loan.loan_id;
        
        DBMS_OUTPUT.PUT_LINE('Customer: ' || r_loan.name || ' (Age: ' || r_loan.age || ') | ' ||
                             'Loan ID: ' || r_loan.loan_id || ' | ' ||
                             'Old Rate: ' || TO_CHAR(v_old_rate, '99.99') || '% | ' ||
                             'New Rate: ' || TO_CHAR(v_new_rate, '99.99') || '%');
    END LOOP;
    COMMIT;
END;
/
