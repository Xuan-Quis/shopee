import React from "react";
import "./App.css";
import useRouterElement from "./hooks/useRouterElement";

function App() {
  const routeElement = useRouterElement();
  return <>{routeElement}</>;
}

export default App;
