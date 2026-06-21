-- Scenario 2: Promote a customer to VIP status if their total balance across all accounts exceeds $10,000.

DECLARE
    CURSOR c_customers IS
        SELECT c.customer_id, c.name, COALESCE(SUM(a.balance), 0) AS total_balance
        FROM Customers c
        LEFT JOIN Accounts a ON c.customer_id = a.customer_id
        GROUP BY c.customer_id, c.name;
BEGIN
    FOR r_customer IN c_customers LOOP
        IF r_customer.total_balance > 10000.00 THEN
            UPDATE Customers
            SET is_vip = 'TRUE'
            WHERE customer_id = r_customer.customer_id;
            
            DBMS_OUTPUT.PUT_LINE('Customer: ' || r_customer.name || ' (ID: ' || r_customer.customer_id || ') PROMOTED to VIP | ' ||
                                 'Total Balance: $' || LTRIM(TO_CHAR(r_customer.total_balance, '99,999.99')));
        ELSE
            DBMS_OUTPUT.PUT_LINE('Customer: ' || r_customer.name || ' (ID: ' || r_customer.customer_id || ') remains standard | ' );
        END IF;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('VIP promotion processing complete.');
END;
/
