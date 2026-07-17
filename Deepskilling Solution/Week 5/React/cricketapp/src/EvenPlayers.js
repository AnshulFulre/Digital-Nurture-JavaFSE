export default function EvenPlayers({ players }) {
  const [, second, , fourth, , sixth, , eighth, , tenth] = players;

  return (
    <ul>
      <li>Second: {second}2</li>
      <li>Fourth: {fourth}4</li>
      <li>Sixth: {sixth}6</li>
    </ul>
  );
}