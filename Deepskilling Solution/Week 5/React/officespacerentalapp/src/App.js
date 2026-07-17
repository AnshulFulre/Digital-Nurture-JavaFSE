import React from "react";
import office from "./office.jpg";

function App() {

  const heading = "Office Space";

  const officeList = [
    {
      Name: "DBS",
      Rent: 50000,
      Address: "Chennai",
      Image: office
    },
    {
      Name: "Regus",
      Rent: 70000,
      Address: "Bangalore",
      Image: office
    },
    {
      Name: "WeWork",
      Rent: 55000,
      Address: "Hyderabad",
      Image: office
    }
  ];

  return (
    <div className="container">
      <h1>{heading}, at Affordable Range</h1>

      {officeList.map((item, index) => {

        let color = item.Rent <= 60000 ? "red" : "green";

        return (
          <div key={index} className="card">

            <img
              src={item.Image}
              alt="Office Space"
              width="250"
              height="250"
            />

            <h2>Name: {item.Name}</h2>

            <h3 style={{ color: color }}>
              Rent: Rs. {item.Rent}
            </h3>

            <h3>Address: {item.Address}</h3>

            <hr />

          </div>
        );
      })}
    </div>
  );
}

export default App;