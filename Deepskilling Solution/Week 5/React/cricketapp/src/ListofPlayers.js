export default function ListofPlayers({ players }) {
  return (
    <ul>
      {players.map((player, index) => (
        <li key={index}>
          Mr. {player.name} - Score: {player.score}
        </li>
      ))}
    </ul>
  );
}