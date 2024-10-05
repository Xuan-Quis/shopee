import React from "react";
import Header from "../../components/Header/Header";
import Categories from "./Categories";
import FooterComponent from "../../components/FooterComponent/FooterComponent";
import CreateComponent from "./CreateComponent";

const HomePage = () => {
  return (
    <div style={{ background: "#f5f5f5" }}>
      <Header></Header>
      <Categories></Categories>
      <CreateComponent />
      <FooterComponent />
    </div>
  );
};

export default HomePage;
