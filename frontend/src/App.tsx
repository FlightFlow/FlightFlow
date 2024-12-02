const App = () => {
  const example = import.meta.env.VITE_APP_EXAMPLE_VARIABLE;

  return (
    <div>
      <b>App</b>
      <div>{example}</div>
    </div>
  );
};

export default App;
