import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { isUserLoggedIn, logout } from "../services/AuthService";

const HeaderComponent = () => {
  const isAuth = isUserLoggedIn();
  const navigate = useNavigate();

  function handlLogout() {
    logout();
    navigate("/login");
  }
  return (
    <div>
      <header className="header">
        <nav className="navbar navbar-expand-md navbar-dark ">
          <div>
            <a href="http://localhost:3000" className="navbar-brand">
              Patient Medicine and Appointment Application
            </a>
          </div>
          
               
        </nav>
      </header>
    </div>
  );
};

export default HeaderComponent;
