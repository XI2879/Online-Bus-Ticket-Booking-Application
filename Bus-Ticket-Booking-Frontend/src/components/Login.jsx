import React, { useEffect, useState } from 'react';
import { LoginAPICall, RegisterAPICall, getToken, saveLoggedInUser, storeToken } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
  const [showRegisterForm, setShowRegisterForm] = useState(false);
  const [userName,setUserName] = useState();
  const [password,setPassword] = useState();
  const [registerData, setRegisterData] = useState({
    name: '',
    userName: '',
    email: '',
    password: ''
  });
  const navigate = useNavigate();

  const handleToggleForm = () => {
    setShowRegisterForm(!showRegisterForm);
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    const response = await LoginAPICall(userName,password);
    const token = "Bearer " + response.data.accessToken;
    const role = response.data.role;
    storeToken(token);
    saveLoggedInUser(userName, role);
    navigate("/");
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setRegisterData({ ...registerData, [name]: value });
  };
  
  useEffect(() => {
    handleRegister(); 
  }, []);

  const handleRegister = async () => {
  
    // try{
    //   const response = await RegisterAPICall(registerData);
    //   console.log("no error")
    // }catch{
    //   console.log("error occured")
    // }
    
  };

  return (
    <div className="login-page">
      <div className="login-card">
        <h2>{showRegisterForm ? 'Register' : 'Login'}</h2>
        {showRegisterForm ? (
          <form onSubmit={()=>handleRegister(e)}>
            {/* Register form fields */}
            <div>
              <label htmlFor="registerName">Name</label>
              <input type="text" id="registerName" placeholder="Enter your Name" 
              onChange={handleInputChange}/>
            </div>
            <div>
              <label htmlFor="registerUsername">Username</label>
              <input type="text" id="registerUsername" placeholder="Enter your username" 
              onChange={handleInputChange}/>
            </div>
            <div>
              <label htmlFor="registerEmail">Email</label>
              <input type="text" id="registerEmail" placeholder="Enter your email" 
              onChange={handleInputChange}/>
            </div>
            <div>
              <label htmlFor="registerPassword">Password</label>
              <input type="password" id="registerPassword" placeholder="Enter your password" 
              onChange={handleInputChange}/>
            </div>
            <button type="submit">Register</button>
          </form>
        ) : (
          <form onSubmit={handleLogin}>
            {/* Login form fields */}
            <div>
              <label htmlFor="loginUsername">Username</label>
              <input type="text" id="loginUsername" placeholder="Enter your username" 
              value={userName}
              onChange={(e)=>setUserName(e.target.value)}/>
            </div>
            <div>
              <label htmlFor="loginPassword">Password</label>
              <input type="password" id="loginPassword" placeholder="Enter your password" 
              value={password}
              onChange={(e)=>setPassword(e.target.value)}/>
            </div>
            <button type="submit">Login</button>
          </form>
        )}
        <div>
          <button onClick={handleToggleForm}>
            {showRegisterForm ? 'Already have an account? Login' : "Don't have an account? Register"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;