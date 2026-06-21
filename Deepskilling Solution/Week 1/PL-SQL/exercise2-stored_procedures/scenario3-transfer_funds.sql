-- Scenario 3: Transfer funds between two accounts, verifying sufficient balance.

CREATE OR REPLACE PROCEDURE TransferFunds (
    from_account IN NUMBER,
    to_account IN NUMBER,
    amount IN NUMBER
) AS
    source_balance NUMBER(15, 2);
    source_exists NUMBER := 0;
    dest_exists NUMBER := 0;
BEGIN

    IF amount <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('Error: Transfer amount must be positive.');
        RETURN;
    END IF;
    
    BEGIN
        SELECT balance INTO source_balance
        FROM Accounts
        WHERE account_id = from_account;
        source_exists := 1;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: Source account (ID: ' || from_account || ') does not exist.');
            RETURN;
    END;

    SELECT COUNT(*) INTO dest_exists
    FROM Accounts
    WHERE account_id = to_account;
    
    IF dest_exists = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Error: Destination account (ID: ' || to_account || ') does not exist.');
        RETURN;
    END IF;

    -- Check for sufficient balance
    IF source_balance >= amount THEN
        -- Deduct from source account
        UPDATE Accounts
        SET balance = balance - amount
        WHERE account_id = from_account;
        
        -- Add to destination account
        UPDATE Accounts
        SET balance = balance + amount
        WHERE account_id = to_account;
        
        DBMS_OUTPUT.PUT_LINE('Success: Transferred $' || LTRIM(TO_CHAR(amount, '99,999.99')) || 
                             ' from Account ' || from_account || ' to Account ' || to_account);
        COMMIT;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in Account ' || from_account);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: Transfer failed. System error' );
        ROLLBACK;
END;
/
