import React, { useState } from "react";
import GuestPage from "./GuestPage";
import UserPage from "./UserPage";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <div style={{ textAlign: "center", marginTop: "30px" }}>
      <h1>Ticket Booking App</h1>

      {isLoggedIn ? (
        <>
          <UserPage />
          <button onClick={() => setIsLoggedIn(false)}>
            Logout
          </button>
        </>
      ) : (
        <>
          <GuestPage />
          <button onClick={() => setIsLoggedIn(true)}>
            Login
          </button>
        </>
      )}
    </div>
  );
}

export default App;