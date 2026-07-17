import ListofPlayers from "./ListofPlayers";
import Scorebelow70 from "./Scorebelow70";
import OddPlayers from "./OddPlayers";
import EvenPlayers from "./EvenPlayers";
import ListofIndianPlayers from "./ListofIndianPlayers";

function App() {
  const flag = true;

  const playerNames = [
    "Dhoni",
    "Rohit",
    "Gill",
    "Rahul",
    "Hardik",
    "Jadeja",
    "Surya",
    "Pant",
    "Bumrah",
    "Shami",
    "Kuldeep"
  ];

  const playerScores = [85, 67, 45, 72, 30, 55, 90, 40, 12, 18, 25];

  
  const players = playerNames.map((name, index) => ({
    name : name,
    score: playerScores[index]
  }));

  const T20players = ["Virat", "Raina", "Ashwin"];
  const RanjiTrophy = ["Pujara", "Rahane", "Iyer"];

  const IndianPlayers = [...T20players, ...RanjiTrophy];

  if (flag) {
    return (
      <div>
        <h1>List of Players</h1>
        <ListofPlayers players={players} />

        <hr />

        <h1>Players with Score Below 70</h1>
        <Scorebelow70 players={players} />
      </div>
    );
  } else {
    return (
      <div>
        <h1>Odd Players</h1>
        <OddPlayers players={playerNames} />

        <hr />

        <h1>Even Players</h1>
        <EvenPlayers players={playerNames} />

        <hr />

        <h1>List of Indian Players Merged:</h1>
        <ListofIndianPlayers IndianPlayers={IndianPlayers} />
      </div>
    );
  }
}

export default App;