export default function OddPlayers({ players }) {
  const [first, , third, , fifth, , seventh, , ninth, , eleventh] = players;

  return (
    <ul>
      <li>First: {first}1</li>
      <li>Third: {third}3</li>
      <li>Fifth: {fifth}5</li>
    </ul>
  );
}