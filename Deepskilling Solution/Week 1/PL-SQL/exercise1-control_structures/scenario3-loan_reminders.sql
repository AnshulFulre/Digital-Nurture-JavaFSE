-- Scenario 3: Send reminders to customers whose loans are due within the next 30 days.

DECLARE
    CURSOR c_dueLoans IS
        SELECT c.name, l.loan_id, l.principal_amount, l.due_date
        FROM Customers c
        JOIN Loans l ON c.customer_id = l.customer_id
        WHERE l.due_date BETWEEN SYSDATE AND SYSDATE + 30;
    
    v_days_left NUMBER;
BEGIN
    FOR r_loan IN c_dueLoans LOOP
        v_days_left := CEIL(r_loan.due_date - SYSDATE);
        
        DBMS_OUTPUT.PUT_LINE('REMINDER:' || r_loan.name || ', your loan (ID: ' || r_loan.loan_id || ') ' ||
                             'of $' || LTRIM(TO_CHAR(r_loan.principal_amount, '99,999.99')) || ' is due in ' || 
                             v_days_left || ' days (Date: ' || TO_CHAR(r_loan.due_date, 'YYYY-MM-DD') || '). Please ensure sufficient funds.');
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Reminder generation completed.');
END;
/
