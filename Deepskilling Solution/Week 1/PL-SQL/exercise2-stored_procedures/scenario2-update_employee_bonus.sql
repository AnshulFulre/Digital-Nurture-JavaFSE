-- Scenario 2: Update employee salaries in a department by adding a bonus percentage.

CREATE OR REPLACE PROCEDURE updateEmployeeBonus (
    p_dept IN VARCHAR2,
    p_bonusPercent IN NUMBER
) AS
    v_rows_updated NUMBER := 0;
BEGIN
    IF p_bonusPercent IS NULL OR p_bonusPercent < 0 THEN
        DBMS_OUTPUT.PUT_LINE('Error: Invalid bonus percentage. Must be a non-negative number.');
        RETURN;
    END IF;
    
    UPDATE Employees
    SET salary = salary * (1 + (p_bonusPercent / 100))
    WHERE department = p_dept;
    
    v_rows_updated := SQL%ROWCOUNT;
    
    DBMS_OUTPUT.PUT_LINE('Updated ' || v_rows_updated || ' employees in department "' || p_dept || 
                         '" with a bonus of ' || p_bonusPercent || '%.');
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
        ROLLBACK;
END;
/
