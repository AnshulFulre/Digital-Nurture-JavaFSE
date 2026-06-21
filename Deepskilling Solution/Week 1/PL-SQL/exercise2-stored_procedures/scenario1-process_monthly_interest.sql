-- Scenario 1: Process monthly interest for all savings accounts (1% interest).

DECLARE
    v_rows_updated NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Processing Monthly Savings Interest ---');
    
    UPDATE Accounts
    SET balance = balance * 1.01
    WHERE account_type = 'Savings';
    
    v_rows_updated := SQL%ROWCOUNT;
    
    DBMS_OUTPUT.PUT_LINE('Successfully applied 1% monthly interest to ' || v_rows_updated || ' savings accounts.');
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error processing interest: ');
        ROLLBACK;
END;
/
